package org.semspringframework.beans.factory.support;

import org.semspringframework.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    @Override
    public Object autowireBeanByConstructor(BeanDefinition beanDefinition) {

        // create bean using the constructor
        Constructor<?> beanConstructor = beanDefinition.getBeanConstructor();

        // the array named 'constructorParameter' load parameter name of constructor orderly
        String[] constructorParameter = beanDefinition.getConstructorParameter();

        // the HashMap'content is the parameter of constructor
        HashMap<String, Object> initParam = beanDefinition.getInitParam();

        List<Object> objectList = new LinkedList<>();

        for (String parameterName : constructorParameter) {

            Object parameterObj = initParam.get(parameterName);

            if(parameterObj instanceof RefType) {
                Object bean = getBean(((RefType) parameterObj).getRefName());
                objectList.add(bean);
            }

            else{
                objectList.add(parameterObj);
            }
        }

        try {
            return beanConstructor.newInstance(objectList.toArray());
        } catch (Exception e) {
            throw new IllegalStateException("creating bean named " + beanDefinition.getBeanName() + " happen Exception usring the constructor");
        }

    }

    @Override
    public Object autowireBeanBySet(BeanDefinition beanDefinition, Object object) {
        return object;
    }

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) {

        Object constructorObj = autowireBeanByConstructor(beanDefinition);

        return autowireBeanBySet(beanDefinition, constructorObj);
    }
}
