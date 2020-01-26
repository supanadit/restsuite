package com.supanadit.restsuite.model;

public class RequestBodyFormTypeModel {
    protected String name;

    public RequestBodyFormTypeModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RequestBodyFormTypeModel FIELD() {
        return new RequestBodyFormTypeModel("Field");
    }

    public static RequestBodyFormTypeModel FILE() {
        return new RequestBodyFormTypeModel("File");
    }
}
