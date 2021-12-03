package org.semspringframework.context.support;

import org.semspringframework.beans.factory.BeanPostProcessor;
import org.semspringframework.beans.factory.config.BeanFactory;
import org.semspringframework.beans.factory.config.ListableBeanFactory;
import org.semspringframework.beans.factory.support.BeanDefinition;
import org.semspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.semspringframework.context.ApplicationContext;
import org.semspringframework.context.ApplicationEvent;
import org.semspringframework.context.ApplicationListener;
import org.semspringframework.context.event.ContextRefreshEvent;
import org.semspringframework.context.event.SimpleApplicationEventMulticaster;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractApplicationContext implements ApplicationContext {

    private SimpleApplicationEventMulticaster eventMulticaster;

    @Override
    public void refresh() {

        // initialize and obtain BeanFactory
        BeanFactory beanFactory = obtainBeanFactory();

        // register BeanPostProcessor
        registerBeanPostProcessors((DefaultListableBeanFactory) beanFactory);

        // initialize ApplicationEventMulticaster
        initApplicationEventMulticaster();

        // register listener to ApplicationEventMulticaster
        registerListener((ListableBeanFactory) beanFactory);

        // generate singleton bean in order to BeanFactory
        finishSingletonBean((DefaultListableBeanFactory) beanFactory);

        // finish refresh
        finishRefresh();

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
    public void registerBeanPostProcessors(DefaultListableBeanFactory beanFactory) {

        if(null == beanFactory)
            throw new IllegalStateException("the param beanFactory is null, so can't register BeanPostProcessor");

        List<String> beanPostProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, false);

        for (String beanPostProcessorName : beanPostProcessorNames) {

            BeanPostProcessor beanPostProcessor = (BeanPostProcessor)getBean(beanPostProcessorName);

            beanFactory.addBeanPostProcess(beanPostProcessor);

        }

    }

    @Override
    public void initApplicationEventMulticaster() {
        if(this.eventMulticaster != null)
            throw new IllegalStateException("event multicaster is not null, so can't initialize application event multicaster");
        this.eventMulticaster = new SimpleApplicationEventMulticaster();
    }

    @Override
    public void registerListener(ListableBeanFactory beanFactory) {

        if(null == beanFactory)
            throw new IllegalStateException("the param beanfactory is null, so can't register BeanPostProcessor");

        List<String> listenerNames = beanFactory.getBeanNamesForType(ApplicationListener.class, false);

        for (String listenerName : listenerNames) {
            ApplicationListener listener = (ApplicationListener)beanFactory.getBean(listenerName);
            getEventMulticaster().addApplicationListener(listener);
        }
    }

    @Override
    public void finishSingletonBean(DefaultListableBeanFactory beanFactory) {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        HashMap<String, BeanDefinition> beanDefinitionMap = beanFactory.getBeanDefinitionMap();

        for (String beanDefinitionName : beanDefinitionNames) {

            BeanDefinition beanDefinition = beanDefinitionMap.get(beanDefinitionName);

            if(beanDefinition.getSingleton()) {
                beanFactory.getBean(beanDefinition.getBeanName());
            }
        }
    }

    @Override
    public void finishRefresh() {
        publishEvent(new ContextRefreshEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        getEventMulticaster().multicastEvent(event);
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

    @Override
    public List<String> getBeanNamesForType(Class<?> baseClazz, Boolean rejectSubClass) {
        return getBeanFactory().getBeanNamesForType(baseClazz, rejectSubClass);
    }

    public SimpleApplicationEventMulticaster getEventMulticaster() {
        return eventMulticaster;
    }


}
