package org.semspringframework.beans.factory.support;

import org.semspringframework.beans.factory.config.BeanDefinitionReader;
import org.semspringframework.beans.factory.config.BeanDefinitionRegistry;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public AbstractBeanDefinitionReader() {
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public void setBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public void loadBeanDefinition(String location) {

        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource resource = resourceLoader.getResource(location);

        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinition(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("fail to get the inputstream of xml file");
        }

    }

    @Override
    public void loadBeanDefinition(String... locations) {

        if(locations == null)
            return;

        for (String loc : locations) {
            loadBeanDefinition(loc);
        }
    }

    protected abstract void doLoadBeanDefinition(InputStream inputStream);

}
