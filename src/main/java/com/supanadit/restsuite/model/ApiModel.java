package com.supanadit.restsuite.model;

import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.panel.api.request.TabPanel;
import com.supanadit.restsuite.panel.api.response.ResponseTabPanel;

public class ApiModel {
    private InputTextURL url;
    private RequestTypeComboBox requestMethod;
    private TabPanel tabPanel;
    private ResponseTabPanel responseTabPanel;

    public ApiModel(InputTextURL url, RequestTypeComboBox requestMethod, TabPanel tabPanel, ResponseTabPanel responseTabPanel) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.tabPanel = tabPanel;
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

    public TabPanel getTabPanel() {
        return tabPanel;
    }

    public ResponseTabPanel getResponseTabPanel() {
        return responseTabPanel;
    }
}
