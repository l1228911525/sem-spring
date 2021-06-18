package org.semspringframework.beans.factory.support;

import org.junit.Before;
import org.junit.Test;
import org.semspringframework.beans.factory.pojo.Person;
import org.semspringframework.beans.factory.pojo.User;

import java.util.HashMap;

public class DefaultListableBeanFactoryTest {

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
    public void test0() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.setBeanDefinitionMap(hashMap);

        Object person = beanFactory.getBean("user");

        System.out.println(person);
    }



}
