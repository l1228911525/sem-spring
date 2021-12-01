package pojo;

import org.semspringframework.beans.factory.BeanFactoryAware;
import org.semspringframework.beans.factory.config.BeanFactory;

public class InitBean implements BeanFactoryAware {

    private BeanFactory beanFactory;

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
