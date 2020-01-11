package com.supanadit.restsuite.model;

import com.supanadit.restsuite.panel.RequestTabPanel;
import com.supanadit.restsuite.panel.ResponseTabPanel;

public class ApiModel {
    RequestTabPanel requestTabPanel;
    ResponseTabPanel responseTabPanel;

    public ApiModel(RequestTabPanel requestTabPanel, ResponseTabPanel responseTabPanel) {
        this.requestTabPanel = requestTabPanel;
        this.responseTabPanel = responseTabPanel;
    }

    public RequestTabPanel getRequestTabPanel() {
        return requestTabPanel;
    }

    public ResponseTabPanel getResponseTabPanel() {
        return responseTabPanel;
    }
}
