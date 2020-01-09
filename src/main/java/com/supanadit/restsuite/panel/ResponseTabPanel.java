package com.supanadit.restsuite.panel;

import javax.swing.*;

public class ResponseTabPanel extends JTabbedPane {
    protected BodyPanel bodyPanel;

    public ResponseTabPanel() {
        this.bodyPanel = new BodyPanel(false, null, null, null);

        this.add("Response", this.bodyPanel);
    }

    public BodyPanel body() {
        return this.bodyPanel;
    }
}
