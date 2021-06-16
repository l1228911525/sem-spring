package org.semspringframework.beans.factory.support;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * attributes of the class description the corresponding bean. there attributes are derived from bean tag of xml file
 */
public class BeanDefinition {

    private String beanName;

    private Class<?> beanClass;

    private Boolean singleton;

    private Boolean lazy;

    private String initMethod;

    private String destroyMethod;

    private Constructor<?> beanConstructor;

    private String[] constructorParameter;

    private HashMap<String, Object> initParam;

    private HashMap<String, Object> setParam;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Boolean getSingleton() {
        return singleton;
    }

    public void setSingleton(Boolean singleton) {
        this.singleton = singleton;
    }

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getDestroyMethod() {
        return destroyMethod;
    }

    public void setDestroyMethod(String destroyMethod) {
        this.destroyMethod = destroyMethod;
    }

    public Constructor<?> getBeanConstructor() {
        return beanConstructor;
    }

    public void setBeanConstructor(Constructor<?> beanConstructor) {
        this.beanConstructor = beanConstructor;
    }

    public String[] getConstructorParameter() {
        return constructorParameter;
    }

    public void setConstructorParameter(String[] constructorParameter) {
        this.constructorParameter = constructorParameter;
    }

    public HashMap<String, Object> getInitParam() {
        return initParam;
    }

    public void setInitParam(HashMap<String, Object> initParam) {
        this.initParam = initParam;
    }

    public HashMap<String, Object> getSetParam() {
        return setParam;
    }

    public void setSetParam(HashMap<String, Object> setParam) {
        this.setParam = setParam;
    }
}
