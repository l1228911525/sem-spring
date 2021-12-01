package org.semspringframework.beans;

import org.semspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.semspringframework.beans.factory.xml.XmlBeanDefinitionReader;
import pojo.InitBean;
import pojo.InitBeanPostProcessor;

public class InitializationBeanTest {

    public static void main(String[] args) {

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinition("myspringmyspring.xml");

        InitBeanPostProcessor initBeanPostProcessor = factory.getBean(InitBeanPostProcessor.class);

        factory.addBeanPostProcess(initBeanPostProcessor);

        InitBean initBean = factory.getBean(InitBean.class);

        System.out.println(initBean);

        System.out.println(initBean.getBeanFactory());
    }

}
