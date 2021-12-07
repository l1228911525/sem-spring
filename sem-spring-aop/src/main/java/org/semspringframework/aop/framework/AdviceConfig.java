package org.semspringframework.aop.framework;

import java.util.LinkedList;
import java.util.List;

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

}
