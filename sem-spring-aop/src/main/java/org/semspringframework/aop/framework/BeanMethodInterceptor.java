package org.semspringframework.aop.framework;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * the concrete realization of the proxy class
 */
public class BeanMethodInterceptor implements MethodInterceptor {

    private List<BeanAdvice> beanAdviceList = new LinkedList<>();

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        // run pre-enhanced method
        for (BeanAdvice beanAdvice : beanAdviceList) {
            List<Method> beforeMethods = beanAdvice.getBeforeMethod();

            for (Method beforeMethod : beforeMethods) {
                beforeMethod.invoke(beanAdvice.getAdviceObj());
            }
        }

        // proxyed bean method
        Object o = methodProxy.invokeSuper(obj, args);

        // run post-enhanced method
        for (BeanAdvice beanAdvice : beanAdviceList) {
            List<Method> afterMethods = beanAdvice.getAfterMethod();

            for (Method afterMethod : afterMethods) {
                afterMethod.invoke(beanAdvice.getAdviceObj());
            }
        }
        return o;
    }

    public List<BeanAdvice> getBeanAdviceList() {
        return beanAdviceList;
    }

    public void setBeanAdviceList(List<BeanAdvice> beanAdviceList) {
        this.beanAdviceList = beanAdviceList;
    }
}
