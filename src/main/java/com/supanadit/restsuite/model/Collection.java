package com.supanadit.restsuite.model;

import java.util.ArrayList;

public class Collection {
    private Collection parent;
    private String name;
    private String URL;
    private String method;
    private ArrayList<Request> headers;
    private String bodyType;
    private ArrayList<RequestBodyFormInputModel> bodyForm;
    private String bodyRawType;
    private String bodyRawValue;
}
