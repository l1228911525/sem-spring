package org.semspringframework.beans.factory.support;

import org.semspringframework.beans.factory.config.BeanDefinitionRegistry;
import org.semspringframework.beans.factory.config.BeanFactory;
import org.semspringframework.beans.factory.config.ObjectFactory;
import org.semspringframework.beans.factory.config.AutowireCapableBeanFactory;

import java.util.HashMap;
import java.util.Set;

/**
 * the abstract class inherit {@link SingletonRegistrySupport} surface it hold the ability of singleton container,
 * it also implement {@link BeanDefinitionRegistry} surface it can operator {@link BeanDefinition},
 * but {@link AbstractBeanFactory} doesn't implement those methods of {@link BeanDefinitionRegistry}.
 */
public abstract class AbstractBeanFactory extends SingletonRegistrySupport implements BeanFactory, BeanDefinitionRegistry {

    @Override
    public Object getBean(String beanName) {

        return doGetBean(beanName, null);
    }

    @Override
    public <T> Object getBean(Class<T> beanClazz) {

        return doGetBean(null, beanClazz);

    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClazz) {
        return (T)doGetBean(beanName, beanClazz);
    }


    public Object doGetBean(String beanName, Class<?> beanClazz) {

        BeanDefinition beanDefinition = null;

        Object result = null;

        if(null != beanClazz) {

            HashMap<String, BeanDefinition> beanDefinitionMap = getAllBeanDefinition();
            Set<String> keySet = beanDefinitionMap.keySet();

            for (String key : keySet) {
                BeanDefinition temp = beanDefinitionMap.get(key);

                if(temp.getClass().equals(beanClazz) && beanDefinition == null) {
                    beanDefinition = temp;
                }

                else if(temp.getClass().equals(beanClazz)) {
                    throw new IllegalStateException("there are more one BeanDefinition what its class equal 'beanClass'");
                }
            }
        }

        if(null != beanName && ! "".equals(beanName)) {

            BeanDefinition temp = getBeanDefinition(beanName);

            if(null != beanDefinition && temp != beanDefinition)
                return null;
            beanDefinition = temp;
        }

        if(null == beanDefinition)
            return null;


        // create singleton bean
        if(beanDefinition.getSingleton()) {
            result = getSingleton(beanDefinition.getBeanName());

            // (result == null) surface the BeanDefinition doesn't register into the singleton container
            if(null == result){
                BeanDefinition finalBeanDefinition = beanDefinition;
                result = getSingleton(new ObjectFactory() {
                    @Override
                    public Object getObject() {
                        return createBean(beanName, finalBeanDefinition);
                    }
                });
            }

            if(null != result)
                return result;
        }

        // create prototype bean
        if(!beanDefinition.getSingleton()) {
            result = createBean(beanName, beanDefinition);
        }

        return result;

    }

    @Override
    public Boolean containBean(String beanName) {
        return containsBeanDefinition(beanName);
    }

    @Override
    public <T> Boolean containBean(Class<T> beanClazz) {
        HashMap<String, BeanDefinition> beanDefinitions = getAllBeanDefinition();
        Set<String> keys = beanDefinitions.keySet();
        for (String key : keys) {
            BeanDefinition beanDefinition = beanDefinitions.get(key);
            if(beanDefinition.getClass().equals(beanClazz))
                return true;
        }
        return false;
    }

    @Override
    public <T> Boolean containBean(String beanName, Class<T> beanClazz) {
        BeanDefinition beanDefinition = getBeanDefinition(beanName);

        if(beanDefinition.getClass().equals(beanClazz))
            return true;
        return false;
    }

    @Override
    public Class<?> getType(String beanName) {

        BeanDefinition beanDefinition = getBeanDefinition(beanName);

        return beanDefinition.getClass();
    }

    /**
     * the method creates bean really and its implement is in {@link AutowireCapableBeanFactory}
     */
    public abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
