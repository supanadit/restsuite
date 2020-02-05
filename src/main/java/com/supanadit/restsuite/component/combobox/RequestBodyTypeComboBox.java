package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.model.BodyTypeModel;
import com.supanadit.restsuite.renderer.RequestBodyTypeRenderer;

import javax.swing.*;
import java.util.ArrayList;

public class RequestBodyTypeComboBox extends JComboBox<BodyTypeModel> {
    ArrayList<BodyTypeModel> bodyTypeModels = new ArrayList<>();

    public RequestBodyTypeComboBox() {
        setRenderer(new RequestBodyTypeRenderer());

        bodyTypeModels.add(BodyTypeModel.RAW());
        bodyTypeModels.add(BodyTypeModel.FORM());

        for (BodyTypeModel bodyTypeModel : bodyTypeModels) {
            addItem(bodyTypeModel);
        }
    }

    public RequestBodyTypeComboBox(String type) {
        this();
        setType(type);
    }

    public void setType(String type) {
        for (BodyTypeModel bodyTypeModel : getBodyTypeModels()) {
            if (bodyTypeModel.getName().equals(type)) {
                setSelectedItem(bodyTypeModel);
            }
        }
    }

    public ArrayList<BodyTypeModel> getBodyTypeModels() {
        return bodyTypeModels;
    }
}
