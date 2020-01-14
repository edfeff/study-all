package chapter03.s01;

import chapter03.s01.constructargs.Person;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.util.Set;

public class ConstructArgsTest {
    /**
     * 测试 constructor-arg
     * 1. 命名参数
     * 2. 索引参数
     */
    @Test
    public void t1() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-constructargs.xml"));
        Person p1 = beanFactory.getBean("p1", Person.class);
        Person p2 = beanFactory.getBean("p2", Person.class);
        Assert.assertTrue(11 == p1.getAge());
        Assert.assertTrue(12 == p2.getAge());
    }

    /**
     * 命名参数
     */
    @Test
    public void t2() {
        RootBeanDefinition person = new RootBeanDefinition(Person.class);

        ConstructorArgumentValues.ValueHolder name = new ConstructorArgumentValues.ValueHolder("ID11", null, "name");
        ConstructorArgumentValues.ValueHolder age = new ConstructorArgumentValues.ValueHolder("11", null, "age");
        ConstructorArgumentValues.ValueHolder email = new ConstructorArgumentValues.ValueHolder("22@qq.com", null, "email");

        person.getConstructorArgumentValues().addGenericArgumentValue(name);
        person.getConstructorArgumentValues().addGenericArgumentValue(age);
        person.getConstructorArgumentValues().addGenericArgumentValue(email);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("person", person);

        Person bean = beanFactory.getBean(Person.class);
        Assert.assertTrue(11 == bean.getAge());
        Assert.assertTrue("ID11".equals(bean.getName()));
        Assert.assertTrue("22@qq.com".equals(bean.getEmail()));

    }

    /**
     * 索引参数
     */
    @Test
    public void t3() {
        RootBeanDefinition person = new RootBeanDefinition(Person.class);

        ConstructorArgumentValues.ValueHolder name = new ConstructorArgumentValues.ValueHolder("ID11");
        ConstructorArgumentValues.ValueHolder age = new ConstructorArgumentValues.ValueHolder("11");
        ConstructorArgumentValues.ValueHolder email = new ConstructorArgumentValues.ValueHolder("22@qq.com");

        person.getConstructorArgumentValues().addIndexedArgumentValue(0, name);
        person.getConstructorArgumentValues().addIndexedArgumentValue(1, age);
        person.getConstructorArgumentValues().addIndexedArgumentValue(2, email);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("person", person);

        Person bean = beanFactory.getBean(Person.class);
        Assert.assertTrue(11 == bean.getAge());
        Assert.assertTrue("ID11".equals(bean.getName()));
        Assert.assertTrue("22@qq.com".equals(bean.getEmail()));

    }

    /**
     * list
     */
    @Test
    public void t4() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-constructargs.xml"));
        Person p3 = beanFactory.getBean("p3", Person.class);
        Assert.assertEquals(3, p3.getFriends().size());
    }

    /**
     * map
     */
    @Test
    public void t5() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-constructargs.xml"));
        Person p3 = beanFactory.getBean("p4", Person.class);
        Assert.assertEquals(2, p3.getMetas().size());
    }

    /**
     * map --null
     */
    @Test
    public void t6() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-constructargs.xml"));
        Person p3 = beanFactory.getBean("p5", Person.class);
        Assert.assertNull(p3.getMetas());
    }

    /**
     * properties
     */
    @Test
    public void t7() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-constructargs.xml"));
        Person p3 = beanFactory.getBean("p6", Person.class);
        Assert.assertEquals(3, p3.getProperties().size());
    }

    /**
     * set
     */
    @Test
    public void t8() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-constructargs.xml"));
        Person p3 = beanFactory.getBean("p7", Person.class);
        Assert.assertEquals(2, p3.getSet().size());
    }

    /**
     * array
     */
    @Test
    public void t9() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-constructargs.xml"));
        Person p3 = beanFactory.getBean("p8", Person.class);
        Assert.assertEquals(3, p3.getArr().length);
    }

    /**
     * qualifier
     */
    @Test
    public void t10() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-constructargs.xml"));
        AbstractBeanDefinition p9 = (AbstractBeanDefinition) beanFactory.getBeanDefinition("p9");
        Set<AutowireCandidateQualifier> qualifiers = p9.getQualifiers();
        Assert.assertNotNull(qualifiers);
        Assert.assertEquals(1, qualifiers.size());
    }
}
