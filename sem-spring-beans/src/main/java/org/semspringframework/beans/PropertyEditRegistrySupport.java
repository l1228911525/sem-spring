package org.semspringframework.beans;

import org.semspringframework.beans.propertyedit.BooleanPropertyEdit;
import org.semspringframework.beans.propertyedit.NumberPropertyEdit;
import org.semspringframework.beans.propertyedit.PropertyEdit;
import org.semspringframework.beans.propertyedit.StringPropertyEdit;

import java.util.HashMap;

/**
 * the abstract class implement PropertyEditRegistry,
 * so the class hold the ability of managing PropertyEdit
 */
public class PropertyEditRegistrySupport implements PropertyEditRegistry {

    HashMap<Class<?>, PropertyEdit> propertyEditMap;

    /**
     * initialization of the property HashMap and register a few default property edit
     */
    public PropertyEditRegistrySupport() {
        propertyEditMap = new HashMap<>();
        registerDefaultPropertyEdit();
    }

    /**
     * register property edit
     */
    @Override
    public void registerPropertyEdit(PropertyEdit propertyEdit, Class<?> clazz) {
        this.propertyEditMap.put(clazz, propertyEdit);
    }

    @Override
    public void removePropertyEdit(Class<?> clazz) {
        this.propertyEditMap.remove(clazz);
    }

    @Override
    public PropertyEdit findPropertyEdit(Class<?> clazz) {
        return propertyEditMap.get(clazz);
    }

    /**
     * register a few default property edit
     */
    private void registerDefaultPropertyEdit() {
        this.registerPropertyEdit(new BooleanPropertyEdit(), boolean.class);
        this.registerPropertyEdit(new BooleanPropertyEdit(), Boolean.class);
        NumberPropertyEdit numberPropertyEdit = new NumberPropertyEdit();
        this.registerPropertyEdit(numberPropertyEdit, byte.class);
        this.registerPropertyEdit(numberPropertyEdit, Byte.class);
        this.registerPropertyEdit(numberPropertyEdit, short.class);
        this.registerPropertyEdit(numberPropertyEdit, Short.class);
        this.registerPropertyEdit(numberPropertyEdit, int.class);
        this.registerPropertyEdit(numberPropertyEdit, Integer.class);
        this.registerPropertyEdit(numberPropertyEdit, long.class);
        this.registerPropertyEdit(numberPropertyEdit, Long.class);
        this.registerPropertyEdit(numberPropertyEdit, float.class);
        this.registerPropertyEdit(numberPropertyEdit, Float.class);
        this.registerPropertyEdit(numberPropertyEdit, double.class);
        this.registerPropertyEdit(numberPropertyEdit, Double.class);
        this.registerPropertyEdit(new StringPropertyEdit(), String.class);
    }

    public static class TypeConvertSupport {
    }
}
