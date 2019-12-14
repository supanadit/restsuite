package com.supanadit.restsuite.component;

import io.reactivex.subjects.PublishSubject;

import javax.swing.*;

public class RequestTabPanel extends JTabbedPane {
    protected BodyPanel bodyPanel;
    protected HeadersPanel headersPanel;
    protected ParamsPanel paramsPanel;

    protected static PublishSubject<JTable> headerTable = PublishSubject.create();

    public RequestTabPanel() {
        this.bodyPanel = new BodyPanel(true);
        this.headersPanel = new HeadersPanel();
        this.paramsPanel = new ParamsPanel();

        this.headersPanel.setSubject(headerTable);

        this.add("Query Params", this.paramsPanel);
        this.add("Header", this.headersPanel);
        this.add("Body", this.bodyPanel);
    }
}
