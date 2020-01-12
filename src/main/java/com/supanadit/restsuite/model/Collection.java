package com.supanadit.restsuite.model;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Collection {
    private Collection parent;
    private String name;
    private String url;
    private String method;
    private ArrayList<Request> headers;
    private String bodyType;
    private ArrayList<RequestBodyFormInputModel> bodyForm;
    private String bodyRawType;
    private String bodyRawValue;

    public Collection(@Nullable Collection parent, @Nullable String name, String url, String method, ArrayList<Request> headers, String bodyType, ArrayList<RequestBodyFormInputModel> bodyForm, String bodyRawType, String bodyRawValue) {
        this.parent = parent;
        this.name = name;
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.bodyType = bodyType;
        this.bodyForm = bodyForm;
        this.bodyRawType = bodyRawType;
        this.bodyRawValue = bodyRawValue;
    }

    public Collection(String name, String url, String method, ArrayList<Request> headers, String bodyType, ArrayList<RequestBodyFormInputModel> bodyForm, String bodyRawType, String bodyRawValue) {
        this(null, name, url, method, headers, bodyType, bodyForm, bodyRawType, bodyRawValue);
    }

    public static Collection fromApiModel(ApiModel apiModel) {
        String url = apiModel.getUrl().getText();
        String method = apiModel.getRequestMethod().getName();
        ArrayList<Request> headers = apiModel.getRequestTabPanel().getRequestModel().getHeadersPanel().getRequestTable().getRequest();
        String bodyType = apiModel.getRequestTabPanel().getRequestModel().getBodyPanel().getRequestBodyType().getName();
        ArrayList<RequestBodyFormInputModel> bodyForm = apiModel.getRequestTabPanel().getRequestModel().getBodyPanel().getBodyFormPanel().getModel().getAllFormInput();
        String bodyRawType = apiModel.getRequestTabPanel().getRequestModel().getBodyPanel().getRequestBodyRawType().getName();
        String bodyRawValue = apiModel.getRequestTabPanel().getRequestModel().getBodyPanel().getRequestBodyRawValue();
        return new Collection(null, null, url, method, headers, bodyType, bodyForm, bodyRawType, bodyRawValue);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public ArrayList<Request> getHeaders() {
        return headers;
    }

    public String getBodyType() {
        return bodyType;
    }

    public ArrayList<RequestBodyFormInputModel> getBodyForm() {
        return bodyForm;
    }

    public String getBodyRawType() {
        return bodyRawType;
    }

    public String getBodyRawValue() {
        return bodyRawValue;
    }

    @Override
    public String toString() {
        String stringCollection = "Parent Collection : ".concat((getParent() != null) ? getParent().getName() : "null").concat("\n")
                .concat("Name : ").concat((getName() != null) ? getName() : "null").concat("\n")
                .concat("URL : ").concat(getUrl()).concat("\n")
                .concat("Method : ").concat(getMethod()).concat("\n")
                .concat("Header : ").concat("\n");
        for (Request header : getHeaders()) {
            stringCollection = stringCollection.concat("-").concat(" ").concat(header.getKey()).concat(" = ").concat(header.getValue()).concat("\n");
        }
        stringCollection = stringCollection.concat("Body Type : ").concat(getBodyType()).concat("\n")
                .concat("Body Form : ").concat("\n");
        for (RequestBodyFormInputModel form : getBodyForm()) {
            stringCollection = stringCollection.concat("-").concat(" ").concat("(").concat(form.getType()).concat(")").concat(" ").concat(form.getKey()).concat(" = ").concat(form.getValue()).concat("\n");
        }
        stringCollection = stringCollection.concat("Body Raw Type : ").concat(getBodyRawType()).concat("\n")
                .concat("Body Raw Value : ").concat(getBodyRawValue()).concat("\n");
        return stringCollection;
    }
}
