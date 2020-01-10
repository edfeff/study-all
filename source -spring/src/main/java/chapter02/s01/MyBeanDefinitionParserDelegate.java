package chapter02.s01;

import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.DocumentDefaultsDefinition;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Element;

public class MyBeanDefinitionParserDelegate extends BeanDefinitionParserDelegate {
    /**
     * Create a new BeanDefinitionParserDelegate associated with the supplied
     * {@link XmlReaderContext}.
     *
     * @param readerContext
     */
    public MyBeanDefinitionParserDelegate(XmlReaderContext readerContext) {
        super(readerContext);
    }

    @Override
    protected void populateDefaults(DocumentDefaultsDefinition defaults, DocumentDefaultsDefinition parentDefaults, Element root) {
        log("start populateDefaults");
        super.populateDefaults(defaults, parentDefaults, root);
        log("end populateDefaults");
    }

    public void log(String msg) {
        System.out.println(MyBeanDefinitionParserDelegate.class.getName() + " : " + msg);
    }
}

