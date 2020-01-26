package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.renderer.RequestBodyRawTypeRenderer;
import com.supanadit.restsuite.model.BodyRawTypeModel;

import javax.swing.*;

public class RequestBodyRawTypeComboBox extends JComboBox<BodyRawTypeModel> {
    public RequestBodyRawTypeComboBox() {
        setRenderer(new RequestBodyRawTypeRenderer());
    }

    public static RequestBodyRawTypeComboBox getComponent() {
        RequestBodyRawTypeComboBox requestBodyRawTypeComboBox = new RequestBodyRawTypeComboBox();

        requestBodyRawTypeComboBox.addItem(BodyRawTypeModel.JSON());
        requestBodyRawTypeComboBox.addItem(BodyRawTypeModel.TEXT());
        requestBodyRawTypeComboBox.addItem(BodyRawTypeModel.XML());
        requestBodyRawTypeComboBox.addItem(BodyRawTypeModel.HTML());
        requestBodyRawTypeComboBox.addItem(BodyRawTypeModel.JAVASCRIPT());

        return requestBodyRawTypeComboBox;
    }

    @Override
    public String toString() {
        BodyRawTypeModel model = (BodyRawTypeModel) getSelectedItem();
        assert model != null;
        return model.getName();
    }
}
