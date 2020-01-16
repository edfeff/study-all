package chapter05.s01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.DecoratingClassLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.HashSet;
import java.util.Set;

public class MyXmlBeanFactory extends XmlBeanFactory {
    Log log = LogFactory.getLog(MyXmlBeanFactory.class);

    public MyXmlBeanFactory(Resource resource) throws BeansException {
        super(resource);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        log.info("getBean");
        return doGetBean(name, null, null, false);
    }

    @Override
    protected String transformedBeanName(String name) {
        log.info("转换名称，确定是否转FactoryBean的名称，比如将&X --> X , &&&X --> X");
        String s = BeanFactoryUtils.transformedBeanName(name);
        log.info("FactoryBean名称转换：" + name + "-->" + s);
        String s1 = canonicalName(s);
        log.info("取出真正的名字，即转换别名。" + s + "-->" + s1);
        return s1;
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        log.info("从beanDefinitionMap中查询是否有：" + beanName + "的BeanDefinition");
        BeanDefinition beanDefinition = super.getBeanDefinition(beanName);
        log.info(beanName + " : " + beanDefinition);
        return beanDefinition;
    }

//    @Override
//    protected boolean isDependent(String beanName, String dependentBeanName) {
//        return isDependent(beanName, dependentBeanName, null);
//    }

//    private boolean isDependent(String beanName, String dependentBeanName, Set<String> alreadySeen) {
//        if (alreadySeen != null && alreadySeen.contains(beanName)) {
//            return false;
//        }
//        String canonicalName = canonicalName(beanName);
//        Set<String> dependentBeans = this.dependentBeanMap.get(canonicalName);
//        if (dependentBeans == null) {
//            return false;
//        }
//        if (dependentBeans.contains(dependentBeanName)) {
//            return true;
//        }
//        for (String transitiveDependency : dependentBeans) {
//            if (alreadySeen == null) {
//                alreadySeen = new HashSet<String>();
//            }
//            alreadySeen.add(beanName);
//            if (isDependent(transitiveDependency, dependentBeanName, alreadySeen)) {
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    protected <T> T doGetBean(final String name, final Class<T> requiredType, final Object[] args, boolean typeCheckOnly) throws BeansException {
        log.info("doGetBean");
        log.info("转换bean的名称,取出真正的Bean名称" + name);
        final String beanName = transformedBeanName(name);
        Object bean;


        // Eagerly check singleton cache for manually registered singletons.
        log.info("查询是否有单例缓存");
        Object sharedInstance = getSingleton(beanName);
        log.info("缓存结果：" + sharedInstance);

        if (sharedInstance != null && args == null) {
            if (logger.isDebugEnabled()) {
                if (isSingletonCurrentlyInCreation(beanName)) {
                    logger.debug("Returning eagerly cached instance of singleton bean '" + beanName +
                            "' that is not fully initialized yet - a consequence of a circular reference");
                } else {
                    logger.debug("Returning cached instance of singleton bean '" + beanName + "'");
                }
            }
            log.info("对缓存的单例进行处理");
            bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
        } else {
            // Fail if we're already creating this bean instance:
            // We're assumably within a circular reference.
            log.info("确定当前的Bean是否已经在创建实例化的过程中了，如果已经有一个在创建的过程中了，此处就要异常");
            if (isPrototypeCurrentlyInCreation(beanName)) {
                throw new BeanCurrentlyInCreationException(beanName);
            }

            // Check if bean definition exists in this factory.
            log.info("委托父BeanFactory，询问自己的父级是否有已经实例化的Bean了");
            BeanFactory parentBeanFactory = getParentBeanFactory();
            log.info("父级为:" + parentBeanFactory);

            if (parentBeanFactory != null && !containsBeanDefinition(beanName)) {
                log.info("存在父级，且自身没有" + beanName + "的BeanDefinition定义");
                // Not found -> check parent.
                String nameToLookup = originalBeanName(name);
//                log.info("需要先把名字恢复到原来的样子，因为上面操作时，已经做了去&处理和别名转换，这么要把这些恢复回去,现在是：" + name + ",恢复成：" + nameToLookup);
                if (args != null) {
                    // Delegation to parent with explicit args.
                    return (T) parentBeanFactory.getBean(nameToLookup, args);
                } else {
                    // No args -> delegate to standard getBean method.
                    return parentBeanFactory.getBean(nameToLookup, requiredType);
                }
            }

            if (!typeCheckOnly) {
                log.info("标记" + beanName + " 已经处理了，把名字放入到alreadyCreated的Map中");
                markBeanAsCreated(beanName);
            }

            try {
                log.info("获取合并后的" + beanName + "的BeanDefinition");
                final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);

                log.info("校验合并后的BeanDefinition，即合并后的BenaDefinition不能是抽象的！！");
                checkMergedBeanDefinition(mbd, beanName, args);

                // Guarantee initialization of beans that the current bean depends on.
                log.info("检查此BeanDefinition有没有DependsOn的，如有，则先去触发这些DependsOn的实例化");
                String[] dependsOn = mbd.getDependsOn();
                /*
                    <pre>
                        当前依赖情况
                                        A
                        beanName -->{   B
                                        C

                        检查

                        1， A 是否依赖 beanName
                    </pre>
                 */
                if (dependsOn != null) {
                    log.info("实例化此BeanDefinition：" + beanName + "的dependOn");
                    for (String dep : dependsOn) {
                        log.info("实例化：" + dep + ",先检查下是否循环依赖");
                        if (isDependent(beanName, dep)) {
                            throw new BeanCreationException(mbd.getResourceDescription(), beanName,
                                    "Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
                        }
                        log.info("记录依赖关系");
                        registerDependentBean(dep, beanName);
                        log.info("实例化:" + dep);
                        getBean(dep);
                    }
                }

                log.info("准备创建实例");
                // Create bean instance.
                if (mbd.isSingleton()) {

                    log.info(beanName + "是单例 Singleton 情况");
                    log.info("创建单例前的操作: 1 如果此BeanName 实例化不需要检查,即在inCreationCheckExclusions中,就把创建的beanName放入到singletonsCurrentlyInCreation");
                    sharedInstance = getSingleton(beanName, new ObjectFactory<Object>() {
                        @Override
                        public Object getObject() throws BeansException {
                            try {
                                return createBean(beanName, mbd, args);
                            } catch (BeansException ex) {
                                // Explicitly remove instance from singleton cache: It might have been put there
                                // eagerly by the creation process, to allow for circular reference resolution.
                                // Also remove any beans that received a temporary reference to the bean.
                                destroySingleton(beanName);
                                throw ex;
                            }
                        }
                    });

                    log.info(" sharedInstance 创建完毕," + sharedInstance + "继续处理: getObjectForBeanInstance ");
                    bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);

                } else if (mbd.isPrototype()) {
                    log.info(beanName + "是原型 Prototype 情况");
                    // It's a prototype -> create a new instance.
                    Object prototypeInstance = null;
                    try {
                        beforePrototypeCreation(beanName);
                        prototypeInstance = createBean(beanName, mbd, args);
                    } finally {
                        afterPrototypeCreation(beanName);
                    }
                    bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
                } else {
                    log.info(beanName + "是其他情况的Scope");
                    String scopeName = mbd.getScope();
                    final Scope scope = getRegisteredScope(scopeName);
                    if (scope == null) {
                        throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
                    }
                    try {
                        Object scopedInstance = scope.get(beanName, new ObjectFactory<Object>() {
                            @Override
                            public Object getObject() throws BeansException {
                                beforePrototypeCreation(beanName);
                                try {
                                    return createBean(beanName, mbd, args);
                                } finally {
                                    afterPrototypeCreation(beanName);
                                }
                            }
                        });
                        bean = getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
                    } catch (IllegalStateException ex) {
                        throw new BeanCreationException(beanName,
                                "Scope '" + scopeName + "' is not active for the current thread; consider " +
                                        "defining a scoped proxy for this bean if you intend to refer to it from a singleton",
                                ex);
                    }
                }
            } catch (BeansException ex) {
                cleanupAfterBeanCreationFailure(beanName);
                throw ex;
            }
        }

