package org.semspringframework.aop.framework;

import java.util.LinkedList;
import java.util.List;

public class Aspect {

    private String id;

    private String ref;

    private List<BeforeAdvice> beforeAdviceList = new LinkedList<>();

    private List<AfterAdvice> afterAdviceList = new LinkedList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void addBeforeAdvice(BeforeAdvice beforeAdvice){
        beforeAdviceList.add(beforeAdvice);
    }

    public void addAfterAdvice(AfterAdvice afterAdvice){
        afterAdviceList.add(afterAdvice);
    }

    public List<BeforeAdvice> getBeforeAdviceList() {
        return beforeAdviceList;
    }

    public List<AfterAdvice> getAfterAdviceList() {
        return afterAdviceList;
    }
}
