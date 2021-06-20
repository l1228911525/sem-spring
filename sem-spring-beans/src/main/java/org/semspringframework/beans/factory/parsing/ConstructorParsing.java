package org.semspringframework.beans.factory.parsing;

import org.semspringframework.beans.factory.support.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * operator of constructor related
 */
public class ConstructorParsing {

    /**
     * get related constructor according to parameter name
     */
    public static Constructor<?> getConstructor(BeanDefinition beanDefinition) {

        String[] paramNames = beanDefinition.getConstructorParameter();

        Constructor<?> result = null;

        Class<?> beanClass = beanDefinition.getBeanClass();

        Constructor<?>[] constructors = beanClass.getConstructors();

        for (Constructor<?> constructor : constructors) {

            // the sign mark the constructor is right or not
            boolean flag = true;

            Parameter[] parameters = constructor.getParameters();

            if(parameters.length == paramNames.length) {

                for (Parameter parameter : parameters) {

                    if (!Arrays.asList(paramNames).contains(parameter.getName())) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    result = constructor;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * get parameter name through parsing constructor
     */
    public static String[] getParameterName(BeanDefinition beanDefinition) {

        List<String> parameterName = new LinkedList<>();


        Class<?> beanClass = beanDefinition.getBeanClass();
        HashMap<String, Object> initParam = beanDefinition.getInitParam();

        Set<String> initKey = initParam.keySet();

        Constructor<?>[] constructors = beanClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();

            if(parameters.length != initKey.size())
                continue;

            for (Parameter parameter : parameters) {
                if(!initKey.contains(parameter.getName()))
                    break;
                parameterName.add(parameter.getName());
            }

            if(parameterName.size() == parameters.length) {
                return (String[])parameterName.toArray(new String[parameterName.size()]);
            }
        }
        throw new IllegalStateException("parameter: constructor-arg is error. don't have the constructor. ");
    }

}
