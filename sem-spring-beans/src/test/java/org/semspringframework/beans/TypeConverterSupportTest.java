package org.semspringframework.beans;

import org.junit.Test;

public class TypeConverterSupportTest {

    @Test
    public void test0() {
        TypeConverterSupport support = new TypeConverterSupport();

        Boolean aFalse = support.convert("false", Boolean.class);

        System.out.println(aFalse);
    }

    @Test
    public void test1() {
        TypeConverterSupport support = new TypeConverterSupport();

        Integer convert = support.convert("111", Integer.class);

        System.out.println(convert);


    }
}
