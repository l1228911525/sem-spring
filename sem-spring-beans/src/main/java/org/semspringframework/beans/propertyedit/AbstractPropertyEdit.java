package org.semspringframework.beans.propertyedit;

public abstract class AbstractPropertyEdit implements PropertyEdit {

    private Object value;
    private Object source;

    @Override
    public void setValue(Object object) {
        value = object;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setAsText(String strObject, Class<?> clazz) {
        if(strObject != null)
            setValue(strObject);
    }

    @Override
    public String getAsText() {
        return value != null ? value.toString() : null;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
