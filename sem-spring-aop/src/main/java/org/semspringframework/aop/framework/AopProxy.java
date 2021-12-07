package org.semspringframework.aop.framework;

/**
 * if a class implement the interface{@link AopProxy}, it hold a ability of generating proxy object for {@param bean}
 */
public interface AopProxy {

    public Object getProxy(Object bean);

}
