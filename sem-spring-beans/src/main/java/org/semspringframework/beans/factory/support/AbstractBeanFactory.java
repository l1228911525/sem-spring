package org.semspringframework.beans.factory.support;

import org.semspringframework.beans.factory.config.BeanDefinitionRegistry;
import org.semspringframework.beans.factory.config.BeanFactory;

public abstract class AbstractBeanFactory extends SingletonRegistrySupport implements BeanFactory, BeanDefinitionRegistry {
    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public <T> Object getBean(Class<T> beanClazz) {
        return null;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClazz) {
        return null;
    }

    @Override
    public Boolean containBean(String beanName) {
        return null;
    }

    @Override
    public <T> Boolean containBean(Class<T> beanClazz) {
        return null;
    }

    @Override
    public <T> Boolean containBean(String beanName, Class<T> beanClazz) {
        return null;
    }

    @Override
    public Class<?> getType(String beanName) {
        return null;
    }

    public abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
