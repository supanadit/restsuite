package com.supanadit.restsuite.panel.api;

import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.component.button.RequestApiButton;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.model.ApiModel;
import com.supanadit.restsuite.panel.api.request.TabPanel;
import com.supanadit.restsuite.panel.api.response.ResponseTabPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ApiPanel extends JPanel {
    private InputTextURL apiURL;
    private TabPanel tabPanel;
    private ResponseTabPanel responseTabPanel;
    private RequestTypeComboBox requestTypeComboBox;

    protected RequestApiButton sendButton;

    public ApiPanel() {
        super(new MigLayout("insets 10 10 10 10"));

        apiURL = new InputTextURL();

        requestTypeComboBox = new RequestTypeComboBox();
        tabPanel = new TabPanel(apiURL.getSubject());
        responseTabPanel = new ResponseTabPanel();

        sendButton = new RequestApiButton(this);

        JPanel restApiHeader = new JPanel(new MigLayout("insets 0 0 0 0"));
        restApiHeader.add(new JLabel("API URL"));

        add(restApiHeader, "pushx,growx,wrap");
        add(apiURL, "growx, pushx");
        add(requestTypeComboBox);
        add(sendButton, "wrap");

        add(tabPanel, "growx, pushx, span 3, wrap");
        add(responseTabPanel, "growx, growy, pushy, pushx, span 3, h 500");

        sendButton.setResponseBodyPanel(this.responseTabPanel.body());
    }

    public ApiModel getModel() {
        return new ApiModel(apiURL, requestTypeComboBox, tabPanel, responseTabPanel);
    }
}
