package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.HeadersPanel;
import com.supanadit.restsuite.model.*;
import io.reactivex.subjects.PublishSubject;

import javax.swing.*;

public class RequestTabPanel extends JTabbedPane {
    private BodyPanel bodyPanel;
    private HeadersPanel headersPanel;
    private ParamsPanel paramsPanel;

    public RequestTabPanel(PublishSubject<String> urlSubject) {
        paramsPanel = new ParamsPanel(urlSubject);
        headersPanel = new HeadersPanel();
        bodyPanel = new BodyPanel(true);

        add("Query Params", paramsPanel);
        add("Header", headersPanel);
        add("Body", bodyPanel);
    }

    public ApiRequestModel getRequestModel() {
        return new ApiRequestModel(bodyPanel, headersPanel, paramsPanel);
    }
}
