package chapter03.s01;

import chapter02.s01.MyTestBean;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;

public class BeanDefinitionMainTest {
    /**
     * <meta>
     * 的元素可以使用 BeanDefinition 的 getAttribute获取
     */
    @Test
    public void t1() {
        // BeanDefinition
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-s01.xml"));
        BeanDefinition myTestBean = beanFactory.getBeanDefinition("myTestBean");
        String author = (String) myTestBean.getAttribute("author");
        assertEquals(author, "wpp");
    }

    @Test
    public void t2() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setAttribute("author", "wpp");
        beanDefinition.setBeanClass(MyTestBean.class);
        beanFactory.registerBeanDefinition("MyTestBean", beanDefinition);

        BeanDefinition myTestBean = beanFactory.getBeanDefinition("MyTestBean");
        String author = (String) myTestBean.getAttribute("author");
        assertEquals(author, "wpp");

        MyTestBean bean = beanFactory.getBean(MyTestBean.class);
        assertEquals("testStr", bean.getTestStr());
    }
}