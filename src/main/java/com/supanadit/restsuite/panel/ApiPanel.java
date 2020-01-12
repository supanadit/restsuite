package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputTextURL;
import com.supanadit.restsuite.component.RequestApiButton;
import com.supanadit.restsuite.component.RequestTypeComboBox;
import com.supanadit.restsuite.model.ApiModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ApiPanel extends JPanel {
    private InputTextURL apiURL;
    private RequestTabPanel requestTabPanel;
    private ResponseTabPanel responseTabPanel;
    private RequestTypeComboBox requestTypeComboBox;

    public ApiPanel() {
        super(new MigLayout("insets 10 10 0 0"));

        JLabel apiName = new JLabel("API URL");
        apiURL = new InputTextURL();

        requestTypeComboBox = new RequestTypeComboBox();
        requestTabPanel = new RequestTabPanel(apiURL.getSubject());
        responseTabPanel = new ResponseTabPanel();

        RequestApiButton sendButton = new RequestApiButton(this);

        add(apiName, "wrap");
        add(apiURL, "growx, pushx");
        add(requestTypeComboBox);
        add(sendButton, "wrap");

        add(requestTabPanel, "growx, pushx, span 3, wrap");
        add(responseTabPanel, "growx, growy, pushy, pushx, span 3, h 500");

        sendButton.setBodyPanel(this.responseTabPanel.body());
    }

    public ApiModel getModel() {
        return new ApiModel(apiURL, requestTypeComboBox, requestTabPanel, responseTabPanel);
    }
}
