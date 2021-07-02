package org.semspringframework.beans;

import org.semspringframework.beans.propertyedit.PropertyEdit;

/**
 * the support implement {@link TypeConverter} that hold the ability of converting property type
 * using PropertyEdit
 */
public class TypeConverterSupport extends PropertyEditRegistrySupport implements TypeConverter {

    public TypeConverterSupport() {
        super();
    }

    @Override
    public <T> T convert(Object oldValue, Class<T> clazz, T newValue) {
        return doConvert(oldValue, clazz, newValue);
    }

    @Override
    public <T> T convert(Object oldValue, Class<T> clazz) {
        return doConvert(oldValue, clazz, null);
    }

    public <T> T doConvert(Object oldValue, Class<T> clazz, T newValue) {
        PropertyEdit propertyEdit = findPropertyEdit(clazz);

        if(!(oldValue instanceof String))
            throw new IllegalStateException("the type of "+oldValue+" is't String");
        propertyEdit.setAsText((String)oldValue, clazz);

        Object value = propertyEdit.getValue();

        if(null != newValue)
            newValue = (T)value;

        return (T)value;

    }
}
