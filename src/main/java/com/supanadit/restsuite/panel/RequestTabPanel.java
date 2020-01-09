package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.HeadersPanel;
import com.supanadit.restsuite.model.RequestBodyRawType;
import com.supanadit.restsuite.model.RequestBodyType;
import io.reactivex.subjects.PublishSubject;

import javax.swing.*;

public class RequestTabPanel extends JTabbedPane {
    public BodyPanel bodyPanel;
    public HeadersPanel headersPanel;
    public ParamsPanel paramsPanel;

    public static PublishSubject<JTable> headerTable = PublishSubject.create();
    public static PublishSubject<String> bodyText = PublishSubject.create();
    public static PublishSubject<RequestBodyRawType> requestBodyRawTypeSubject = PublishSubject.create();
    public static PublishSubject<RequestBodyType> requestBodyTypeSubject = PublishSubject.create();

    public RequestTabPanel(PublishSubject<String> urlSubject) {
        paramsPanel = new ParamsPanel(urlSubject);
        headersPanel = new HeadersPanel();
        bodyPanel = new BodyPanel(true, bodyText, requestBodyRawTypeSubject, requestBodyTypeSubject);

        headersPanel.setSubject(headerTable);

        add("Query Params", paramsPanel);
        add("Header", headersPanel);
        add("Body", bodyPanel);
    }
}
