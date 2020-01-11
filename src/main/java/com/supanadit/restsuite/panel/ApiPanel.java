package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.*;
import com.supanadit.restsuite.model.ApiModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ApiPanel extends JPanel {
    protected JLabel apiName;
    protected InputTextURL apiURL;
    protected RequestApiButton sendButton;
    protected RequestTabPanel requestTabPanel;
    protected ResponseTabPanel responseTabPanel;
    protected RequestTypeComboBox requestTypeComboBox;

    public ApiPanel() {
        super(new MigLayout("insets 10 10 0 0"));

        apiName = new JLabel("API URL");
        apiURL = new InputTextURL();

        requestTypeComboBox = new RequestTypeComboBox();
        requestTabPanel = new RequestTabPanel(getInputURL().getSubject());
        responseTabPanel = new ResponseTabPanel();

        sendButton = new RequestApiButton(this);

        add(apiName, "wrap");
        add(apiURL, "growx, pushx");
        add(requestTypeComboBox);
        add(sendButton, "wrap");

        add(requestTabPanel, "growx, pushx, span 3, wrap");
        add(responseTabPanel, "growx, growy, pushy, pushx, span 3, h 500");

        sendButton.setBodyPanel(this.responseTabPanel.body());
    }

    public ApiModel getModel() {
        return new ApiModel(requestTabPanel, responseTabPanel);
    }

    public RequestTypeComboBox getRequestTypeComboBox() {
        return requestTypeComboBox;
    }

    public InputTextURL getInputURL() {
        return apiURL;
    }
}
