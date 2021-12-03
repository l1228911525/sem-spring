package org.semspringframework.beans.factory.config;

import java.util.List;

/**
 * a class implement the interface surface the class hold the ability of listing the definition name
 * because the interface inherit {@link BeanFactory}, the interface is container definitely
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * getting name of all BeanDefinitions
     */
    public String[] getBeanDefinitionNames();

    /**
     * getting name of all BeanDefinitions by class
     */
    public String[] getBeanDefintionNamesForClass(Class<?> beanClass);

    /**
     * getting the count of BeanDefinition
     */
    public Integer getBeanDefinitionCounts();

    /**
     * getting bean name set according to base class
     * @param baseClazz
     * @param rejectSubClass true: only obtain name set of baseClazz'bean,
     *                       false: obtain name set of baseClazz and its subclass'bean
     * @return bean name set
     */
    public List<String> getBeanNamesForType(Class<?> baseClazz, Boolean rejectSubClass);

}
