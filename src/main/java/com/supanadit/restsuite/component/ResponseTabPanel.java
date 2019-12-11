package com.supanadit.restsuite.component;

import javax.swing.*;

public class ResponseTabPanel extends JTabbedPane {
    protected BodyPanel bodyPanel;

    public ResponseTabPanel() {
        this.bodyPanel = new BodyPanel(false);

        this.add("Response", this.bodyPanel);
    }

    public BodyPanel body() {
        return this.bodyPanel;
    }
}
