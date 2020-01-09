package com.supanadit.restsuite.component;

import com.supanadit.restsuite.renderer.RequestTypeRenderer;
import com.supanadit.restsuite.model.RequestType;

import javax.swing.*;

public class RequestTypeComboBox extends JComboBox<RequestType> {
    public RequestTypeComboBox() {
        setRenderer(new RequestTypeRenderer());
    }

    public static RequestTypeComboBox getComponent() {
        RequestTypeComboBox requestTypeComboBox = new RequestTypeComboBox();

        requestTypeComboBox.addItem(RequestType.GET());
        requestTypeComboBox.addItem(RequestType.POST());
        requestTypeComboBox.addItem(RequestType.DELETE());
        requestTypeComboBox.addItem(RequestType.PUT());

        return requestTypeComboBox;
    }
}
