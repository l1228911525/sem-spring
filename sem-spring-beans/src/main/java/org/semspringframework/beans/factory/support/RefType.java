package org.semspringframework.beans.factory.support;

public class RefType {

    private String name;

    private String refName;

    public RefType() {
    }

    public RefType(String name, String refName) {
        this.name = name;
        this.refName = refName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }
}
