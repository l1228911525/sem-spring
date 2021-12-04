package org.semspringframework.context;

import org.semspringframework.context.support.ClassPathXmlApplicationContext;
import pojo.InitPojo;

public class ClassPathApplicationContextTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myspring.xml");

        Object bean = context.getBean(InitPojo.class);

        System.out.println(bean);
    }

}
