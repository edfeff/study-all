package chapter02.s01;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.parsing.*;
import org.springframework.beans.factory.xml.DocumentDefaultsDefinition;
import org.springframework.core.io.Resource;

public class MyReaderEventListener implements ReaderEventListener {
    @Override
    public void defaultsRegistered(DefaultsDefinition defaultsDefinition) {
        System.out.println();
        System.out.println("MyReaderEventListener : " + "defaultsRegistered");
        System.out.println(defaultsDefinition.getClass().toString());
        if (defaultsDefinition instanceof DocumentDefaultsDefinition) {
            DocumentDefaultsDefinition documentDefaultsDefinition = (DocumentDefaultsDefinition) defaultsDefinition;
            System.out.println("getAutowire:  " + documentDefaultsDefinition.getAutowire());
            System.out.println("getAutowireCandidates:  " + documentDefaultsDefinition.getAutowireCandidates());
            System.out.println("getDependencyCheck:  " + documentDefaultsDefinition.getDependencyCheck());
            System.out.println("getDestroyMethod:  " + documentDefaultsDefinition.getDestroyMethod());
            System.out.println("getInitMethod:  " + documentDefaultsDefinition.getInitMethod());
            System.out.println("getLazyInit:  " + documentDefaultsDefinition.getLazyInit());
            System.out.println("getMerge:  " + documentDefaultsDefinition.getMerge());
        }
        System.out.println();
    }

    @Override
    public void componentRegistered(ComponentDefinition componentDefinition) {
        System.out.println();
        System.out.println("MyReaderEventListener : " + "componentRegistered");
        System.out.println("getName: " + componentDefinition.getName());
        System.out.println("getDescription: " + componentDefinition.getDescription());
        BeanDefinition[] beanDefinitions = componentDefinition.getBeanDefinitions();
        System.out.println("componentDefinition.getBeanDefinitions");
        for (BeanDefinition beanDefinition : beanDefinitions) {
            System.out.println(beanDefinition.toString());
        }
        BeanReference[] beanReferences = componentDefinition.getBeanReferences();
        System.out.println("componentDefinition.getBeanReferences");
        for (BeanReference beanReference : beanReferences) {
            System.out.println(beanReference.getBeanName());
        }
        BeanDefinition[] innerBeanDefinitions = componentDefinition.getInnerBeanDefinitions();
        System.out.println("componentDefinition.getInnerBeanDefinitions()");
        for (BeanDefinition innerBeanDefinition : innerBeanDefinitions) {
            System.out.println(innerBeanDefinition.getBeanClassName());
        }
        System.out.println();
    }

    @Override
    public void aliasRegistered(AliasDefinition aliasDefinition) {
        System.out.println();

        System.out.println("MyReaderEventListener : " + "aliasRegistered");
        System.out.println("getBeanName: " + aliasDefinition.getBeanName());
        System.out.println("getAlias: " + aliasDefinition.getAlias());
        System.out.println();

    }

    @Override
    public void importProcessed(ImportDefinition importDefinition) {
        System.out.println();
        System.out.println("MyReaderEventListener : " + "importProcessed");
        System.out.println("getImportedResource: " + importDefinition.getImportedResource());
        Resource[] actualResources = importDefinition.getActualResources();
        System.out.println("importDefinition.getActualResources");
        for (Resource actualResource : actualResources) {
            System.out.println(actualResource.getDescription());
        }
        System.out.println();

    }
}
