package org.semspringframework.context.support;

import org.semspringframework.beans.factory.config.ListableBeanFactory;
import org.semspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.semspringframework.beans.factory.xml.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    private String[] locations;

    public ClassPathXmlApplicationContext() {

    }

    public ClassPathXmlApplicationContext(String...locations) {
        this.locations = locations;
        refresh();
    }

    public void setLocations(String...locations) {
        this.locations = locations;
    }

    @Override
    public String getApplicationName() {
        return "";
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public Boolean hasBeanFactory() {
        return this.beanFactory != null;
    }

    @Override
    public void destroyBeanFactory() {
        this.beanFactory = null;
    }

    @Override
    protected void refreshBeanFactory() {

        if(this.locations == null)
            return;

        if(hasBeanFactory()) {
            destroyBeanFactory();
        }

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinition(locations);

        this.beanFactory = beanFactory;
    }
}
