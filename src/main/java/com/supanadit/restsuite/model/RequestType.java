package com.supanadit.restsuite.model;

public class RequestType {
    protected String name;

    public RequestType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RequestType GET() {
        return new RequestType("GET");
    }

    public static RequestType POST() {
        return new RequestType("POST");
    }

    public static RequestType DELETE() {
        return new RequestType("DELETE");
    }

    public static RequestType PUT() {
        return new RequestType("PUT");
    }
}
