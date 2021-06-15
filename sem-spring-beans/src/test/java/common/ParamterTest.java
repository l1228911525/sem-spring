package common;

import org.semspringframework.beans.factory.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class ParamterTest {

    public static void main(String[] args) {
        Class<?> clazz = Test.class;

        Constructor<?>[] constructors = clazz.getConstructors();

        for (Constructor<?> constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();

            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
            }
        }

    }

}
