package com.supanadit.restsuite.model;

import com.supanadit.restsuite.component.HeadersPanel;
import com.supanadit.restsuite.panel.BodyPanel;
import com.supanadit.restsuite.panel.ParamsPanel;
import com.supanadit.restsuite.panel.RequestTabPanel;
import com.supanadit.restsuite.panel.ResponseTabPanel;

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
