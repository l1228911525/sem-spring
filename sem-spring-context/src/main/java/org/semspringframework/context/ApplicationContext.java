package org.semspringframework.context;

import org.semspringframework.beans.factory.config.BeanFactory;
import org.semspringframework.beans.factory.config.ListableBeanFactory;

/**
 * a class implement the interface{@link ApplicationContext} surface the class hold a few ability of managing BeanFactory,
 * publishing application event and managing the life cycle of bean so on.
 */
public interface ApplicationContext extends ListableBeanFactory, ApplicationEventPublisher {

    /**
     * get the application name
     * @return
     */
    public String getApplicationName();

    /**
     * get the BeanFactory the application context manager
     * @return
     */
    public ListableBeanFactory getBeanFactory();

    /**
     * judge BeanFactory is null or not
     * true: BeanFactory is null, false: BeanFactory is not null
     */
    public Boolean hasBeanFactory();

    /**
     * destroy BeanFactory
     */
    public void destroyBeanFactory();

    /**
     *  refresh application context: load definition, run BeanFactoryPostProcessor, initialize BeanPostProcessor,
     *  initialize Application listener, create singleton bean, finish refresh
     */
    public void refresh();

    /**
     * BeanDefinitionReader load BeanDefinition from xml file or annotation and put into BeanFactory.
     * the function return the completed bean factory.
     * @return
     */
    public BeanFactory obtainBeanFactory();

    /**
     * register BeanPostProcess to application context
     */
    public void registerBeanPostProcessors(BeanFactory beanFactory);

    /**
     * initialize event multicaster of application event.
     */
    public void initApplicationEventMulticaster();

    /**
     * register application listener to application context
     */
    public void registerListener();

    /**
     * create singleton bean and put into singleton cache
     */
    public void finishSingletonBean();

    /**
     * finish refresh: publish refreshed event
     */
    public void finishRefresh();
}
