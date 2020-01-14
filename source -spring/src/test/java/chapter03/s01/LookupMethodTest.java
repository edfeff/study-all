package chapter03.s01;

import chapter03.s01.lookup.Animal;
import chapter03.s01.lookup.Cat;
import chapter03.s01.lookup.Zoo;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.LookupOverride;
import org.springframework.beans.factory.support.MethodOverrides;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class LookupMethodTest {
    /**
     * 测试lookup-method
     * 可以将一个方法的返回值传给指定的方法,作为该方法的返回值
     */
    @Test
    public void t1() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-looup.xml"));
        Zoo zoo = beanFactory.getBean("zoo", Zoo.class);
        Animal animal = zoo.getAnimal();
        animal.say();
    }

    /**
     * lookup-method的执行的基本代码
     */
    @Test
    public void t2() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //1 注册 cat Bean
        Cat cat = new Cat();
        beanFactory.registerSingleton("cat", cat);

        //2 定义一个抽象类的BeanDefinition Zoo
        RootBeanDefinition zooBeanDefinition = new RootBeanDefinition(Zoo.class);

        //3 配置Zoo需要lookup的方法
        LookupOverride getAnimalLookup = new LookupOverride("getAnimal", "cat");
        zooBeanDefinition.getMethodOverrides().addOverride(getAnimalLookup);

        //4 注册该BeanDefinition
        beanFactory.registerBeanDefinition("zoo", zooBeanDefinition);

        //5 获取抽象类的实例对象
        Zoo bean = beanFactory.getBean(Zoo.class);

        //6 测试
        Animal animal = bean.getAnimal();
        animal.say();

    }
}
