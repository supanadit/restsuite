package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.model.BodyFormTypeModel;
import com.supanadit.restsuite.model.RequestTypeModel;
import com.supanadit.restsuite.renderer.RequestBodyFormTypeRenderer;

import javax.swing.*;
import java.util.ArrayList;

public class RequestBodyFormTypeComboBox extends JComboBox<BodyFormTypeModel> {
    ArrayList<BodyFormTypeModel> listBodyFormType = new ArrayList<>();

    public RequestBodyFormTypeComboBox() {
        setRenderer(new RequestBodyFormTypeRenderer());
        listBodyFormType.add(BodyFormTypeModel.FIELD());
        listBodyFormType.add(BodyFormTypeModel.FILE());

        for (BodyFormTypeModel bodyFormTypeModel : listBodyFormType) {
            addItem(bodyFormTypeModel);
        }
    }

    public RequestBodyFormTypeComboBox(String type) {
        this();
        for (BodyFormTypeModel bodyFormTypeModel : getListBodyFormType()) {
            if (bodyFormTypeModel.getName().equals(type)) {
                setSelectedItem(bodyFormTypeModel);
            }
        }
    }

    public ArrayList<BodyFormTypeModel> getListBodyFormType() {
        return listBodyFormType;
    }

    @Override
    public String toString() {
        BodyFormTypeModel model = (BodyFormTypeModel) getSelectedItem();
        assert model != null;
        return model.getName();
    }
}
