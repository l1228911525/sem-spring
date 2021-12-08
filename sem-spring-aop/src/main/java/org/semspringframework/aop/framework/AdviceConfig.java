package org.semspringframework.aop.framework;

import org.semspringframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * whole aop configuration class
 */
public class AdviceConfig {

    List<Pointcut> pointcutList = new LinkedList<>();

    List<Aspect> aspectList = new LinkedList<>();

    public void addPointcut(Pointcut pointcut) {
        this.pointcutList.add(pointcut);
    }

    public Pointcut getPointcutById(String pointcutId) {
        for (Pointcut pointcut : this.pointcutList) {
            if(pointcut.getId().equals(pointcutId))
                return pointcut;
        }
        return null;
    }

    public void addAspect(Aspect aspect) {
        this.aspectList.add(aspect);
    }

    public Aspect getAspectById(String aspectId) {
        for (Aspect aspect : aspectList) {
            if(aspect.getId().equals(aspectId))
                return aspect;
        }
        return null;
    }

    public List<Pointcut> getPointcutList() {
        return pointcutList;
    }

    public List<Aspect> getAspectList() {
        return aspectList;
    }

    public List<BeanAdvice> getBeanAdvice(Class<?> clazz, DefaultListableBeanFactory beanFactory) {

        List<BeanAdvice> beanAdviceList = new LinkedList<>();

        for (Aspect aspect : this.aspectList) {

            BeanAdvice beanAdvice = new BeanAdvice();

            String aspectRef = aspect.getRef();

            Object aspectBean = beanFactory.getBean(aspectRef);

            beanAdvice.setAdviceObj(aspectBean);

            List<BeforeAdvice> beforeAdviceList = aspect.getBeforeAdviceList();

            List<AfterAdvice> afterAdviceList = aspect.getAfterAdviceList();

            for (BeforeAdvice beforeAdvice : beforeAdviceList) {

                String pointcut = beforeAdvice.getPointcut();

                String pointcutRef = beforeAdvice.getPointcutRef();

                if(pointcut != null && !"".equals(pointcut)) {

                    Boolean judgeResult = AopUtils.judgePointcutAndClass(pointcut, clazz);

                    if(judgeResult) {
                        try {

                            Object aspectObj = beanFactory.getBean(aspect.getRef());

                            beanAdvice.addBeforeMethod(aspectObj.getClass().getMethod(beforeAdvice.getMethod()));
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                } else if(pointcutRef != null && !"".equals(pointcutRef)) {

                    Pointcut pointcutObj = this.getPointcutById(pointcutRef);

                    Boolean judgeResult = AopUtils.judgePointcutAndClass(pointcutObj.getExpression(), clazz);

                    if(judgeResult) {
                        try {
                            beanAdvice.addBeforeMethod(aspectBean.getClass().getMethod(beforeAdvice.getMethod()));
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }

                }

            }


            for (AfterAdvice afterAdvice : afterAdviceList) {

                String pointcut = afterAdvice.getPointcut();

                String pointcutRef = afterAdvice.getPointcutRef();

                if(pointcut != null && !"".equals(pointcut)) {

                    Boolean judgeResult = AopUtils.judgePointcutAndClass(pointcut, clazz);

                    if(judgeResult) {
                        try {
                            beanAdvice.addAfterMethod(aspect.getRef().getClass().getMethod(afterAdvice.getMethod()));
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                } else if(pointcutRef != null && !"".equals(pointcutRef)) {

                    Pointcut pointcutObj = this.getPointcutById(pointcutRef);

                    Boolean judgeResult = AopUtils.judgePointcutAndClass(pointcutObj.getExpression(), clazz);

                    if(judgeResult) {
                        try {
                            beanAdvice.addAfterMethod(aspectBean.getClass().getMethod(afterAdvice.getMethod()));
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    }

                }

            }

            if(beanAdvice.getBeforeMethod().size() != 0 || beanAdvice.getAfterMethod().size() != 0)
                beanAdviceList.add(beanAdvice);
        }

        return beanAdviceList;

    }

}
