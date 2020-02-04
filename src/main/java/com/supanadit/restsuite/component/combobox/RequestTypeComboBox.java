package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.renderer.RequestTypeRenderer;
import com.supanadit.restsuite.model.RequestTypeModel;

import javax.swing.*;
import java.util.ArrayList;

public class RequestTypeComboBox extends JComboBox<RequestTypeModel> {
    ArrayList<RequestTypeModel> listRequestType = new ArrayList<>();

    public RequestTypeComboBox() {
        setRenderer(new RequestTypeRenderer());

        listRequestType.add(RequestTypeModel.GET());
        listRequestType.add(RequestTypeModel.POST());
        listRequestType.add(RequestTypeModel.DELETE());
        listRequestType.add(RequestTypeModel.PUT());

        for (RequestTypeModel requestTypeModel : listRequestType) {
            addItem(requestTypeModel);
        }
    }

    public ArrayList<RequestTypeModel> getListRequestType() {
        return listRequestType;
    }

    @Override
    public String toString() {
        RequestTypeModel model = (RequestTypeModel) getSelectedItem();
        assert model != null;
        return model.getName();
    }
}
