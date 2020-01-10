package chapter02.s01.register;

import chapter02.s01.MyTestBean;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;

public class StudyBeanDefinitionDocumentReaderTest {
    /**
     * 测试了
     * 使用代理将xml解析成BeanDefinition的过程
     * 将BeanDefinition注册到BeanFactory
     * <p>
     * 从xml到BeanDefinition的流程大致如下:
     * 1. 使用 Resource 接口来获取 xml的数据流 ,并包装成 InputSource
     * 2. 使用 DocumentLoader 将 InputSource加工成 Document
     * 3. 使用 BeanDefinitionDocumentReader 将 Document 中的xml标签片段进行加工成 BeanDefinitionHolder
     * 3.1 BeanDefinitionDocumentReader 仅仅负责整个标签解析的流程控制,而读取解析属性\子标签则委托给了 BeanDefinitionParserDelegate
     * 4. 使用工具类 BeanDefinitionReaderUtils 将BeanDefinitionHolder中的 BeanDefinition 注册到BeanFactory中
     * <p>
     * --处理流程
     * ----------------------XmlBeanDefinitionReader
     * --      |                          |
     * -----Resource-----InputSource----BeanDefinitionDocumentReader---------------BeanDefinitionParserDelegate-
     * ------ |             |               |            |                                |
     * --[XML]-->[Resource]-->[InputSource]-->[Document]-->[<bean><beans><alias><import>]-->[BeanDefinitionHolder]------>[BeanDefinition]--->...
     * ------------------------------------------------------------------------------------------------------------------------------
     * <p>
     * 可以发现Spring提供了一些自定义的扩展点:
     * 1.xml解析扩展点
     * 1.1 可以继承 DefaultBeanDefinitionDocumentReader,实现在xml解析前后的拦截方法  postProcessXml 和 preProcessXml
     * 2. 事件扩展点
     * 2.1 spring在解析import beans alias bean完成时,会会发送相关的事件,只要将事件监听器注册到XmlBeanDefinitionReader时,即可完成扩展
     */
    @Test
    public void te1() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

//        beanDefinitionReader.setEventListener();

//        1. 注入自定义的 BeanDefinitionDocumentReader
        beanDefinitionReader.setDocumentReaderClass(StudyBeanDefinitionDocumentReader.class);

        int i = beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("chapter02-s08.xml"));

        assertEquals(1, i);

        MyTestBean bean = beanFactory.getBean(MyTestBean.class);

        assertEquals("x2", bean.getTestStr());
    }
}