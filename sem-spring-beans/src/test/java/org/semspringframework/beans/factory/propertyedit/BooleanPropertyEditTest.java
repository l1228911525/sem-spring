package org.semspringframework.beans.factory.propertyedit;

import org.junit.Test;
import org.semspringframework.beans.propertyedit.BooleanPropertyEdit;

public class BooleanPropertyEditTest {

    @Test
    public void test0() {
        BooleanPropertyEdit propertyEdit = new BooleanPropertyEdit();

        propertyEdit.setAsText("true", Boolean.class);

        Object value = propertyEdit.getValue();

        System.out.println(value);

    }

    @Test
    public void test1() {
        BooleanPropertyEdit propertyEdit = new BooleanPropertyEdit();

        propertyEdit.setAsText("yhrrh", Boolean.class);

        Object value = propertyEdit.getValue();

        System.out.println(value);
    }

}
