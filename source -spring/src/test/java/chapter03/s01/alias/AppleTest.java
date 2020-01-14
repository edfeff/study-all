package chapter03.s01.alias;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;

public class AppleTest {
    @Test
    public void t1() {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-alias.xml"));
        Apple apple = xmlBeanFactory.getBean("apple", Apple.class);
        Apple apple1 = xmlBeanFactory.getBean("Apple", Apple.class);
        Apple apple2 = xmlBeanFactory.getBean("APPLE", Apple.class);
        Apple pingguo = xmlBeanFactory.getBean("pingguo", Apple.class);
        Assert.assertEquals(apple, apple1);
        Assert.assertEquals(apple, apple2);
        Assert.assertEquals(apple, pingguo);

    }

}