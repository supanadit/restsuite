package com.supanadit.restsuite.component;

import com.supanadit.restsuite.helper.RequestBodyRawTypeRenderer;
import com.supanadit.restsuite.model.RequestBodyRawType;

import javax.swing.*;

public class RequestBodyRawTypeComboBox extends JComboBox<RequestBodyRawType> {
    public RequestBodyRawTypeComboBox() {
        this.setRenderer(new RequestBodyRawTypeRenderer());
    }

    public static RequestBodyRawTypeComboBox getComponent() {
        RequestBodyRawTypeComboBox requestBodyRawTypeComboBox = new RequestBodyRawTypeComboBox();

        requestBodyRawTypeComboBox.addItem(RequestBodyRawType.JSON());
        requestBodyRawTypeComboBox.addItem(RequestBodyRawType.TEXT());
        requestBodyRawTypeComboBox.addItem(RequestBodyRawType.XML());
        requestBodyRawTypeComboBox.addItem(RequestBodyRawType.HTML());
        requestBodyRawTypeComboBox.addItem(RequestBodyRawType.JAVASCRIPT());

        return requestBodyRawTypeComboBox;
    }
}
