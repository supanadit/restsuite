package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.model.RequestBodyFormTypeModel;
import com.supanadit.restsuite.renderer.RequestBodyFormTypeRenderer;

import javax.swing.*;

public class RequestBodyFormTypeComboBox extends JComboBox<RequestBodyFormTypeModel> {
    public RequestBodyFormTypeComboBox() {
        setRenderer(new RequestBodyFormTypeRenderer());
        addItem(RequestBodyFormTypeModel.FIELD());
        addItem(RequestBodyFormTypeModel.FILE());
    }
}
