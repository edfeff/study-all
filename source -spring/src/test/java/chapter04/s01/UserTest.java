package chapter04.s01;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void t1() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("chapter04-s01.xml");
        User bean = bf.getBean(User.class);
        Assert.assertEquals("test", bean.getEmail());
        Assert.assertEquals("test", bean.getUserName());
    }
}
