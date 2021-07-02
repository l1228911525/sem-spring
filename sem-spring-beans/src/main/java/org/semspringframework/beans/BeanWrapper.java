package org.semspringframework.beans;

/**
 * a class implement the interface surface the class hold the ability of manager the bean
 */
public interface BeanWrapper {

    public Object getObject();

    public void setObject(Object object);

    public Class<?> getObjectClass();

}
