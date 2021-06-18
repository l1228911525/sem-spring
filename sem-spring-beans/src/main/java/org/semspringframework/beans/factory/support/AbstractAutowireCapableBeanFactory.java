package org.semspringframework.beans.factory.support;

import org.semspringframework.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /**
     * DI(Dependency injection) in invoking constructor
     */
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

    /**
     * DI(Dependency injection) using set method of target
     */
    @Override
    public Object autowireBeanBySet(BeanDefinition beanDefinition, Object target) {

        Object parameterObject = null;

        String setMethodName = null;

        HashMap<String, Object> setParam = beanDefinition.getSetParam();

        if(setParam == null)
            return target;

        Set<String> keySet = setParam.keySet();

        for (String key : keySet) {

            Object param = setParam.get(key);

            if(param instanceof RefType) {

                parameterObject = getBean(((RefType) param).getRefName());

            }

            else{
                parameterObject = param;
            }

            setMethodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);

            try {
                Method method = beanDefinition.getBeanClass().getDeclaredMethod(setMethodName, parameterObject.getClass());

                method.invoke(target, parameterObject);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new IllegalStateException("fail to invoke the method named "+setMethodName+" of target");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalStateException("fail to invoke the method named "+setMethodName+" of target");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new IllegalStateException("fail to invoke the method named "+setMethodName+" of target");
            }

        }

        return target;
    }

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) {

        Object constructorObj = autowireBeanByConstructor(beanDefinition);

        return autowireBeanBySet(beanDefinition, constructorObj);
    }
}
