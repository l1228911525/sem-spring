package org.semspringframework.beans.factory.config;

import org.semspringframework.beans.BeanWrapperImpl;
import org.semspringframework.beans.factory.support.BeanDefinition;

/**
 * a class implement the interface surface the class hold the ability of DI(Dependency injection).
 * because the interface is inheriting {@link BeanFactory}, the interface is container definitely.
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * the method create and autowire a bean by constructor using BeanDefinition
     */
    public Object autowireBeanByConstructor(BeanDefinition beanDefinition);

    /**
     * autowire a bean by the function named 'set'
     */
    public BeanWrapperImpl autowireBeanBySet(BeanDefinition beanDefinition, BeanWrapperImpl beanWrapper);

    /**
     * initialize bean after the method named 'autowireBeanBySet'.
     * Operation include executing the method of class named BeanPostProcessor and the method of class named Aware.
     */
    public Object initializeBean(Object bean, String beanName);

}
