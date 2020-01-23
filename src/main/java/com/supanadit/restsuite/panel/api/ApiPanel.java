package com.supanadit.restsuite.panel.api;

import com.supanadit.restsuite.component.core.Dialog;
import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.component.button.RequestApiButton;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.model.ApiModel;
import com.supanadit.restsuite.panel.api.request.RequestTabPanel;
import com.supanadit.restsuite.panel.api.response.ResponseTabPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ApiPanel extends JPanel {
    private InputTextURL apiURL;
    private RequestTabPanel requestTabPanel;
    private ResponseTabPanel responseTabPanel;
    private RequestTypeComboBox requestTypeComboBox;

    public ApiPanel() {
        super(new MigLayout("insets 10 10 10 10"));

        JTextField apiName = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        Dialog renameAPI = new Dialog("Rename API", 400, 130);
        renameAPI.setResizable(false);

        renameAPI.add(new JLabel("Name"), "wrap");
        renameAPI.add(apiName, "pushx,growx,wrap");

        JPanel bottomPanel = new JPanel(new MigLayout("rtl, insets 0 0 0 0"));
        renameAPI.add(bottomPanel, "grow,push");
        bottomPanel.add(cancelButton);
        bottomPanel.add(saveButton);

        apiURL = new InputTextURL();

        requestTypeComboBox = new RequestTypeComboBox();
        requestTabPanel = new RequestTabPanel(apiURL.getSubject());
        responseTabPanel = new ResponseTabPanel();

        RequestApiButton sendButton = new RequestApiButton(this);

        JPanel restApiHeader = new JPanel(new MigLayout("insets 0 0 0 0"));

        add(restApiHeader, "pushx,growx,wrap");
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
