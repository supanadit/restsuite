package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.renderer.RequestTypeRenderer;
import com.supanadit.restsuite.model.RequestTypeModel;

import javax.swing.*;

public class RequestTypeComboBox extends JComboBox<RequestTypeModel> {
    public RequestTypeComboBox() {
        setRenderer(new RequestTypeRenderer());
        addItem(RequestTypeModel.GET());
        addItem(RequestTypeModel.POST());
        addItem(RequestTypeModel.DELETE());
        addItem(RequestTypeModel.PUT());
    }

    @Override
    public String toString() {
        RequestTypeModel model = (RequestTypeModel) getSelectedItem();
        assert model != null;
        return model.getName();
    }
}
