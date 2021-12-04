package org.semspringframework.beans.factory.support;

import org.semspringframework.beans.factory.BeanPostProcessor;
import org.semspringframework.beans.factory.config.ListableBeanFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ListableBeanFactory {

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
        Set<String> beanDefinitionNameSet = beanDefinitionMap.keySet();
        return beanDefinitionNameSet.toArray(new String[beanDefinitionNameSet.size()]);
    }

    @Override
    public Integer getBeanDefinitionCounts() {
        return beanDefinitionMap.size();
    }



    public HashMap<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public void setBeanDefinitionMap(HashMap<String, BeanDefinition> beanDefinitionMap) {
        this.beanDefinitionMap = beanDefinitionMap;
    }

    @Override
    public String[] getBeanDefintionNamesForClass(Class<?> beanClass) {
        return new String[0];
    }

    @Override
    public List<String> getBeanNamesForType(Class<?> baseClazz, Boolean rejectSubClass) {

        List<String> beanNames = new LinkedList<>();

        Set<String> keys = this.beanDefinitionMap.keySet();

        for (String key : keys) {
            BeanDefinition beanDefinition = this.beanDefinitionMap.get(key);
            Class<?> beanClass = beanDefinition.getBeanClass();

            if(baseClazz.isAssignableFrom(beanClass) && !rejectSubClass)
                beanNames.add(beanDefinition.getBeanName());
            else if(rejectSubClass && baseClazz.equals(beanClass))
                beanNames.add(beanDefinition.getBeanName());
        }
        return beanNames;
    }
}
