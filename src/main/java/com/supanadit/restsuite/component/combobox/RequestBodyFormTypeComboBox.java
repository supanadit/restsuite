package com.supanadit.restsuite.component.combobox;

import com.supanadit.restsuite.model.RequestBodyFormType;
import com.supanadit.restsuite.model.RequestBodyType;
import com.supanadit.restsuite.renderer.RequestBodyFormTypeRenderer;
import com.supanadit.restsuite.renderer.RequestBodyTypeRenderer;
import okhttp3.RequestBody;

import javax.swing.*;

public class RequestBodyFormTypeComboBox extends JComboBox<RequestBodyFormType> {
    public RequestBodyFormTypeComboBox() {
        setRenderer(new RequestBodyFormTypeRenderer());
        addItem(RequestBodyFormType.FIELD());
        addItem(RequestBodyFormType.FILE());
    }
}
