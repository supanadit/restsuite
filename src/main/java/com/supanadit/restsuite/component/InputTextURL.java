package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.InputTextUrlListener;
import io.reactivex.subjects.PublishSubject;

public class InputTextURL extends InputComponent {
    private PublishSubject<String> subject = PublishSubject.create();

    public InputTextURL() {
        setPlaceholder("URL");
        getDocument().addDocumentListener(new InputTextUrlListener(this, subject));
    }

    public PublishSubject<String> getSubject() {
        return subject;
    }
}
