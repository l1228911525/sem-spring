package org.semspringframework.aop.framework;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.springframework.cglib.proxy.Enhancer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * if a class implement the interface{@link AopUtils}, it hold a ability of generating proxy object for {@param bean}
 */
public class AopUtils {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    private static PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, AopUtils.class.getClassLoader());


    public static Object getProxy(Object bean, List<BeanAdvice> beanAdviceList) {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(bean.getClass());

        BeanMethodInterceptor beanMethodInterceptor = new BeanMethodInterceptor();

        beanMethodInterceptor.setBeanAdviceList(beanAdviceList);

        enhancer.setCallback(beanMethodInterceptor);

        return bean.getClass().cast(enhancer.create());

    }

    public static Boolean judgePointcutAndClass(String expression, Class<?> clazz) {

        return pointcutParser.parsePointcutExpression(expression).couldMatchJoinPointsInType(clazz);

    }

}
