package org.semspringframework.beans.factory.config;

import org.semspringframework.beans.BeanWrapperImpl;
import org.semspringframework.beans.factory.support.BeanDefinition;

/**
 * a class implement the interface surface the class hold the ability of DI(Dependency injection).
 * because the interface is inheriting {@link BeanFactory}, the interface is container definitely.
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * create and autowire a bean by constructor using BeanDefinition
     */
    public Object autowireBeanByConstructor(BeanDefinition beanDefinition);

    /**
     * autowire a bean by the function of 'set'
     */
    public BeanWrapperImpl autowireBeanBySet(BeanDefinition beanDefinition, BeanWrapperImpl beanWrapper);

}
