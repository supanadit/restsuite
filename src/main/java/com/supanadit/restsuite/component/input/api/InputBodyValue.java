package com.supanadit.restsuite.component.input.api;

import com.supanadit.restsuite.component.input.InputComponent;

public class InputBodyValue extends InputComponent {
    public InputBodyValue() {
        setPlaceholder("Value");
    }

    public InputBodyValue(String value) {
        this();
        setText(value);
    }
}
