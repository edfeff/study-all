package chapter02.s01.register;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanEntry;
import org.springframework.beans.factory.parsing.ParseState;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyBeanDefinitionParserDelegate extends BeanDefinitionParserDelegate {
    Log log = LogFactory.getLog(StudyBeanDefinitionParserDelegate.class);
    String SINGLETON_ATTRIBUTE = "singleton";

    /**
     * Create a new BeanDefinitionParserDelegate associated with the supplied
     * {@link XmlReaderContext}.
     *
     * @param readerContext
     */
    public StudyBeanDefinitionParserDelegate(XmlReaderContext readerContext) {
        super(readerContext);
    }

    ParseState parseState = new ParseState();

    @Override
    public AbstractBeanDefinition parseBeanDefinitionElement(Element ele, String beanName, BeanDefinition containingBean) {
        log.info("---------------------------");

        log.info("1 parseBeanDefinitionElement     id:  " + ele.getAttribute("id") + " beanName: " + beanName + " : " + containingBean);
//        return super.parseBeanDefinitionElement(ele, beanName, containingBean);

        log.info("创建BeanDefinition，首先将 " + beanName + " 压入栈");
        this.parseState.push(new BeanEntry(beanName));

        String className = null;

        log.info("查询：" + beanName + "的class属性");
        if (ele.hasAttribute(CLASS_ATTRIBUTE)) {
            className = ele.getAttribute(CLASS_ATTRIBUTE).trim();
            log.info("查询：" + beanName + "的" + CLASS_ATTRIBUTE + "属性为" + className);
        }

        try {
            String parent = null;
            log.info("查询：" + beanName + "的parent");
            if (ele.hasAttribute(PARENT_ATTRIBUTE)) {
                parent = ele.getAttribute(PARENT_ATTRIBUTE);
                log.info("查询：" + beanName + "的" + PARENT_ATTRIBUTE + "属性为" + parent);
            }

            log.info("利用class和parent 创建一个 AbstractBeanDefinition");
            AbstractBeanDefinition bd = createBeanDefinition(className, parent);

            log.info("解析" + bd + "的属性");
            parseBeanDefinitionAttributes(ele, beanName, containingBean, bd);

            log.info("解析 子标签: <description>");
            bd.setDescription(DomUtils.getChildElementValueByTagName(ele, DESCRIPTION_ELEMENT));

            log.info("解析 元数据: <meta>");
            parseMetaElements(ele, bd);

            log.info("解析 查找方法：<" + LOOKUP_METHOD_ELEMENT + ">");
            parseLookupOverrideSubElements(ele, bd.getMethodOverrides());

            log.info("解析 替换方法：<" + REPLACED_METHOD_ELEMENT + ">");
            parseReplacedMethodSubElements(ele, bd.getMethodOverrides());

            log.info("解析 构造参数<" + CONSTRUCTOR_ARG_ELEMENT + ">");
            parseConstructorArgElements(ele, bd);

            log.info("解析 构造参数<" + PROPERTY_ELEMENT + ">");
            parsePropertyElements(ele, bd);

            log.info("解析 构造参数<" + QUALIFIER_ELEMENT + ">");
            parseQualifierElements(ele, bd);

            log.info("设置此BeanDefinition的原始资源和源文件");
            bd.setResource(this.getReaderContext().getResource());

            bd.setSource(extractSource(ele));

            return bd;
        } catch (ClassNotFoundException ex) {
            error("Bean class [" + className + "] not found", ele, ex);
        } catch (NoClassDefFoundError err) {
            error("Class that bean class [" + className + "] depends on not found", ele, err);
        } catch (Throwable ex) {
            error("Unexpected failure during bean definition parsing", ele, ex);
        } finally {
            this.parseState.pop();
        }

        return null;
    }

    @Override
    public AbstractBeanDefinition parseBeanDefinitionAttributes(Element ele, String beanName, BeanDefinition containingBean, AbstractBeanDefinition bd) {

        log.info("设置 Scope");
        if (ele.hasAttribute(SINGLETON_ATTRIBUTE)) {
            log.info("查询设置单例属性 " + SINGLETON_ATTRIBUTE);
            error("Old 1.x 'singleton' attribute in use - upgrade to 'scope' declaration", ele);
        } else if (ele.hasAttribute(SCOPE_ATTRIBUTE)) {
            log.info("查询并设置 " + SCOPE_ATTRIBUTE);
            bd.setScope(ele.getAttribute(SCOPE_ATTRIBUTE));
        } else if (containingBean != null) {
            // Take default from containing bean in case of an inner bean definition.
            bd.setScope(containingBean.getScope());
        }
        log.info("设置 " + ABSTRACT_ATTRIBUTE);
        if (ele.hasAttribute(ABSTRACT_ATTRIBUTE)) {
            bd.setAbstract(TRUE_VALUE.equals(ele.getAttribute(ABSTRACT_ATTRIBUTE)));
        }
        log.info("设置 " + LAZY_INIT_ATTRIBUTE);
        String lazyInit = ele.getAttribute(LAZY_INIT_ATTRIBUTE);
        if (DEFAULT_VALUE.equals(lazyInit)) {
            lazyInit = this.getDefaults().getLazyInit();
        }
        bd.setLazyInit(TRUE_VALUE.equals(lazyInit));

        log.info("设置 " + AUTOWIRE_ATTRIBUTE);
        String autowire = ele.getAttribute(AUTOWIRE_ATTRIBUTE);
        bd.setAutowireMode(getAutowireMode(autowire));

        log.info("设置 " + DEPENDENCY_CHECK_ATTRIBUTE);
        String dependencyCheck = ele.getAttribute(DEPENDENCY_CHECK_ATTRIBUTE);
        bd.setDependencyCheck(getDependencyCheck(dependencyCheck));

        log.info("设置 " + DEPENDS_ON_ATTRIBUTE);
        if (ele.hasAttribute(DEPENDS_ON_ATTRIBUTE)) {
            String dependsOn = ele.getAttribute(DEPENDS_ON_ATTRIBUTE);
            bd.setDependsOn(StringUtils.tokenizeToStringArray(dependsOn, MULTI_VALUE_ATTRIBUTE_DELIMITERS));
        }

        log.info("设置 " + AUTOWIRE_CANDIDATE_ATTRIBUTE);
        String autowireCandidate = ele.getAttribute(AUTOWIRE_CANDIDATE_ATTRIBUTE);
        if ("".equals(autowireCandidate) || DEFAULT_VALUE.equals(autowireCandidate)) {
            String candidatePattern = this.getDefaults().getAutowireCandidates();
            if (candidatePattern != null) {
                String[] patterns = StringUtils.commaDelimitedListToStringArray(candidatePattern);
                bd.setAutowireCandidate(PatternMatchUtils.simpleMatch(patterns, beanName));
            }
        } else {
            bd.setAutowireCandidate(TRUE_VALUE.equals(autowireCandidate));
        }

        log.info("设置 " + PRIMARY_ATTRIBUTE);
        if (ele.hasAttribute(PRIMARY_ATTRIBUTE)) {
            bd.setPrimary(TRUE_VALUE.equals(ele.getAttribute(PRIMARY_ATTRIBUTE)));
        }

        log.info("设置 " + INIT_METHOD_ATTRIBUTE);
        if (ele.hasAttribute(INIT_METHOD_ATTRIBUTE)) {
            String initMethodName = ele.getAttribute(INIT_METHOD_ATTRIBUTE);
            if (!"".equals(initMethodName)) {
                bd.setInitMethodName(initMethodName);
            }
        } else {
            if (this.getDefaults().getInitMethod() != null) {
                bd.setInitMethodName(this.getDefaults().getInitMethod());
                bd.setEnforceInitMethod(false);
            }
        }

        log.info("设置 " + DESTROY_METHOD_ATTRIBUTE);

        if (ele.hasAttribute(DESTROY_METHOD_ATTRIBUTE)) {
            String destroyMethodName = ele.getAttribute(DESTROY_METHOD_ATTRIBUTE);
            bd.setDestroyMethodName(destroyMethodName);
        } else {
            if (this.getDefaults().getDestroyMethod() != null) {
                bd.setDestroyMethodName(this.getDefaults().getDestroyMethod());
                bd.setEnforceDestroyMethod(false);
            }
        }

        log.info("设置 " + FACTORY_METHOD_ATTRIBUTE);
        if (ele.hasAttribute(FACTORY_METHOD_ATTRIBUTE)) {
            bd.setFactoryMethodName(ele.getAttribute(FACTORY_METHOD_ATTRIBUTE));
        }
        if (ele.hasAttribute(FACTORY_BEAN_ATTRIBUTE)) {
            bd.setFactoryBeanName(ele.getAttribute(FACTORY_BEAN_ATTRIBUTE));
        }

        return bd;
    }

    @Override
    public BeanDefinitionHolder parseBeanDefinitionElement(Element ele, BeanDefinition containingBean) {
        log.info("---------------------------");
        log.info("2 parseBeanDefinitionElement   id: " + ele.getAttribute("id") + " : " + containingBean);
//        return super.parseBeanDefinitionElement(ele, containingBean);

        String id = ele.getAttribute(ID_ATTRIBUTE);
        String nameAttr = ele.getAttribute(NAME_ATTRIBUTE);

        List<String> aliases = new ArrayList<String>();
        if (StringUtils.hasLength(nameAttr)) {
            String[] nameArr = StringUtils.tokenizeToStringArray(nameAttr, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
            aliases.addAll(Arrays.asList(nameArr));
        }

        String beanName = id;
        if (!StringUtils.hasText(beanName) && !aliases.isEmpty()) {
            beanName = aliases.remove(0);
            if (logger.isDebugEnabled()) {
                logger.debug("No XML 'id' specified - using '" + beanName +
                        "' as bean name and " + aliases + " as aliases");
            }
        }

        if (containingBean == null) {
            checkNameUniqueness(beanName, aliases, ele);
        }
        log.info("---------解析xml中的BeanDefinition------------");
        AbstractBeanDefinition beanDefinition = parseBeanDefinitionElement(ele, beanName, containingBean);

        if (beanDefinition != null) {
            log.info("确定Bean的名称，如果没有设置的话，这里会生成出一个随机的名称");
            if (!StringUtils.hasText(beanName)) {

                try {
                    if (containingBean != null) {
                        beanName = BeanDefinitionReaderUtils.generateBeanName(beanDefinition, this.getReaderContext().getRegistry(), true);
                    } else {
                        beanName = this.getReaderContext().generateBeanName(beanDefinition);
                        // Register an alias for the plain bean class name, if still possible,
                        // if the generator returned the class name plus a suffix.
                        // This is expected for Spring 1.2/2.0 backwards compatibility.
                        String beanClassName = beanDefinition.getBeanClassName();
                        if (beanClassName != null &&
                                beanName.startsWith(beanClassName) && beanName.length() > beanClassName.length() &&
                                !this.getReaderContext().getRegistry().isBeanNameInUse(beanClassName)) {
                            aliases.add(beanClassName);
                        }
                    }
                    if (logger.isDebugEnabled()) {
                        logger.debug("Neither XML 'id' nor 'name' specified - " +
                                "using generated bean name [" + beanName + "]");
                    }
                } catch (Exception ex) {
                    error(ex.getMessage(), ele);
                    return null;
                }
            }
            log.info("设置 别名");
            String[] aliasesArray = StringUtils.toStringArray(aliases);
            log.info("包装成BeanDefinitionHolder返回,里面装了 本名,别名,BeanDefinition对象");
            return new BeanDefinitionHolder(beanDefinition, beanName, aliasesArray);
        }
        return null;
    }
}
