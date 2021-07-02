package org.semspringframework.beans.propertyedit;

public class StringPropertyEdit extends AbstractPropertyEdit {

    @Override
    public void setAsText(String strObject, Class<?> clazz) {
        setValue(strObject.toString());
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}