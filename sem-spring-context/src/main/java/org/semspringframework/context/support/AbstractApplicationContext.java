package org.semspringframework.context.support;

import org.semspringframework.beans.factory.config.BeanFactory;
import org.semspringframework.context.ApplicationContext;
import org.semspringframework.context.ApplicationEvent;

public abstract class AbstractApplicationContext implements ApplicationContext {

    @Override
    public void refresh() {

    }

    @Override
    public BeanFactory obtainBeanFactory() {
        refreshBeanFactory();
        return getBeanFactory();
    }

    /**
     * refresh BeanFactory: destroy existing BeanFactory, create new BeanFactory and load BeanDefinition put into BeanFactory
     * according to the distinct of meta information(xml file or annotation), the method has two difference implement.
     */
    protected abstract void refreshBeanFactory();

    @Override
    public void registerBeanPostProcessors(BeanFactory beanFactory) {

    }

    @Override
    public void initApplicationEventMulticaster() {

    }

    @Override
    public void registerListener() {

    }

    @Override
    public void finishSingletonBean() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void publishEvent(ApplicationEvent event) {

    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> Object getBean(Class<T> beanClazz) {
        return getBeanFactory().getBean(beanClazz);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClazz) {
        return getBeanFactory().getBean(beanName, beanClazz);
    }

    @Override
    public Boolean containBean(String beanName) {
        return getBeanFactory().containBean(beanName);
    }

    @Override
    public <T> Boolean containBean(Class<T> beanClazz) {
        return getBeanFactory().containBean(beanClazz);
    }

    @Override
    public <T> Boolean containBean(String beanName, Class<T> beanClazz) {
        return getBeanFactory().containBean(beanName, beanClazz);
    }

    @Override
    public Class<?> getType(String beanName) {
        return getBeanFactory().getType(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanDefintionNamesForClass(Class<?> beanClass) {
        return getBeanFactory().getBeanDefintionNamesForClass(beanClass);
    }

    @Override
    public Integer getBeanDefinitionCounts() {
        return getBeanFactory().getBeanDefinitionCounts();
    }
}
