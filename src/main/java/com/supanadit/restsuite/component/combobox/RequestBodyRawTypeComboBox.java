package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.renderer.RequestBodyRawTypeRenderer;
import com.supanadit.restsuite.model.RequestBodyRawTypeModel;

import javax.swing.*;

public class RequestBodyRawTypeComboBox extends JComboBox<RequestBodyRawTypeModel> {
    public RequestBodyRawTypeComboBox() {
        setRenderer(new RequestBodyRawTypeRenderer());
    }

    public static RequestBodyRawTypeComboBox getComponent() {
        RequestBodyRawTypeComboBox requestBodyRawTypeComboBox = new RequestBodyRawTypeComboBox();

        requestBodyRawTypeComboBox.addItem(RequestBodyRawTypeModel.JSON());
        requestBodyRawTypeComboBox.addItem(RequestBodyRawTypeModel.TEXT());
        requestBodyRawTypeComboBox.addItem(RequestBodyRawTypeModel.XML());
        requestBodyRawTypeComboBox.addItem(RequestBodyRawTypeModel.HTML());
        requestBodyRawTypeComboBox.addItem(RequestBodyRawTypeModel.JAVASCRIPT());

        return requestBodyRawTypeComboBox;
    }
}
