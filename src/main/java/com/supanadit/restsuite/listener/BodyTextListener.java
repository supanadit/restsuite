package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.BodyTextArea;
import io.reactivex.subjects.PublishSubject;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BodyTextListener implements DocumentListener {
    BodyTextArea bodyTextArea;
    public PublishSubject<String> subject;

    public BodyTextListener(BodyTextArea bodyTextArea, PublishSubject<String> subject) {
        this.bodyTextArea = bodyTextArea;
        this.subject = subject;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        valueChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        valueChanged();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        valueChanged();
    }

    protected void valueChanged() {
        subject.onNext(bodyTextArea.getText());
    }
}