        // Check if required type matches the type of the actual bean instance.
        if (requiredType != null && bean != null && !requiredType.isAssignableFrom(bean.getClass())) {
            try {
                return getTypeConverter().convertIfNecessary(bean, requiredType);
            } catch (TypeMismatchException ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Failed to convert bean '" + name + "' to required type '" +
                            ClassUtils.getQualifiedName(requiredType) + "'", ex);
                }
                throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
            }
        }
        return (T) bean;
    }

    @Override
    protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) throws BeanCreationException {
        log.info("创建bean:" + beanName);
        if (logger.isDebugEnabled()) {
            logger.debug("Creating instance of bean '" + beanName + "'");
        }
        RootBeanDefinition mbdToUse = mbd;

        // Make sure bean class is actually resolved at this point, and
        // clone the bean definition in case of a dynamically resolved Class
        // which cannot be stored in the shared merged bean definition.
        log.info("解析出需要实例化的类");
        Class<?> resolvedClass = resolveBeanClass(mbd, beanName);
        log.info("解析结果:" + resolvedClass.getName());
        if (resolvedClass != null && !mbd.hasBeanClass() && mbd.getBeanClassName() != null) {
            log.info("深度拷贝一份原始的beanDefinition,将Class设置进去");
            mbdToUse = new RootBeanDefinition(mbd);
            mbdToUse.setBeanClass(resolvedClass);
        }

        // Prepare method overrides.
        try {
            log.info("准备新BeanDefinition的方法重写,主要是检查需要重写的方法名,此Bean的Class中是存在的");
            mbdToUse.prepareMethodOverrides();
        } catch (BeanDefinitionValidationException ex) {
            throw new BeanDefinitionStoreException(mbdToUse.getResourceDescription(), beanName, "Validation of method overrides failed", ex);
        }

        try {
            // Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
            log.info("实例化此bean 前,给BeanPostProcessors一次覆盖的机会,如果BeanPostProcessors处理后返回出一个非空对象(代理对象),此对象就是结果");
            log.info("依次执行 InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation(注意单词是实例化前),如果这些处理器返回值不为null时,则认为就是代理的对象," +
                    "并对这个代理的对象施加后处理,即继续调用所有BeanPostProcessor的postProcessAfterInitialization(注意单词是初始化后)方法");
            Object bean = resolveBeforeInstantiation(beanName, mbdToUse);

            if (bean != null) {
                log.info("预处理发现返回了一个代理:" + bean + " 将此代理作为结果返回");
                return bean;
            }
        } catch (Throwable ex) {
            throw new BeanCreationException(mbdToUse.getResourceDescription(), beanName,
                    "BeanPostProcessor before instantiation of bean failed", ex);
        }

        log.info("如果没有代理对象创建,就执行doCreateBean,来创建此Bean实例");
        Object beanInstance = doCreateBean(beanName, mbdToUse, args);

        if (logger.isDebugEnabled()) {
            logger.debug("Finished creating instance of bean '" + beanName + "'");
        }

        return beanInstance;
    }


    @Override
    protected Class<?> resolveBeanClass(final RootBeanDefinition mbd, String beanName, final Class<?>... typesToMatch) throws CannotLoadBeanClassException {
        try {
            log.info("1  首先查看BeanDefinition的 beanClass");
            if (mbd.hasBeanClass()) {
                return mbd.getBeanClass();
            }
            log.info("2 如有BenaDefinition没有设置beanClass属性,则此处进行解析");
            if (System.getSecurityManager() != null) {
                return AccessController.doPrivileged(new PrivilegedExceptionAction<Class<?>>() {
                    @Override
                    public Class<?> run() throws Exception {
                        return doResolveBeanClass(mbd, typesToMatch);
                    }
                }, getAccessControlContext());
            } else {
                return doResolveBeanClass(mbd, typesToMatch);
            }
        } catch (PrivilegedActionException pae) {
            ClassNotFoundException ex = (ClassNotFoundException) pae.getException();
            throw new CannotLoadBeanClassException(mbd.getResourceDescription(), beanName, mbd.getBeanClassName(), ex);
        } catch (ClassNotFoundException ex) {
            throw new CannotLoadBeanClassException(mbd.getResourceDescription(), beanName, mbd.getBeanClassName(), ex);
        } catch (LinkageError ex) {
            throw new CannotLoadBeanClassException(mbd.getResourceDescription(), beanName, mbd.getBeanClassName(), ex);
        }
    }

    private Class<?> doResolveBeanClass(RootBeanDefinition mbd, Class<?>... typesToMatch) throws ClassNotFoundException {
        log.info(" 自行解析 " + mbd);

        ClassLoader beanClassLoader = getBeanClassLoader();
        ClassLoader classLoaderToUse = beanClassLoader;
        if (!ObjectUtils.isEmpty(typesToMatch)) {
            log.info("如果传入了 typesToMatch 参数,按照此去解析");
            // When just doing type checks (i.e. not creating an actual instance yet),
            // use the specified temporary class loader (e.g. in a weaving scenario).
            ClassLoader tempClassLoader = getTempClassLoader();
            if (tempClassLoader != null) {
                classLoaderToUse = tempClassLoader;
                if (tempClassLoader instanceof DecoratingClassLoader) {
                    DecoratingClassLoader dcl = (DecoratingClassLoader) tempClassLoader;
                    for (Class<?> typeToMatch : typesToMatch) {
                        dcl.excludeClass(typeToMatch.getName());
                    }
                }
            }
        }
        log.info("检查" + mbd + "的BeanClassName");
        String className = mbd.getBeanClassName();
        if (className != null) {
            log.info("如果className 存在,则进行此处的解析");
            Object evaluated = evaluateBeanDefinitionString(className, mbd);
            if (!className.equals(evaluated)) {
                // A dynamically resolved expression, supported as of 4.2...
                if (evaluated instanceof Class) {
                    return (Class<?>) evaluated;
                } else if (evaluated instanceof String) {
                    return ClassUtils.forName((String) evaluated, classLoaderToUse);
                } else {
                    throw new IllegalStateException("Invalid class name expression result: " + evaluated);
                }
            }
            // When resolving against a temporary class loader, exit early in order
            // to avoid storing the resolved Class in the bean definition.
            if (classLoaderToUse != beanClassLoader) {
                return ClassUtils.forName(className, classLoaderToUse);
            }
        }
        return mbd.resolveBeanClass(beanClassLoader);
    }
}
