package org.semspringframework.beans.factory;

import org.semspringframework.beans.factory.config.BeanFactory;

public interface BeanFactoryAware extends Aware {

    public void setBeanFactory(BeanFactory beanFactory);

}
