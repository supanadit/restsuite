package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.renderer.RequestBodyTypeRenderer;
import com.supanadit.restsuite.model.RequestBodyTypeModel;

import javax.swing.*;

public class RequestBodyTypeComboBox extends JComboBox<RequestBodyTypeModel> {
    public RequestBodyTypeComboBox() {
        setRenderer(new RequestBodyTypeRenderer());
    }

    public static RequestBodyTypeComboBox getComponent() {
        RequestBodyTypeComboBox requestBodyTypeComboBox = new RequestBodyTypeComboBox();

        requestBodyTypeComboBox.addItem(RequestBodyTypeModel.RAW());
        requestBodyTypeComboBox.addItem(RequestBodyTypeModel.FORM());

        return requestBodyTypeComboBox;
    }
}
