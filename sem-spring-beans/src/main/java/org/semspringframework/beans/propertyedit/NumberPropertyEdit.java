package org.semspringframework.beans.propertyedit;

public class NumberPropertyEdit extends AbstractPropertyEdit {

    @Override
    public void setAsText(String strObject, Class<?> clazz) {

        Number number = NumberUtils.convertNumberFromStr(strObject, clazz);

        setValue(number);
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }

    public static class NumberUtils{
        public static Number convertNumberFromStr(String strNumber, Class<?> clazz) {

            if(byte.class == clazz || Byte.class == clazz)
                return Byte.parseByte(strNumber);
            else if(short.class == clazz || Short.class == clazz)
                return Short.parseShort(strNumber);
            else if(int.class == clazz || Integer.class == clazz)
                return Integer.parseInt(strNumber);
            else if(long.class == clazz || Long.class == clazz)
                return Long.parseLong(strNumber);
            else if(float.class == clazz || Float.class == clazz)
                return Float.parseFloat(strNumber);
            else if(double.class == clazz || Double.class == clazz)
                return Double.parseDouble(strNumber);
            throw new IllegalStateException("class: " + clazz +" isn't number class");
        }
    }
}
