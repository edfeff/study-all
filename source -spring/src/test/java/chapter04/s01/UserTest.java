package chapter04.s01;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    /**
     * 自定以标签的解析
     * 1. 遇到自定义标签时  DefaultBeanDefinitionDocumentReader 委托代理类 BeanDefinitionParserDelegate 来解析自定义命名空间的标签
     * 2. 代理类BeanDefinitionParserDelegate抽取出标签的namespace，然后找到此命名空间的 NamespaceHandler
     * 3. 委托 NamespaceHandler 来处理该自定义标签
     * <p>
     * 问题：
     * 1. 自定义的标签何时被加载到容器中的，即上述第二步，代理类是如何找到合适的NamespaceHandler的？
     * 代理类利用 XmlReaderContext 中的成员 NamespaceHandlerResolver 来查找指定的NamespaceHandler，由于XmlBeanDefinitionReader中创建的NamespaceHandlerResolver智能解析默认的命名空间，所以此处我们没有使用XmlBeanFactory，而是使用了一个ApplicationContext
     * <p>
     * 2.NamespaceHandler 是如何将一个《xml》标签解析成BeanDefinition的？
     * NamespaceHandler本身提供了方法 parse ，可以将xml标签解析成BeanDefinition。典型的有 SecurityNamespaceHandler等
     * 不过Spring为了系统的灵活性，并不是完全是使用NamespaceHandler的实现类来完成xml的解析，这里面有一些设计模式的存在。
     * 2.1 使用 NamespaceHandler的实现类 NamespaceHandlerSupport作为工具类，将xml标签和xml标签解析器BeanDefinitionParser的关系映射起来，使得 NamespaceHandler作为xml标签的解析入口，而真正的解析功能则交给了BeanDefinitionParser完成
     */
    @Test
    public void t1() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("chapter04-s01.xml");
        User bean = bf.getBean(User.class);
        Assert.assertEquals("test", bean.getEmail());
        Assert.assertEquals("test", bean.getUserName());
    }
}
