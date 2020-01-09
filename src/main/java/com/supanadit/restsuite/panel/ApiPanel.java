package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ApiPanel extends JPanel {
    protected JLabel apiName;
    protected InputTextURL apiUrl;
    protected RequestApiButton sendButton;
    protected RequestTabPanel requestTabPanel;
    protected ResponseTabPanel responseTabPanel;
    protected RequestTypeComboBox requestTypeComboBox;

    public ApiPanel() {
        super(new MigLayout("insets 10 10 0 0"));

        apiName = new JLabel("API URL");
        apiUrl = new InputTextURL();
        requestTypeComboBox = RequestTypeComboBox.getComponent();
        requestTabPanel = new RequestTabPanel(apiUrl.urlSubject);
        responseTabPanel = new ResponseTabPanel();

        sendButton = new RequestApiButton(apiUrl, requestTypeComboBox);

        add(apiName, "wrap");
        add(apiUrl, "growx, pushx");
        add(requestTypeComboBox);
        add(sendButton, "wrap");

        add(requestTabPanel, "growx, pushx, span 3, wrap");
        add(responseTabPanel, "growx, growy, pushy, pushx, span 3, h 500");

        sendButton.setBodyPanel(this.responseTabPanel.body());
    }

    public RequestApiButton getSendButton() {
        return this.sendButton;
    }
}
