package com.supanadit.restsuite.model;

import com.supanadit.restsuite.panel.api.request.tab.header.HeadersPanel;
import com.supanadit.restsuite.panel.api.BodyPanel;
import com.supanadit.restsuite.panel.api.request.tab.param.ParamsPanel;

public class ApiRequestModel {
    private BodyPanel bodyPanel;
    private HeadersPanel headersPanel;
    private ParamsPanel paramsPanel;

    public ApiRequestModel(BodyPanel bodyPanel, HeadersPanel headersPanel, ParamsPanel paramsPanel) {
        this.bodyPanel = bodyPanel;
        this.headersPanel = headersPanel;
        this.paramsPanel = paramsPanel;
    }

    public BodyPanel getBodyPanel() {
        return bodyPanel;
    }

    public HeadersPanel getHeadersPanel() {
        return headersPanel;
    }

    public ParamsPanel getParamsPanel() {
        return paramsPanel;
    }
}
