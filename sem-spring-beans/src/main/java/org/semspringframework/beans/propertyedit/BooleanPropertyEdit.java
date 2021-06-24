package org.semspringframework.beans.propertyedit;

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
        return super.getAsText();
    }

    public Boolean encodeStr(String booleanStr) {
        if("true".equals(booleanStr))
            return true;
        else if("false".equals(booleanStr))
            return false;
        throw new IllegalStateException("booleanStr: "+booleanStr+" isn't true or false");
    }

}
