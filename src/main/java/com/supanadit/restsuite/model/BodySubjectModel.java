package com.supanadit.restsuite.model;

import io.reactivex.subjects.PublishSubject;

public class BodySubjectModel {
    private PublishSubject<String> bodyRaw;
    private PublishSubject<RequestBodyFormModel> requestBodyFormModelSubject;
    private PublishSubject<RequestBodyRawType> requestBodyRawTypeSubject;
    private PublishSubject<RequestBodyType> requestBodyTypeSubject;

    public BodySubjectModel(PublishSubject<String> bodyRaw, PublishSubject<RequestBodyFormModel> requestBodyFormModelSubject, PublishSubject<RequestBodyRawType> requestBodyRawTypeSubject, PublishSubject<RequestBodyType> requestBodyTypeSubject) {
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

    public PublishSubject<RequestBodyRawType> getRequestBodyRawTypeSubject() {
        return requestBodyRawTypeSubject;
    }

    public PublishSubject<RequestBodyType> getRequestBodyTypeSubject() {
        return requestBodyTypeSubject;
    }
}
