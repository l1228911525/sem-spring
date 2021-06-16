package org.semspringframework.beans.factory;

import java.lang.reflect.Constructor;

public class Demo {

    private String name;

    public Demo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        
    }

}
