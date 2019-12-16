package com.supanadit.restsuite.component;

import io.reactivex.subjects.PublishSubject;

import javax.swing.*;

public class RequestTabPanel extends JTabbedPane {
    protected BodyPanel bodyPanel;
    protected HeadersPanel headersPanel;
    protected ParamsPanel paramsPanel;

    protected static PublishSubject<JTable> headerTable = PublishSubject.create();
    protected static PublishSubject<String> bodyText = PublishSubject.create();

    public RequestTabPanel(PublishSubject<String> urlSubject) {
        this.paramsPanel = new ParamsPanel(urlSubject);
        this.headersPanel = new HeadersPanel();
        this.bodyPanel = new BodyPanel(true, bodyText);

        this.headersPanel.setSubject(headerTable);

        this.add("Query Params", this.paramsPanel);
        this.add("Header", this.headersPanel);
        this.add("Body", this.bodyPanel);
    }
}
