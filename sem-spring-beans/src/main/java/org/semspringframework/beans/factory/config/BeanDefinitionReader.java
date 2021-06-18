package org.semspringframework.beans.factory.config;

/**
 * a class implement the interface{@link BeanDefinitionReader}
 * surface the class hold the ability of loading BeanDefinition from inputStream or fileName
 */
public interface BeanDefinitionReader {

    public BeanDefinitionRegistry getBeanDefinitionRegistry();

    public void setBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry);

    /**
     * load BeanDefinition from location of the configuration file
     */
    public void loadBeanDefinition(String location);

    /**
     * load BeanDefinition from location of a few configuration file
     */
    public void loadBeanDefinition(String... locations);

}
