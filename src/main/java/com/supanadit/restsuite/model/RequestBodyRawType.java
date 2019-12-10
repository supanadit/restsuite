package com.supanadit.restsuite.model;

public class RequestBodyRawType {
    protected String name;

    public RequestBodyRawType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RequestBodyRawType TEXT() {
        return new RequestBodyRawType("TEXT");
    }

    public static RequestBodyRawType JSON() {
        return new RequestBodyRawType("JSON");
    }

    public static RequestBodyRawType HTML() {
        return new RequestBodyRawType("HTML");
    }

    public static RequestBodyRawType XML() {
        return new RequestBodyRawType("XML");
    }

    public static RequestBodyRawType JAVASCRIPT() {
        return new RequestBodyRawType("JAVASCRIPT");
    }
}
