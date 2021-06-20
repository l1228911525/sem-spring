package org.semspringframework.beans.factory.parsing;

import org.junit.Before;
import org.junit.Test;
import org.semspringframework.beans.factory.support.BeanDefinition;
import org.semspringframework.beans.factory.support.RefType;
import pojo.Person;
import pojo.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ConstructorParsingTest {

    HashMap<String, BeanDefinition> hashMap = new HashMap<String, BeanDefinition>();

    @Before
    public void before() throws Exception {
        BeanDefinition beanDefinition = new BeanDefinition();

        beanDefinition.setBeanName("person");
        beanDefinition.setBeanClass(Person.class);
        beanDefinition.setBeanConstructor(Person.class.getConstructor(String.class, Integer.class));
        HashMap<String, Object> initMap = new HashMap<String, Object>();
        initMap.put("name", "lqyyy");
        initMap.put("age", 18);
        beanDefinition.setInitParam(initMap);
        beanDefinition.setConstructorParameter(new String[]{"name", "age"});
        beanDefinition.setSingleton(false);
        beanDefinition.setLazy(false);
        hashMap.put("person", beanDefinition);

        beanDefinition.setBeanName("person2");
        beanDefinition.setBeanClass(Person.class);
        beanDefinition.setBeanConstructor(Person.class.getConstructor(String.class, Integer.class));
        HashMap<String, Object> initMapPerson2 = new HashMap<String, Object>();
        initMapPerson2.put("name", "lll");
        initMapPerson2.put("age", 12);
        beanDefinition.setInitParam(initMapPerson2);
        beanDefinition.setConstructorParameter(new String[]{"name", "age"});
        beanDefinition.setSingleton(false);
        beanDefinition.setLazy(false);
        HashMap<String, Object> setParam = beanDefinition.getSetParam();
        hashMap.put("person2", beanDefinition);


        BeanDefinition beanDefinition2 = new BeanDefinition();
        beanDefinition2.setBeanName("user");
        beanDefinition2.setBeanClass(User.class);
        beanDefinition2.setBeanConstructor(User.class.getConstructor(Person.class));
        HashMap<String, Object> initMap2 = new HashMap<String, Object>();
        initMap2.put("person", new RefType("person", "person"));
        beanDefinition2.setInitParam(initMap2);
        beanDefinition2.setConstructorParameter(new String[]{"person"});
        beanDefinition2.setSingleton(false);
        beanDefinition2.setLazy(false);
        HashMap<String, Object> setParamUser = new HashMap<>();
        setParamUser.put("person", new RefType("person", "person2"));
        beanDefinition2.setSetParam(setParamUser);
        hashMap.put("user", beanDefinition2);
    }

    @Test
    public void test() throws IllegalAccessException, InvocationTargetException, InstantiationException {

        Constructor<?> constructor = ConstructorParsing.getConstructor(hashMap.get("person"));


        Object[] objects = new Object[2];

        objects[0] = "lqyy";
        objects[1] = 12;

        Object o = constructor.newInstance(objects);


        System.out.println(o);

    }

    @Test
    public void test1() {
        String[] people = ConstructorParsing.getParameterName(hashMap.get("person"));

        System.out.println(people[0]);
        System.out.println(people[1]);
    }

}
