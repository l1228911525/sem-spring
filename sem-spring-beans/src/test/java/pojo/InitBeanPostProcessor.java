package pojo;

import org.semspringframework.beans.factory.BeanPostProcessor;

public class InitBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object PostProcessorBeforeInit(Object bean, String beanName) {

        System.out.println("before");

        return bean;
    }

    @Override
    public Object PostProcessorAfterInit(Object bean, String beanName) {

        System.out.println("after");

        return bean;
    }
}
