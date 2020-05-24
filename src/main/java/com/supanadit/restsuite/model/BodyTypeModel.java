package com.supanadit.restsuite.model;

public class BodyTypeModel {
    protected String name;

    public BodyTypeModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static BodyTypeModel RAW() {
        return new BodyTypeModel("RAW");
    }

    public static BodyTypeModel FORM() {
        return new BodyTypeModel("FORM");
    }
}
