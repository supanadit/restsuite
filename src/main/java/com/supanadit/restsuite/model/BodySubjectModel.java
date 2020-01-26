package com.supanadit.restsuite.model;

import io.reactivex.subjects.PublishSubject;

public class BodySubjectModel {
    private PublishSubject<String> bodyRaw;
    private PublishSubject<RequestBodyFormModel> requestBodyFormModelSubject;
    private PublishSubject<RequestBodyRawTypeModel> requestBodyRawTypeSubject;
    private PublishSubject<RequestBodyTypeModel> requestBodyTypeSubject;

    public BodySubjectModel(PublishSubject<String> bodyRaw, PublishSubject<RequestBodyFormModel> requestBodyFormModelSubject, PublishSubject<RequestBodyRawTypeModel> requestBodyRawTypeSubject, PublishSubject<RequestBodyTypeModel> requestBodyTypeSubject) {
        this.bodyRaw = bodyRaw;
        this.requestBodyFormModelSubject = requestBodyFormModelSubject;
        this.requestBodyRawTypeSubject = requestBodyRawTypeSubject;
        this.requestBodyTypeSubject = requestBodyTypeSubject;
    }

    public PublishSubject<String> getBodyRaw() {
        return bodyRaw;
    }

    public PublishSubject<RequestBodyFormModel> getRequestBodyFormModelSubject() {
        return requestBodyFormModelSubject;
    }

    public PublishSubject<RequestBodyRawTypeModel> getRequestBodyRawTypeSubject() {
        return requestBodyRawTypeSubject;
    }

    public PublishSubject<RequestBodyTypeModel> getRequestBodyTypeSubject() {
        return requestBodyTypeSubject;
    }
}
