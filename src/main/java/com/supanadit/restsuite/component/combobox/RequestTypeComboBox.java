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
}
