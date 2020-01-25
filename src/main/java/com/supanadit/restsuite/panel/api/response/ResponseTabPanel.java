package com.supanadit.restsuite.panel.api.response;

import javax.swing.*;

public class ResponseTabPanel extends JTabbedPane {
    protected ResponseBodyPanel responseBodyPanel;

    public ResponseTabPanel() {
        responseBodyPanel = new ResponseBodyPanel(false);

        add("Response", responseBodyPanel);
    }

    public ResponseBodyPanel body() {
        return responseBodyPanel;
    }
}
