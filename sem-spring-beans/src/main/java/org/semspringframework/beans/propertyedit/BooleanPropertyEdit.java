package org.semspringframework.beans.propertyedit;

/**
 * edit the property of type boolean
 */
public class BooleanPropertyEdit extends AbstractPropertyEdit {

    @Override
    public void setAsText(String strObject, Class<?> clazz) {
        if(boolean.class == clazz || Boolean.class == clazz) {
            Boolean resultBoolean = encodeStr(strObject);

            setValue(resultBoolean);
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }

    public Boolean encodeStr(String booleanStr) {
        if("false".equals(booleanStr) || "0".equals(booleanStr))
            return false;
        else
            return true;
    }

}
