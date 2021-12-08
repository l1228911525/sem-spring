package org.semspringframework.aop.framework;

/**
 * after advice class
 * configuration class
 */
public class AfterAdvice {

    private String method;

    private String pointcut;

    private String pointcutRef;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPointcut() {
        return pointcut;
    }

    public void setPointcut(String pointcut) {
        this.pointcut = pointcut;
    }

    public String getPointcutRef() {
        return pointcutRef;
    }

    public void setPointcutRef(String pointcutRef) {
        this.pointcutRef = pointcutRef;
    }
}
