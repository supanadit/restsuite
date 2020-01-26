package com.supanadit.restsuite.panel.api.request;

import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.panel.api.request.tab.body.BodyPanel;
import com.supanadit.restsuite.panel.api.request.tab.header.HeadersPanel;
import com.supanadit.restsuite.panel.api.request.tab.param.ParamsPanel;
import io.reactivex.subjects.PublishSubject;

import javax.swing.*;

public class TabPanel extends JTabbedPane {
    public BodyPanel bodyPanel;
    public HeadersPanel headersPanel;
    public ParamsPanel paramsPanel;

    public TabPanel(InputTextURL inputTextURL) {
        paramsPanel = new ParamsPanel(inputTextURL);
        headersPanel = new HeadersPanel();
        bodyPanel = new BodyPanel(true);

        add("Query Params", paramsPanel);
        add("Header", headersPanel);
        add("Body", bodyPanel);
    }
}
