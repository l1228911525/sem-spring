package org.semspringframework.aop.framework;

/**
 * point cut object
 */
public class Pointcut {

    private String id;

    private String expression;

    public Pointcut() {
    }

    public Pointcut(String id, String expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

}
