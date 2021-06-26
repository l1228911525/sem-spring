package org.semspringframework.beans;

import org.semspringframework.beans.propertyedit.PropertyEdit;

/**
 * the registrar of property edit, a class implement the interface surface the class hold the ability of manager property edit
 */
public interface PropertyEditRegistry {

    /**
     * register property edit into HashMap, key: clazz, value: propertyEdit
     * @param propertyEdit
     * @param clazz
     */
    public void registerPropertyEdit(PropertyEdit propertyEdit, Class<?> clazz);

    /**
     * remove property edit according to clazz from HashMap
     * @param clazz
     */
    public void removePropertyEdit(Class<?> clazz);

    /**
     * find and return property edit according to clazz from HashMap
     * @param clazz
     * @return
     */
    public PropertyEdit findPropertyEdit(Class<?> clazz);
}
