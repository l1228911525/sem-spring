package org.semspringframework.beans;

import org.junit.Test;
import pojo.Person;

public class BeanWrapperImplTest {

    @Test
    public void test0() {

        Person person = new Person();

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl();

        beanWrapper.setObject(person);

        beanWrapper.setPropertyValue("name", "lqyyy");

        beanWrapper.setPropertyValue("age", "18");

        System.out.println(person);

    }

    @Test
    public void test1() {

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(Person.class);

        Class<?> name = beanWrapper.getPropertyClass("name");

        System.out.println(name);

        Class<?> age = beanWrapper.getPropertyClass("age");

        System.out.println(age);
    }

}