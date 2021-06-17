package org.semspringframework.beans.factory.config;

import org.semspringframework.beans.factory.support.BeanDefinition;

import java.util.HashMap;

/**
 * a class implement the interface surface the class hold the ability of operator BeanDefinition
 */
public interface BeanDefinitionRegistry {

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * get BeanDefinition by 'beanDefinitionName'
     */
    public BeanDefinition getBeanDefinition(String beanDefinitionName);

    /**
     * return all BeanDefinition
     */
    public HashMap<String, BeanDefinition> getAllBeanDefinition();

    /**
     * judge the registry contain the BeanDefinition named 'beanDefinitionName' or not
     */
    public Boolean containsBeanDefinition(String beanDefinitionName);

    /**
     * getting name of BeanDefinitions
     */
    public String[] getBeanDefinitionNames();

    /**
     * getting count of BeanDefinitions
     */
    public Integer getBeanDefinitionCount();

}
