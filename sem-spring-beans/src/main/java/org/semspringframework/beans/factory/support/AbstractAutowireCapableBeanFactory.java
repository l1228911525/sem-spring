package org.semspringframework.beans.factory.support;

import org.semspringframework.beans.BeanWrapperImpl;
import org.semspringframework.beans.factory.Aware;
import org.semspringframework.beans.factory.BeanFactoryAware;
import org.semspringframework.beans.factory.BeanPostProcessor;
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

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(beanDefinition.getBeanClass());

        // create bean using the constructor
        Constructor<?> beanConstructor = beanDefinition.getBeanConstructor();

        // the array named 'constructorParameter' load parameter name of constructor orderly
        String[] constructorParameter = beanDefinition.getConstructorParameter();

        // the HashMap'content is the parameter of constructor
        HashMap<String, Object> initParam = beanDefinition.getInitParam();

        List<Object> objectList = new LinkedList<Object>();

        for (String parameterName : constructorParameter) {

            Object parameterObj = initParam.get(parameterName);

            if(parameterObj instanceof RefType) {
                Object bean = getBean(((RefType) parameterObj).getRefName());
                objectList.add(bean);
            }

            else{
                Object newObj = beanWrapper.convert(parameterObj, beanWrapper.getPropertyClass(parameterName));
                objectList.add(newObj);
            }
        }

        try {
            beanWrapper.setObject(beanConstructor.newInstance(objectList.toArray()));
            return beanWrapper;
        } catch (Exception e) {
            throw new IllegalStateException("creating bean named " + beanDefinition.getBeanName() + " happen Exception usring the constructor");
        }

    }

    /**
     * DI(Dependency injection) using set method of target
     */
    @Override
    public BeanWrapperImpl autowireBeanBySet(BeanDefinition beanDefinition, BeanWrapperImpl beanWrapper) {

        Object parameterObject = null;

        HashMap<String, Object> setParam = beanDefinition.getSetParam();

        if(setParam == null)
            return beanWrapper;

        Set<String> keySet = setParam.keySet();

        for (String key : keySet) {

            Object param = setParam.get(key);

            if(param instanceof RefType) {

                parameterObject = getBean(((RefType) param).getRefName());

            }

            else{
                parameterObject = param;
            }

            beanWrapper.setPropertyValue(key, parameterObject);

        }

        return beanWrapper;
    }

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) {

        Object beanWrapper = autowireBeanByConstructor(beanDefinition);

        if(beanDefinition.getSingleton()) {
            registerSingleton(beanDefinition.getBeanName(), ((BeanWrapperImpl)beanWrapper).getObject());
        }

        BeanWrapperImpl beanWrapperSet = autowireBeanBySet(beanDefinition, (BeanWrapperImpl) beanWrapper);

        Object bean = beanWrapperSet.getObject();

        return initializeBean(bean, beanName);
    }

    @Override
    public Object initializeBean(Object bean, String beanName) {

        invokeAwareMethod(bean, beanName);

        // implement method of BeanPostProcessor named PostProcessorBeforeInit
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            bean = beanPostProcessor.PostProcessorBeforeInit(bean, beanName);
        }

        // TODO initializing bean

        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            bean = beanPostProcessor.PostProcessorAfterInit(bean,  beanName);
        }

        return bean;
    }

    private Object invokeAwareMethod(Object bean, String beanName) {

        if(bean instanceof BeanFactoryAware)
            ((BeanFactoryAware) bean).setBeanFactory(this);

        return bean;
    }
}
