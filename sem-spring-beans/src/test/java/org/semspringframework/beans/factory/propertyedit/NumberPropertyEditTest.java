package org.semspringframework.beans.factory.propertyedit;

import org.junit.Test;
import org.semspringframework.beans.propertyedit.NumberPropertyEdit;

public class NumberPropertyEditTest {

    @Test
    public void test0() {

        NumberPropertyEdit propertyEdit = new NumberPropertyEdit();

        propertyEdit.setAsText("123", Integer.class);

        Object value = propertyEdit.getValue();

        System.out.println(value.getClass());
        System.out.println(value);

    }
}
