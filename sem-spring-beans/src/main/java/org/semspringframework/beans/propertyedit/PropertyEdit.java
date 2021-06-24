package org.semspringframework.beans.propertyedit;

/**
 * a class implement the interface surface it hold the ability edit property of object
 */
public interface PropertyEdit {

    /**
     * set the property value as object
     * @param object
     */
    public void setValue(Object object);

    /**
     * get the property value
     * @return
     */
    public Object getValue();

    /**
     * set the property value though the strObject
     * @param strObject
     */
    public void setAsText(String strObject, Class<?> clazz);

    /**
     * transform the property value to String and return
     * @return
     */
    public String getAsText();
}
