package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.InputTextUrlListener;
import io.reactivex.subjects.PublishSubject;

public class InputTextURL extends InputComponent {
    public PublishSubject<String> urlSubject = PublishSubject.create();

    public InputTextURL() {
        setPlaceholder("URL");
        getDocument().addDocumentListener(new InputTextUrlListener(this, urlSubject));
    }
}
