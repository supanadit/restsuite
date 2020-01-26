package com.supanadit.restsuite.model;

public class RequestBodyTypeModel {
    protected String name;

    public RequestBodyTypeModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RequestBodyTypeModel RAW() {
        return new RequestBodyTypeModel("RAW");
    }

    public static RequestBodyTypeModel FORM() {
        return new RequestBodyTypeModel("FORM");
    }
}
