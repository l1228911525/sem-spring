package org.semspringframework.aop.framework;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class BeanAdvice {

    private Object adviceObj;

    private List<Method> beforeMethod = new LinkedList<>();

    private List<Method> afterMethod = new LinkedList<>();

    public Object getAdviceObj() {
        return adviceObj;
    }

    public void setAdviceObj(Object adviceObj) {
        this.adviceObj = adviceObj;
    }

    public void addBeforeMethod(Method method) {
        beforeMethod.add(method);
    }

    public void addAfterMethod(Method method) {
        afterMethod.add(method);
    }

    public List<Method> getBeforeMethod() {
        return beforeMethod;
    }

    public List<Method> getAfterMethod() {
        return afterMethod;
    }
}
