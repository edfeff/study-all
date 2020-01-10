package chapter02.s01;

import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 尝试处理下beans的递归嵌套计数
 * <p>
 * 在处理的xml的前后增加自己的处理,用于扩展 原有的XML解析
 */
public class MyDefaultBeanDefinitionDocumentReader extends DefaultBeanDefinitionDocumentReader {
    Map<Integer, Integer> map = new HashMap<>();
    AtomicInteger count = new AtomicInteger(0);

    @Override
    protected void preProcessXml(Element root) {
        if (map.get(root.hashCode()) == null) {
            map.put(root.hashCode(), count.incrementAndGet());
        }
        System.out.println("level begin :" + map.get(root.hashCode()));

        System.out.println("===========在处理: " + root.getTagName() + " : " + root.hashCode() + " 之前===========");
        System.out.println("命名空间是:" + root.getNamespaceURI());
        System.out.println("属性有:");
        NamedNodeMap attributes = root.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node item = attributes.item(i);
            System.out.println(item.getNodeName() + " : " + item.getNodeValue());
        }
    }

    @Override
    protected void postProcessXml(Element root) {
        System.out.println("level end :" + map.get(root.hashCode()));
        System.out.println("===========在处理: " + root.getTagName() + " : " + root.hashCode() + " 之后===========");

    }

    @Override
    protected BeanDefinitionParserDelegate createDelegate(XmlReaderContext readerContext, Element root, BeanDefinitionParserDelegate parentDelegate) {
        System.out.println("采用自定义的BeanDefinition解析代理器");
        MyBeanDefinitionParserDelegate myBeanDefinitionParserDelegate = new MyBeanDefinitionParserDelegate(readerContext);
        myBeanDefinitionParserDelegate.initDefaults(root, parentDelegate);
        return myBeanDefinitionParserDelegate;
    }
}
