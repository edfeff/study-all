package chapter03.s01;


import chapter03.s01.replace.Fish;
import chapter03.s01.replace.Human;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.ReplaceOverride;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class ReplaceMethodTest {
    /**
     * 测试 replaced-metho
     */
    @Test
    public void t1() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("chapter03-replace.xml"));
        Human bean = beanFactory.getBean(Human.class);
        Assert.assertEquals(Fish.class.getName(), bean.kind());
    }

    /**
     * replaced-method 编程的基本流程
     */
    @Test
    public void t2() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        Fish fish = new Fish();
        beanFactory.registerSingleton("fish", fish);

        RootBeanDefinition human = new RootBeanDefinition(Human.class);
        ReplaceOverride replaceOverride = new ReplaceOverride("kind", "fish");
        human.getMethodOverrides().addOverride(replaceOverride);
        beanFactory.registerBeanDefinition("human", human);

        Human bean = beanFactory.getBean(Human.class);
        Assert.assertEquals(Fish.class.getName(), bean.kind());


    }
}
