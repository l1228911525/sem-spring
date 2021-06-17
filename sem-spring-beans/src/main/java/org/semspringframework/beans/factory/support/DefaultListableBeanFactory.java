package org.semspringframework.beans.factory.support;

import java.util.HashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory {

    private HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        if(beanDefinitionMap.containsKey(beanName))
            throw new IllegalStateException("the beanDefinition named "+beanName + " has existed, so registering beanDefinition is failure");
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanDefinitionName) {
        return beanDefinitionMap.get(beanDefinitionName);
    }

    @Override
    public HashMap<String, BeanDefinition> getAllBeanDefinition() {
        return beanDefinitionMap;
    }

    @Override
    public Boolean containsBeanDefinition(String beanDefinitionName) {
        return beanDefinitionMap.containsKey(beanDefinitionName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return (String[]) beanDefinitionMap.keySet().toArray();
    }

    @Override
    public Integer getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }
}
