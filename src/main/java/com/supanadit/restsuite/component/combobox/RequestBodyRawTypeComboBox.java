package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.model.BodyRawTypeModel;
import com.supanadit.restsuite.renderer.RequestBodyRawTypeRenderer;

import javax.swing.*;
import java.util.ArrayList;

public class RequestBodyRawTypeComboBox extends JComboBox<BodyRawTypeModel> {
    ArrayList<BodyRawTypeModel> bodyRawTypeModels = new ArrayList<>();

    public RequestBodyRawTypeComboBox() {
        setRenderer(new RequestBodyRawTypeRenderer());

        bodyRawTypeModels.add(BodyRawTypeModel.JSON());
        bodyRawTypeModels.add(BodyRawTypeModel.TEXT());
        bodyRawTypeModels.add(BodyRawTypeModel.XML());
        bodyRawTypeModels.add(BodyRawTypeModel.HTML());
        bodyRawTypeModels.add(BodyRawTypeModel.JAVASCRIPT());

        for (BodyRawTypeModel bodyRawTypeModel : bodyRawTypeModels) {
            addItem(bodyRawTypeModel);
        }
    }

    public RequestBodyRawTypeComboBox(String type) {
        this();
        setType(type);
    }


    public void setType(String type) {
        for (BodyRawTypeModel bodyRawTypeModel : getBodyRawTypeModels()) {
            if (bodyRawTypeModel.getName().equals(type)) {
                setSelectedItem(bodyRawTypeModel);
            }
        }
    }

    public ArrayList<BodyRawTypeModel> getBodyRawTypeModels() {
        return bodyRawTypeModels;
    }

    @Override
    public String toString() {
        BodyRawTypeModel model = (BodyRawTypeModel) getSelectedItem();
        assert model != null;
        return model.getName();
    }
}
