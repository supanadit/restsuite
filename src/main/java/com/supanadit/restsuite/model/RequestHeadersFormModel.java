package com.supanadit.restsuite.model;

import java.util.ArrayList;

public class RequestHeadersFormModel {
    ArrayList<RequestHeadersFormInputModel> requestBodyFormInputModels;

    public RequestHeadersFormModel(ArrayList<RequestHeadersFormInputModel> requestBodyFormInputModels) {
        this.requestBodyFormInputModels = requestBodyFormInputModels;
    }

    public ArrayList<RequestHeadersFormInputModel> getAllFormInput() {
        return requestBodyFormInputModels;
    }
}
