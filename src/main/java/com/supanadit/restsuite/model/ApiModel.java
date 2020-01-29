package com.supanadit.restsuite.model;

import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.panel.rest.request.TabPanel;
import com.supanadit.restsuite.panel.rest.response.ResponseTabPanel;

import javax.swing.*;

public class ApiModel {
    private int id;
    private JButton title;
    private InputTextURL url;
    private RequestTypeComboBox requestMethod;
    private TabPanel tabPanel;
    private ResponseTabPanel responseTabPanel;

    public ApiModel(JButton title, InputTextURL url, RequestTypeComboBox requestMethod, TabPanel tabPanel, ResponseTabPanel responseTabPanel) {
        this.title = title;
        this.url = url;
        this.requestMethod = requestMethod;
        this.tabPanel = tabPanel;
        this.responseTabPanel = responseTabPanel;
    }

    public ApiModel(int id, JButton title, InputTextURL url, RequestTypeComboBox requestMethod, TabPanel tabPanel, ResponseTabPanel responseTabPanel) {
        this(title, url, requestMethod, tabPanel, responseTabPanel);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public JButton getTitle() {
        return title;
    }

    public InputTextURL getUrl() {
        return url;
    }

    public RequestTypeComboBox getRequestMethodComboBox() {
        return requestMethod;
    }

    public RequestTypeModel getRequestMethod() {
        return (RequestTypeModel) getRequestMethodComboBox().getSelectedItem();
    }

    public TabPanel getTabPanel() {
        return tabPanel;
    }

    public ResponseTabPanel getResponseTabPanel() {
        return responseTabPanel;
    }
}
