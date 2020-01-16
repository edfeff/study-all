package chapter05.s01;

import chapter02.s01.MyTestBean;
import org.junit.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.io.ClassPathResource;

import static org.junit.Assert.*;

public class MyXmlBeanFactoryTest {
    /**
     * 循环依赖Bug
     */
    @Test
    public void tes1() {
        MyXmlBeanFactory beanFactory = new MyXmlBeanFactory(new ClassPathResource("chapter05-s01.xml"));
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("m2");
    }

    @Test
    public void tes2() {
        MyXmlBeanFactory beanFactory = new MyXmlBeanFactory(new ClassPathResource("chapter05-s01.xml"));
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("&&&&&&&m2");
    }


    /**
     * 循环依赖检查
     */
    @Test
    public void tes3() {
        MyXmlBeanFactory beanFactory = new MyXmlBeanFactory(new ClassPathResource("chapter05-s02.xml"));
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("p1");
    }

    @Test
    public void tes4() {
        MyXmlBeanFactory beanFactory = new MyXmlBeanFactory(new ClassPathResource("chapter05-s03.xml"));
        beanFactory.registerScope("other", new OtherScope());
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("p1");
        MyTestBean myTestBean2 = (MyTestBean) beanFactory.getBean("p2");
        MyTestBean myTestBean3 = (MyTestBean) beanFactory.getBean("p3");
    }

}