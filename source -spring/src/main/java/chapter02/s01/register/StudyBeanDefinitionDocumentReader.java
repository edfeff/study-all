package chapter02.s01.register;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudyBeanDefinitionDocumentReader extends DefaultBeanDefinitionDocumentReader {
    Log log = LogFactory.getLog(StudyBeanDefinitionDocumentReader.class);

    @Override
    protected void postProcessXml(Element root) {
        super.postProcessXml(root);
    }

    @Override
    protected void preProcessXml(Element root) {
        super.preProcessXml(root);
    }

    @Override
    protected BeanDefinitionParserDelegate createDelegate(XmlReaderContext readerContext, Element root, BeanDefinitionParserDelegate parentDelegate) {
        log.info("创建 StudyBeanDefinitionParserDelegate");
        BeanDefinitionParserDelegate delegate = new StudyBeanDefinitionParserDelegate(readerContext);
        delegate.initDefaults(root, parentDelegate);
        return delegate;
    }

    @Override
    public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext) {
        log.info("before registerBeanDefinitions");
        super.registerBeanDefinitions(doc, readerContext);
        log.info("after registerBeanDefinitions");
    }

    @Override
    protected void doRegisterBeanDefinitions(Element root) {
        log.info("before doRegisterBeanDefinitions: " + root.getTagName());
        super.doRegisterBeanDefinitions(root);
        log.info("after doRegisterBeanDefinitions: " + root.getTagName());
    }

    @Override
    protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
        log.info("before parseBeanDefinitions: " + root.getTagName());
        super.parseBeanDefinitions(root, delegate);
        log.info("after parseBeanDefinitions: " + root.getTagName());

    }

    @Override
    protected void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) {
        log.info("before processBeanDefinition: " + ele.getTagName() + ":" + ele.getAttribute("id") + ":" + ele.getAttribute("name"));

        log.info("======================== 开始 委托delegate来把xml加工成BeanDefinitionHolder ========================");
        BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
        log.info("======================== 完成 委托delegate来把xml加工成BeanDefinitionHolder ========================");

        if (bdHolder != null) {

            bdHolder = delegate.decorateBeanDefinitionIfRequired(ele, bdHolder);

            try {
                // Register the final decorated instance.
                log.info("注册BeanDefinition,注册别名");
//                BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getRegistry());

//            模拟  BeanDefinitionReaderUtils 的工作 ，分为两步

//                1 注册BeanDefinition
                /*
                细节： 注册BeanDefinition，并不是

                 */
                getReaderContext().getRegistry().registerBeanDefinition(bdHolder.getBeanName(), bdHolder.getBeanDefinition());

//                2 注册别名
                String[] aliases = bdHolder.getAliases();
                if (aliases != null) {
                    for (String alias : aliases) {
                        getReaderContext().getRegistry().registerAlias(bdHolder.getBeanName(), alias);
                    }
                }

            } catch (BeanDefinitionStoreException ex) {
                getReaderContext().error("Failed to register bean definition with name '" +
                        bdHolder.getBeanName() + "'", ele, ex);
            }

            // Send registration event.
            log.info("发送组件注册成功事件");
            getReaderContext().fireComponentRegistered(new BeanComponentDefinition(bdHolder));
        }
        log.info("after processBeanDefinition: " + ele.getTagName() + ":" + ele.getAttribute("id") + ":" + ele.getAttribute("name"));
    }
}
