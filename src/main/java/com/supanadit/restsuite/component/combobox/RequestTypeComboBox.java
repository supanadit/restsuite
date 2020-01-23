package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.renderer.RequestTypeRenderer;
import com.supanadit.restsuite.model.RequestType;

import javax.swing.*;

public class RequestTypeComboBox extends JComboBox<RequestType> {
    public RequestTypeComboBox() {
        setRenderer(new RequestTypeRenderer());
        addItem(RequestType.GET());
        addItem(RequestType.POST());
        addItem(RequestType.DELETE());
        addItem(RequestType.PUT());
    }
}
