package org.semspringframework.beans.factory;

/**
 * BeanPostProcessor is in the life cycle, BeanFactory transfer PostProcessorBeforeInit before creating bean,
 * BeanFactory transfer PostProcessorAfterInit if BeanFactory create bean successfully
 */
public interface BeanPostProcessor {

    public Object PostProcessorBeforeInit(Object bean, String beanName);

    public Object PostProcessorAfterInit(Object bean, String beanName);

}
