package chapter04.s01;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 将 user的命名空间解析器注册到容器中
 *
 * @author wangpp
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserDefinitionParser());
    }
}
