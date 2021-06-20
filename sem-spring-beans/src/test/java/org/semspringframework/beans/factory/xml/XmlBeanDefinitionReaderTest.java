package org.semspringframework.beans.factory.xml;

import org.junit.Test;
import org.semspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

public class XmlBeanDefinitionReaderTest {

    @Test
    public void test() throws Exception{
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinition("myspringmyspring.xml");

        Object test = factory.getBean("person");

        System.out.println(test);
    }

}
