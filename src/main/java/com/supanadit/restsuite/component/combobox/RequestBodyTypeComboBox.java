package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.renderer.RequestBodyTypeRenderer;
import com.supanadit.restsuite.model.BodyTypeModel;

import javax.swing.*;

public class RequestBodyTypeComboBox extends JComboBox<BodyTypeModel> {
    public RequestBodyTypeComboBox() {
        setRenderer(new RequestBodyTypeRenderer());
    }

    public static RequestBodyTypeComboBox getComponent() {
        RequestBodyTypeComboBox requestBodyTypeComboBox = new RequestBodyTypeComboBox();

        requestBodyTypeComboBox.addItem(BodyTypeModel.RAW());
        requestBodyTypeComboBox.addItem(BodyTypeModel.FORM());

        return requestBodyTypeComboBox;
    }
}
