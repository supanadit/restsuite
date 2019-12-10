package com.supanadit.restsuite.model;

public class RequestBodyType {
    protected String name;

    public RequestBodyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RequestBodyType RAW() {
        return new RequestBodyType("RAW");
    }

    public static RequestBodyType FORM() {
        return new RequestBodyType("FORM");
    }
}
