package org.semspringframework.beans;

import java.util.HashMap;

/**
 * a class implement the interface surface the class hold the ability of operator the property of a bean
 */
public interface PropertyAccessor {

    public Class<?> getPropertyClass(String propertyName);

    public void setPropertyValue(String propertyName, Object value);

    public void setPropertyValues(HashMap<String, Object> parameterValues);

}
