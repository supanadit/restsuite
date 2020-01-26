package com.supanadit.restsuite.model;

public class BodyFormTypeModel {
    protected String name;

    public BodyFormTypeModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static BodyFormTypeModel FIELD() {
        return new BodyFormTypeModel("Field");
    }

    public static BodyFormTypeModel FILE() {
        return new BodyFormTypeModel("File");
    }
}
