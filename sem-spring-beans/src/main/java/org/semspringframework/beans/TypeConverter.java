package org.semspringframework.beans;

public interface TypeConverter {

    public <T> T convert(Object oldValue, Class<T> clazz, T newValue);

    public <T> T convert(Object oldValue, Class<T> clazz);

}
