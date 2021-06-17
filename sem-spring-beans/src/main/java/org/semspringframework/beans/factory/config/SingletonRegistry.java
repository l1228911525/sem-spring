package org.semspringframework.beans.factory.config;

import org.semspringframework.beans.factory.support.BeanDefinition;

/**
 * a class implement the interface{@link SingletonRegistry} surface the class hold the ability of operator singleton container.
 */
public interface SingletonRegistry {

    /**
     * register singleton bean into container, key is {@param beanName}, value is {@param object}
     */
    public void registerSingleton(String beanName, Object object);

    /**
     * remove singleton object by {@param beanName}
     */
    public void removeSingleton(String beanName);

    /**
     * get singleton bean by {@param beanName}
     */
    public Object getSingleton(String beanName);

    /**
     * get singleton object is created by the factory of {@param objectFactory}
     */
    public Object getSingleton(ObjectFactory objectFactory);

    /**
     * get singleton object by {@param objectFactory} that create singleton bean and {@param beanDefinition}
     */
    public Object getSingleton(BeanDefinition beanDefinition, ObjectFactory objectFactory);

    /**
     * get singleton object by {@param objectFactory} that create singleton bean and {@param beanName}
     */
    public Object getSingleton(String beanName, ObjectFactory objectFactory);

    /**
     * register singletonFactory that create singleton by {@param beanName} and the {@param objectFactory}
     */
    public void registerSingletonFactory(String beanName, ObjectFactory objectFactory);

    /**
     * remove singletonFactory by {@param beanName}
     */
    public void removeSingletonFactory(String beanName);

    /**
     * get singletonFactory by {@param beanName}
     */
    public ObjectFactory getSingletonFactory(String beanName);

}
