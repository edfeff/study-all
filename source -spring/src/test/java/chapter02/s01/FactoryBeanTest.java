package chapter02.s01;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import util.ReflectUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FactoryBeanTest {
    /**
     * 这就是普通Bean和FactoryBean的区别
     * <p>
     * <p>
     * FactoryBean获取的是他生产的Bean,如果需要获取工厂 对象,则需要在Bean名字前加上&符号
     */

    @Test
    public void t1() {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("chapter02-s09.xml"));
        Object myTestBean = factory.getBean("myTestBean");
        System.out.println(myTestBean); //chapter02.s01.MyTestBean@7225790e
        Object bean = factory.getBean("&myTestBean");
        System.out.println(bean);//chapter02.s01.MyTestBeanFactory@54a097cc
    }

    @Test
    public void t2() throws Exception {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("chapter02-s09.xml"));
        Object myTestBean = factory.getBean("myTestBean");
        System.out.println(myTestBean); //chapter02.s01.MyTestBean@7225790e
        MyTestBeanFactory factoryBean = (MyTestBeanFactory) factory.getBean("&myTestBean");
        System.out.println(factoryBean);//chapter02.s01.MyTestBeanFactory@54a097cc

        Assert.assertTrue(factoryBean.isSingleton());

//        直接调用FactoryBean的构造bean方法,并不能保证一定是单例的,因为取决于FactoryBean的实现
        MyTestBean object = factoryBean.getObject();

        Assert.assertNotEquals(myTestBean, object);
    }

    /**
     * 测试下FactoryBean的BeanDefinition是什么情况
     *
     * @throws Exception
     */
    @Test
    public void t3() throws Exception {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("chapter02-s09.xml"));
//        String[] beanDefinitionNames = factory.getBeanDefinitionNames();
        BeanDefinition beanDefinition = factory.getBeanDefinition("myTestBean");
        ReflectUtils.showAllGet(beanDefinition);
    }

}
