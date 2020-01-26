package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.model.BodyFormTypeModel;
import com.supanadit.restsuite.renderer.RequestBodyFormTypeRenderer;

import javax.swing.*;

public class RequestBodyFormTypeComboBox extends JComboBox<BodyFormTypeModel> {
    public RequestBodyFormTypeComboBox() {
        setRenderer(new RequestBodyFormTypeRenderer());
        addItem(BodyFormTypeModel.FIELD());
        addItem(BodyFormTypeModel.FILE());
    }

    @Override
    public String toString() {
        BodyFormTypeModel model = (BodyFormTypeModel) getSelectedItem();
        assert model != null;
        return model.getName();
    }
}
