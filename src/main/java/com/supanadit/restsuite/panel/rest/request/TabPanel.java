package com.supanadit.restsuite.panel.rest.request;

import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.panel.rest.request.tab.body.BodyPanel;
import com.supanadit.restsuite.panel.rest.request.tab.header.HeadersPanel;
import com.supanadit.restsuite.panel.rest.request.tab.param.ParamsPanel;

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
