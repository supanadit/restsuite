package com.supanadit.restsuite.model;

import com.supanadit.restsuite.component.InputTextURL;
import com.supanadit.restsuite.component.RequestTypeComboBox;
import com.supanadit.restsuite.panel.RequestTabPanel;
import com.supanadit.restsuite.panel.ResponseTabPanel;

public class ApiModel {
    private InputTextURL url;
    private RequestTypeComboBox requestMethod;
    private RequestTabPanel requestTabPanel;
    private ResponseTabPanel responseTabPanel;

    public ApiModel(InputTextURL url, RequestTypeComboBox requestMethod, RequestTabPanel requestTabPanel, ResponseTabPanel responseTabPanel) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.requestTabPanel = requestTabPanel;
        this.responseTabPanel = responseTabPanel;
    }

    public InputTextURL getUrl() {
        return url;
    }

    public RequestTypeComboBox getRequestMethod() {
        return requestMethod;
    }

    public RequestTabPanel getRequestTabPanel() {
        return requestTabPanel;
    }

    public ResponseTabPanel getResponseTabPanel() {
        return responseTabPanel;
    }
}
