package com.supanadit.restsuite.listener;

import io.reactivex.subjects.PublishSubject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InputTextUrlListener implements DocumentListener {
    JTextField textField;
    public PublishSubject<String> subject;

    public InputTextUrlListener(JTextField textField, PublishSubject<String> subject) {
        this.textField = textField;
        this.subject = subject;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.valueChanged();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.valueChanged();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        this.valueChanged();
    }

    protected void valueChanged() {
        this.subject.onNext(this.textField.getText());
    }
}
