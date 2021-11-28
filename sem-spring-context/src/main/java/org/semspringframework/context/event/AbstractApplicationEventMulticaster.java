package org.semspringframework.context.event;

import org.semspringframework.beans.factory.BeanFactoryAware;
import org.semspringframework.beans.factory.config.BeanFactory;
import org.semspringframework.beans.factory.support.AbstractBeanFactory;
import org.semspringframework.context.ApplicationEvent;
import org.semspringframework.context.ApplicationListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * the abstract class manager application listener implementing ApplicationEventMulticaster
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    Map<String, ApplicationListener<?>> listenerMap = new HashMap<>();

    public AbstractApplicationEventMulticaster() {
    }

    public AbstractApplicationEventMulticaster(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if(!(beanFactory instanceof AbstractBeanFactory)) {
            throw new IllegalStateException("bean factory does not instance of abstract bean factory");
        }
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        if(null == this.beanFactory) {
            throw new IllegalStateException("bean factory is null");
        }
        return this.beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        listenerMap.put(listener.getClass().getName(), listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        listenerMap.remove(listener.getClass().getName());
    }

    @Override
    public void removeApplicationListenerByType(Class<? extends ApplicationEvent> eventClazz) {
        // TODO
    }

    @Override
    public void removeAllApplicationListeners() {
        listenerMap.clear();
    }

}
