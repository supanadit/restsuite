package com.supanadit.restsuite.component.input.api;

import com.supanadit.restsuite.component.input.InputComponent;

public class InputBodyKey extends InputComponent {
    public InputBodyKey() {
        setPlaceholder("Key");
    }

    public InputBodyKey(String value) {
        this();
        setText(value);
    }
}
