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

        this.apiName = new JLabel("API URL");
        this.apiUrl = new InputTextURL();
        this.requestTypeComboBox = RequestTypeComboBox.getComponent();
        this.requestTabPanel = new RequestTabPanel(apiUrl.urlSubject);
        this.responseTabPanel = new ResponseTabPanel();

        this.sendButton = new RequestApiButton(this.apiUrl, this.requestTypeComboBox);

        this.add(this.apiName, "wrap");
        this.add(this.apiUrl, "growx, pushx");
        this.add(this.requestTypeComboBox);
        this.add(this.sendButton, "wrap");

        this.add(this.requestTabPanel, "growx, pushx, span 3, wrap");
        this.add(this.responseTabPanel, "growx, growy, pushy, pushx, span 3, h 500");

        this.sendButton.setBodyPanel(this.responseTabPanel.body());
    }

    public RequestApiButton getSendButton() {
        return this.sendButton;
    }
}
