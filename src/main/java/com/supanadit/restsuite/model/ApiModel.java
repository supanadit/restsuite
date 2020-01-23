package com.supanadit.restsuite.model;

import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.panel.api.request.RequestTabPanel;
import com.supanadit.restsuite.panel.api.response.ResponseTabPanel;

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

    public RequestTypeComboBox getRequestMethodComboBox() {
        return requestMethod;
    }

    public RequestType getRequestMethod() {
        return (RequestType) getRequestMethodComboBox().getSelectedItem();
    }

    public RequestTabPanel getRequestTabPanel() {
        return requestTabPanel;
    }

    public ResponseTabPanel getResponseTabPanel() {
        return responseTabPanel;
    }
}
