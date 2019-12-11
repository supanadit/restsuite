package com.supanadit.restsuite.component;

import javax.swing.*;

public class RequestTabPanel extends JTabbedPane {
    protected BodyPanel bodyPanel;
    protected HeadersPanel headersPanel;
    protected ParamsPanel paramsPanel;

    public RequestTabPanel() {
        this.bodyPanel = new BodyPanel(true);
        this.headersPanel = new HeadersPanel();
        this.paramsPanel = new ParamsPanel();

        this.add("Params", this.paramsPanel);
        this.add("Header", this.headersPanel);
        this.add("Body", this.bodyPanel);
    }
}
