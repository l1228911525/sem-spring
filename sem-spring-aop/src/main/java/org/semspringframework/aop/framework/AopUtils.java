package org.semspringframework.aop.framework;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.springframework.cglib.proxy.Enhancer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * aop utils, it hold the function:
 * 1.generate proxy object
 * 2.judge whether a class meets the expression
 */
public class AopUtils {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    public static Object getProxy(Object bean, List<BeanAdvice> beanAdviceList) {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(bean.getClass());

        BeanMethodInterceptor beanMethodInterceptor = new BeanMethodInterceptor();

        beanMethodInterceptor.setBeanAdviceList(beanAdviceList);

        enhancer.setCallback(beanMethodInterceptor);

        return bean.getClass().cast(enhancer.create());

    }

    public static Boolean judgePointcutAndClass(String expression, Class<?> clazz) {

        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, AopUtils.class.getClassLoader());

        boolean b = pointcutParser.parsePointcutExpression(expression).couldMatchJoinPointsInType(clazz);

        return b;

    }

}
