package com.supanadit.restsuite.model;

public class RequestTypeModel {
    protected String name;

    public RequestTypeModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RequestTypeModel GET() {
        return new RequestTypeModel("GET");
    }

    public static RequestTypeModel POST() {
        return new RequestTypeModel("POST");
    }

    public static RequestTypeModel DELETE() {
        return new RequestTypeModel("DELETE");
    }

    public static RequestTypeModel PUT() {
        return new RequestTypeModel("PUT");
    }
}
