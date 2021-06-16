package org.semspringframework.beans.factory.config;

/**
 * the interface is in the top level and a base class of all container.
 * a class implement the interface surface it is a container
 */
public interface BeanFactory {

    /**
     * get bean from container through the bean name
     */
    public Object getBean(String beanName);

    /**
     * get bean from container through the bean class
     */
    public <T> Object getBean(Class<T> beanClazz);

    /**
     * get bean from container through the bean name and the bean class
     */
    public <T> T getBean(String beanName, Class<T> beanClazz);

    /**
     * determine whether the container contains a bean named 'beanName'
     */
    public Boolean containBean(String beanName);

    /**
     * determine whether the container contains a bean what its class is 'beanClazz'
     */
    public <T> Boolean containBean(Class<T> beanClazz);

    /**
     * determine whether the container contains a bean what its class is 'beanClazz' and its name is 'beanName'
     */
    public <T> Boolean containBean(String beanName, Class<T> beanClazz);

    /**
     * getting class of a bean named 'beanName'
     */
    public Class<?> getType(String beanName);

}
