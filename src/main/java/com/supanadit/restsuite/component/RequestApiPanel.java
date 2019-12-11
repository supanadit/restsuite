package com.supanadit.restsuite.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class RequestApiPanel extends JPanel {
    protected JLabel apiName;
    protected InputTextURL apiUrl;
    protected RequestApiButton sendButton;
    protected RequestTabPanel requestTabPanel;
    protected ResponseTabPanel responseTabPanel;

    public RequestApiPanel() {
        super(new MigLayout());

        this.apiName = new JLabel("API Name");
        this.apiUrl = InputTextURL.getComponent();
        this.sendButton = new RequestApiButton(this.apiUrl);
        this.requestTabPanel = new RequestTabPanel();
        this.responseTabPanel = new ResponseTabPanel();

        this.add(this.apiName, "wrap");
        this.add(this.apiUrl, "growx, pushx");
        this.add(RequestTypeComboBox.getComponent());
        this.add(this.sendButton, "wrap");

        this.add(this.requestTabPanel, "growx, pushx, span 3, wrap, hmax 250");
        this.add(this.responseTabPanel, "growx, growy, pushy, pushx, span 3");

        this.sendButton.setBodyPanel(this.responseTabPanel.body());
    }

    public RequestApiButton getSendButton() {
        return this.sendButton;
    }
}
