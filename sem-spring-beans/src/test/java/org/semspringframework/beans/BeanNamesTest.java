package org.semspringframework.beans;

import org.semspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.semspringframework.beans.factory.xml.XmlBeanDefinitionReader;
import pojo.Base;
import pojo.SubClass;

import java.util.List;

public class BeanNamesTest {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinition("myspringmyspring.xml");

        SubClass bean = beanFactory.getBean(SubClass.class);

        List<String> beanNamesForType = beanFactory.getBeanNamesForType(Base.class, false);

        System.out.println(beanNamesForType);
    }

}
