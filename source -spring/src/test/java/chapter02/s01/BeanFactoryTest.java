package chapter02.s01;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.parsing.*;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.*;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.xml.XmlValidationModeDetector;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

public class BeanFactoryTest {
    /**
     * 测试 XmlBeanFactory的基本使用
     * 1. 读取xml配置文件
     * 2. 根据xml配置,找到类的配置,实例化
     * 3. 调用类的实例
     */
    @Test
    public void tes1() {

        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("chapter02-s01.xml"));
        MyTestBean myTestBean = (MyTestBean) xmlBeanFactory.getBean("myTestBean");
        Assert.assertEquals(myTestBean.getTestStr(), "testStr");
    }

    /**
     * 测试扩展  DefaultBeanDefinitionDocumentReader
     * DefaultBeanDefinitionDocumentReader允许我们在<beans>标签解析前后,插入自己的代码
     * 具体是使用函数:
     * 1. preProcessXml
     * 2. postProcessXml
     * <p>
     * 这一证明 <beans>标签是递归处理的
     */
    @Test
    public void tes2() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.setDocumentReaderClass(MyDefaultBeanDefinitionDocumentReader.class);
        reader.loadBeanDefinitions(new ClassPathResource("chapter02-s02.xml"));
        MyTestBean myTestBean = (MyTestBean) factory.getBean("myTestBean");
        Assert.assertEquals(myTestBean.getTestStr(), "testStr");
    }

    /**
     * 测试下profile
     * 默认的激活的profile为default
     */
    @Test
    public void tes3() {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("chapter02-s03.xml"));
        MyTestBean myTestBean = (MyTestBean) xmlBeanFactory.getBean("myTestBean");
        Assert.assertEquals(myTestBean.getTestStr(), "testStr");
    }

    /**
     * 测试profile为 dev
     * 默认情况下此dev下的bean不会被加载,必须设置环境中激活的profiles
     * XmlBeanFactory 本身及其父类并没有
     * 在XmlBeanDefinitionReader 的父类 AbstractBeanDefinitionReader中获取环境
     * 而 Environment 接口,并不支持直接设置profile,需要实现子接口ConfigurableEnvironment的类
     * 所以需要向下转型
     */
    @Test
    public void tes4() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        ConfigurableEnvironment abstractEnvironment = (ConfigurableEnvironment) reader.getEnvironment();

        abstractEnvironment.setActiveProfiles("dev", "default", "test");

        Assert.assertTrue(Arrays.asList(abstractEnvironment.getActiveProfiles()).contains("dev"));
        Assert.assertTrue(Arrays.asList(abstractEnvironment.getActiveProfiles()).contains("test"));
        Assert.assertTrue(Arrays.asList(abstractEnvironment.getActiveProfiles()).contains("default"));

        int i = reader.loadBeanDefinitions(new ClassPathResource("chapter02-s04.xml"));
        Assert.assertEquals(1, i);

        MyTestBean myTestBean = (MyTestBean) factory.getBean("myTestBean");
        Assert.assertEquals(myTestBean.getTestStr(), "testStr");
    }

    /**
     * 测试下 EncodedResource
     */
    @Test
    public void tes5() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        int i = reader.loadBeanDefinitions(new EncodedResource(new ClassPathResource("chapter02-s01.xml"), Charset.defaultCharset()));
        Assert.assertEquals(1, i);

        MyTestBean myTestBean = (MyTestBean) factory.getBean("myTestBean");
        Assert.assertEquals(myTestBean.getTestStr(), "testStr");
    }

    /**
     * 测试下 InputSource
     * 使用 InputSource，需要把原始的资源包装到方法中，直接调用一个参数的加载方法，需要关闭资源的验证，否则会验证失败
     * 貌似需要手动的关闭xml的验证模式
     * reader.setValidating(false); 或者  reader.setValidationMode(XmlValidationModeDetector.VALIDATION_NONE);
     */
    @Test
    public void tes6() throws IOException {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        ClassPathResource resource = new ClassPathResource("chapter02-s01.xml");
        InputStream inputStream = resource.getInputStream();

        Assert.assertNotNull(inputStream);
//
//      单个参数，需要关闭xml的验证
        InputSource inputSource = new InputSource(inputStream);
        reader.setValidating(false);
//        reader.setValidationMode(XmlValidationModeDetector.VALIDATION_NONE);

        inputSource.setEncoding("UTF-8");

        int i = reader.loadBeanDefinitions(inputSource);

        Assert.assertEquals(1, i);

        MyTestBean myTestBean = (MyTestBean) factory.getBean("myTestBean");
        Assert.assertEquals(myTestBean.getTestStr(), "testStr");
    }

    /**
     * 直接使用Document进行注册
     */
    @Test
    public void tes7() throws IOException, ParserConfigurationException, SAXException {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        ClassPathResource resource = new ClassPathResource("chapter02-s01.xml");
        InputStream inputStream = resource.getInputStream();

//        手动加载出 Document
        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(inputStream);

//        直接通过Document 注册
        int i = reader.registerBeanDefinitions(document, null);
        Assert.assertEquals(1, i);

//        重复注册，则会去重
        i = reader.registerBeanDefinitions(document, resource);
        Assert.assertEquals(0, i);


        MyTestBean myTestBean = (MyTestBean) factory.getBean("myTestBean");
        Assert.assertEquals(myTestBean.getTestStr(), "testStr");
    }

    /**
     * BeanDefinitionDocumentReader
     * <p>
     * XmlReaderContext
     * 手动注册
     */
    @Test
    public void tes8() throws IOException, ParserConfigurationException, SAXException {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

//        准备文档
        ClassPathResource resource = new ClassPathResource("chapter02-s01.xml");
        InputStream inputStream = resource.getInputStream();
//        手动加载出 Document
        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(inputStream);


//        准备资源上下文
//        XmlReaderContext readerContext = reader.createReaderContext(resource);
//       约等价于
        XmlReaderContext readerContext = new XmlReaderContext(resource,
                new FailFastProblemReporter(),
                new EmptyReaderEventListener(),
                new NullSourceExtractor(),
                reader,
                new DefaultNamespaceHandlerResolver(reader.getResourceLoader().getClassLoader()));

//        使用自定义的 BeanDefinition文档阅读器
        MyDefaultBeanDefinitionDocumentReader beanDefinitionDocumentReader = new MyDefaultBeanDefinitionDocumentReader();

//        直接调用注册
        beanDefinitionDocumentReader.registerBeanDefinitions(document, readerContext);
//
        MyTestBean myTestBean = (MyTestBean) factory.getBean("myTestBean");

        Assert.assertEquals(myTestBean.getTestStr(), "testStr");

        inputStream.close();
    }

    /**
     * 使用自定义的BeanDefinitionParserDelegate
     * 使用自定义的事件 加载xml时，会触发 默认头加载事件、beanDefinition定义完成事件、alias注册事件、Import事件
     */
    @Test
    public void tes9() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

//        setEventListener
        reader.setEventListener(new MyReaderEventListener());
        reader.setSourceExtractor(new MySourceExtractor());

        reader.setDocumentReaderClass(MyDefaultBeanDefinitionDocumentReader.class);

        reader.loadBeanDefinitions(new ClassPathResource("chapter02-s06.xml"));

        MyTestBean myTestBean = (MyTestBean) factory.getBean("myTestBean");
        Assert.assertEquals(myTestBean.getTestStr(), "testStr");
        MyTestBean myTestBeanAlias_2 = factory.getBean("myTestBeanAlias_2", MyTestBean.class);
        Assert.assertEquals(myTestBean, myTestBeanAlias_2);
        MyTestBean2 myTestBean2 = factory.getBean("myTestBean2", MyTestBean2.class);
        Assert.assertEquals(myTestBean2.getTestStr(), "testStr2");
    }

}