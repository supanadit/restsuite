package com.supanadit.restsuite.model;

public class RequestBodyFormType {
    protected String name;

    public RequestBodyFormType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RequestBodyFormType FIELD() {
        return new RequestBodyFormType("Field");
    }

    public static RequestBodyFormType FILE() {
        return new RequestBodyFormType("File");
    }
}
