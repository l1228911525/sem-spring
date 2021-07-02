package org.semspringframework.beans;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

/**
 * the class implement BeanWrapper and PropertyAccessor,
 * so the class hold the ability of operate bean and its property
 */
public class BeanWrapperImpl extends TypeConverterSupport implements BeanWrapper, PropertyAccessor {

    private Object bean;

    private Class<?> beanClass;

    public BeanWrapperImpl(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public BeanWrapperImpl() {

        this.bean = null;

        this.beanClass = null;

    }

    public BeanWrapperImpl(Object bean) {
        this.bean = bean;
        this.beanClass = bean.getClass();
    }

    @Override
    public Object getObject() {
        return this.bean;
    }

    @Override
    public void setObject(Object object) {
        this.bean = object;
        this.beanClass = object.getClass();
    }

    @Override
    public Class<?> getObjectClass() {
        return this.beanClass;
    }

    @Override
    public Class<?> getPropertyClass(String propertyName) {
        if(beanClass == null)
            return null;
        try {
            Field field = beanClass.getDeclaredField(propertyName);
            field.setAccessible(true);
            return field.getType();
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("get the property name: " + propertyName + " of the class: " + beanClass + " fail, "+e);
        }
    }

    @Override
    public void setPropertyValue(String propertyName, Object value) {

        if(null == this.bean) {
            throw new IllegalStateException("the bean: " + this.bean + " is null, so can't set property value");
        }

        Object convertValue = value;

        if(this.getPropertyClass(propertyName) != value.getClass())
            convertValue = convert(value, this.getPropertyClass(propertyName));

        try {
            Field field = beanClass.getDeclaredField(propertyName);

            field.setAccessible(true);

            field.set(bean, convertValue);
        } catch (Exception e) {

            throw new IllegalStateException("set the property named " + propertyName + " of the class: " + beanClass + " failed, " + e);

        }
    }

    @Override
    public void setPropertyValues(HashMap<String, Object> parameterValues) {
        Set<String> keys = parameterValues.keySet();

        for (String key : keys) {
            setPropertyValue(key, parameterValues.get(key));
        }
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.bean = null;
    }
}