package com.supanadit.restsuite.panel;

import javax.swing.*;

public class ResponseTabPanel extends JTabbedPane {
    protected BodyPanel bodyPanel;

    public ResponseTabPanel() {
        bodyPanel = new BodyPanel(false);

        add("Response", bodyPanel);
    }

    public BodyPanel body() {
        return bodyPanel;
    }
}
