package common;

import org.semspringframework.beans.factory.Demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class ParamterTest {

    public static void main(String[] args) {
        Class<?> clazz = Demo.class;

        Constructor<?>[] constructors = clazz.getConstructors();

        for (Constructor<?> constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();

            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
            }
        }

    }

}
