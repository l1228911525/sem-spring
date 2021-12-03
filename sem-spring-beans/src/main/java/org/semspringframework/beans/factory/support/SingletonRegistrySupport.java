package org.semspringframework.beans.factory.support;

import org.semspringframework.beans.factory.config.ObjectFactory;
import org.semspringframework.beans.factory.config.SingletonRegistry;

import java.util.HashMap;

/**
 * the class is a implementation of the interface {@link SingletonRegistry}.
 */
public class SingletonRegistrySupport implements SingletonRegistry {

    private HashMap<String, Object> singletonMap = new HashMap<>();

    private HashMap<String, ObjectFactory> objectFactoryMap = new HashMap<>();

    @Override
    public void registerSingleton(String beanName, Object object) {
        if(singletonMap.containsKey(beanName)) {
            throw new IllegalStateException("the beanName: " + beanName + " has existed, so the singleton bean named "+ beanName+" is't registered into singletonMap");
        }
        singletonMap.put(beanName, object);
    }

    @Override
    public void removeSingleton(String beanName) {
        singletonMap.remove(beanName);
    }

    public Object getSingleton(String beanName) {

        Object result = null;

        result = singletonMap.get(beanName);

        if(null == result) {
            ObjectFactory objectFactory = objectFactoryMap.get(beanName);

            result = objectFactory != null ? objectFactory.getObject() : null;

            if(null != result)
                registerSingleton(beanName, result);
        }

        return result;
    }

    @Override
    public Object getSingleton(ObjectFactory objectFactory) {
        return objectFactory.getObject();
    }

    @Override
    public Object getSingleton(BeanDefinition beanDefinition, ObjectFactory objectFactory) {
        return null;
    }

    @Override
    public Object getSingleton(String beanName, ObjectFactory objectFactory) {
        Object object = objectFactory.getObject();

        registerSingleton(beanName, object);

        return object;
    }

    @Override
    public void registerSingletonFactory(String beanName, ObjectFactory objectFactory) {
        if(objectFactoryMap.containsKey(beanName)) {
            throw new IllegalStateException("the objectFactoryName: " + beanName + " has existed, so the singletonFactory named "+ beanName+" is't registered into objectFactoryMap");
        }

        objectFactoryMap.put(beanName, objectFactory);
    }

    @Override
    public void removeSingletonFactory(String beanName) {
        objectFactoryMap.remove(beanName);
    }

    @Override
    public ObjectFactory getSingletonFactory(String beanName) {
        return objectFactoryMap.get(beanName);
    }
}
