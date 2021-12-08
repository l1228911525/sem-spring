package org.semspringframework.aop.framework;

import org.semspringframework.beans.factory.BeanFactoryAware;
import org.semspringframework.beans.factory.BeanPostProcessor;
import org.semspringframework.beans.factory.config.BeanFactory;
import org.semspringframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.List;

/**
 * aop bean post processor, generate proxy object if  need
 */
public class AopBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public Object PostProcessorBeforeInit(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object PostProcessorAfterInit(Object bean, String beanName) {

        AdviceConfig adviceConfig = (AdviceConfig)beanFactory.getBean("adviceConfig");

        List<BeanAdvice> beanAdviceList = adviceConfig.getBeanAdvice(bean.getClass(), beanFactory);

        if(beanAdviceList.size() == 0)
            return bean;

        return AopUtils.getProxy(bean, beanAdviceList);

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {

        this.beanFactory = (DefaultListableBeanFactory) beanFactory;

    }
}
