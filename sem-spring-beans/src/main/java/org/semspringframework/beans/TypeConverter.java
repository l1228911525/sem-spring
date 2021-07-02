package org.semspringframework.beans;

/**
 * a class implement the interface surface the class hold the ability of converting a object'type
 */
public interface TypeConverter {

    /**
     * convert a object type and put the result into the parameter 'newValue' and return
     * @param oldValue origin object
     * @param clazz new type class
     * @param newValue a new object after converting type
     * @param <T>
     * @return
     */
    public <T> T convert(Object oldValue, Class<T> clazz, T newValue);

    /**
     * convert the object 'oldValue' type and return
     * @param oldValue
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T convert(Object oldValue, Class<T> clazz);

}
