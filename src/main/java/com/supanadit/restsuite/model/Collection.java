package com.supanadit.restsuite.model;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Collection {
    private Collection parent;
    private String title;
    private String url;
    private String method;
    private ArrayList<RequestHeadersFormInputModel> headers;
    private String bodyType;
    private ArrayList<RequestBodyFormInputModel> bodyForm;
    private String bodyRawType;
    private String bodyRawValue;

    public Collection(@Nullable Collection parent, @Nullable String title, String url, String method, ArrayList<RequestHeadersFormInputModel> headers, String bodyType, ArrayList<RequestBodyFormInputModel> bodyForm, String bodyRawType, String bodyRawValue) {
        this.parent = parent;
        this.title = title;
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.bodyType = bodyType;
        this.bodyForm = bodyForm;
        this.bodyRawType = bodyRawType;
        this.bodyRawValue = bodyRawValue;
    }

    public Collection(String title, String url, String method, ArrayList<RequestHeadersFormInputModel> headers, String bodyType, ArrayList<RequestBodyFormInputModel> bodyForm, String bodyRawType, String bodyRawValue) {
        this(null, title, url, method, headers, bodyType, bodyForm, bodyRawType, bodyRawValue);
    }

    public static Collection fromApiModel(ApiModel apiModel) {
        String title = apiModel.getTitle().getText();
        String url = apiModel.getUrl().getText();
        String method = apiModel.getRequestMethod().getName();
        ArrayList<RequestHeadersFormInputModel> headers = apiModel.getTabPanel().getRequestModel().getHeadersPanel().getHeadersFormPanel().getModel().getAllFormInput();
        String bodyType = apiModel.getTabPanel().getRequestModel().getBodyPanel().getRequestBodyType().getName();
        ArrayList<RequestBodyFormInputModel> bodyForm = apiModel.getTabPanel().getRequestModel().getBodyPanel().getBodyFormPanel().getModel().getAllFormInput();
        String bodyRawType = apiModel.getTabPanel().getRequestModel().getBodyPanel().getRequestBodyRawType().getName();
        String bodyRawValue = apiModel.getTabPanel().getRequestModel().getBodyPanel().getRequestBodyRawValue();
        return new Collection(null, title, url, method, headers, bodyType, bodyForm, bodyRawType, bodyRawValue);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection getParent() {
        return parent;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public ArrayList<RequestHeadersFormInputModel> getHeaders() {
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
        String stringCollection = "Parent Collection : ".concat((getParent() != null) ? getParent().getTitle() : "null").concat("\n")
                .concat("Name : ").concat((getTitle() != null) ? getTitle() : "null").concat("\n")
                .concat("URL : ").concat(getUrl()).concat("\n")
                .concat("Method : ").concat(getMethod()).concat("\n")
                .concat("Header : ").concat("\n");
        for (RequestHeadersFormInputModel header : getHeaders()) {
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
