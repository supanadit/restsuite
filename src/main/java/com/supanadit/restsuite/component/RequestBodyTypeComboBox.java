package com.supanadit.restsuite.component;

import com.supanadit.restsuite.renderer.RequestBodyTypeRenderer;
import com.supanadit.restsuite.model.RequestBodyType;

import javax.swing.*;

public class RequestBodyTypeComboBox extends JComboBox<RequestBodyType> {
    public RequestBodyTypeComboBox() {
        setRenderer(new RequestBodyTypeRenderer());
    }

    public static RequestBodyTypeComboBox getComponent() {
        RequestBodyTypeComboBox requestBodyTypeComboBox = new RequestBodyTypeComboBox();

        requestBodyTypeComboBox.addItem(RequestBodyType.RAW());
        requestBodyTypeComboBox.addItem(RequestBodyType.FORM());

        return requestBodyTypeComboBox;
    }
}
