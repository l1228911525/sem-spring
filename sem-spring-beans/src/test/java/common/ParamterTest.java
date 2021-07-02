package common;

import org.semspringframework.beans.TypeConverter;
import pojo.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public class ParamterTest {

    public static void main(String[] args) throws Exception {

        Class<?> personClass = Person.class;

        System.out.println(personClass);

        Field haha = personClass.getDeclaredField("age");

        haha.setAccessible(true);

        Class<?> type = haha.getType();

        System.out.println(type);

    }



}
