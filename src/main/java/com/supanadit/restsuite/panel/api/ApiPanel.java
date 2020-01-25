package com.supanadit.restsuite.panel.api;

import com.supanadit.restsuite.Main;
import com.supanadit.restsuite.component.core.Dialog;
import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.component.button.RequestApiButton;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.model.ApiModel;
import com.supanadit.restsuite.model.Collection;
import com.supanadit.restsuite.panel.api.request.TabPanel;
import com.supanadit.restsuite.panel.api.response.ResponseTabPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.*;
import java.net.URL;

public class ApiPanel extends JPanel {
    private InputTextURL apiURL;
    private TabPanel tabPanel;
    private ResponseTabPanel responseTabPanel;
    private RequestTypeComboBox requestTypeComboBox;

    protected RequestApiButton sendButton;
    protected JButton title;

    public ApiPanel() {
        super(new MigLayout("insets 10 10 10 10"));

        JTextField apiName = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        Dialog renameAPI = new Dialog("Rename API", 400, 130);
        renameAPI.setResizable(false);

        JPanel bottomPanel = new JPanel(new MigLayout("rtl, insets 0 0 0 0"));
        bottomPanel.add(cancelButton);
        bottomPanel.add(saveButton);

        renameAPI.add(new JLabel("Name"), "wrap");
        renameAPI.add(apiName, "pushx,growx,wrap");
        renameAPI.add(bottomPanel, "grow,push");

        URL saveIconURL = Main.class.getClassLoader().getResource("icon/save.png");
        URL editIconURL = Main.class.getClassLoader().getResource("icon/edit.png");
        URL sendIconURL = Main.class.getClassLoader().getResource("icon/send.png");

        assert saveIconURL != null;
        assert editIconURL != null;
        assert sendIconURL != null;

        Icon saveIcon = new ImageIcon(new ImageIcon(saveIconURL).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
        Icon editIcon = new ImageIcon(editIconURL);
        Icon sendIcon = new ImageIcon(sendIconURL);

        apiURL = new InputTextURL();

        requestTypeComboBox = new RequestTypeComboBox();
        tabPanel = new TabPanel(apiURL.getSubject());
        responseTabPanel = new ResponseTabPanel();

        sendButton = new RequestApiButton(this);
        sendButton.setIcon(sendIcon);

        JButton saveAPI = new JButton("Save");
        saveAPI.setIcon(saveIcon);

        title = new JButton("Untitled");
        title.setSize(300, title.getHeight());

        title.setIcon(editIcon);
        title.setIconTextGap(5);
        title.addActionListener(e -> {
            renameAPI.setVisible(true);
            apiName.setText(title.getText());
        });

        cancelButton.addActionListener(e -> {
            renameAPI.setVisible(false);
        });

        saveButton.addActionListener(e -> {
            title.setText(apiName.getText());
            renameAPI.setVisible(false);
        });

        saveAPI.addActionListener(e -> {
            System.out.println(title.getText());
            Collection collection = Collection.fromApiModel(getModel());
            System.out.println(collection.toString());
        });

        JPanel panelHeader = new JPanel(new MigLayout("insets 0 0 0 0"));
        panelHeader.add(title);

        add(panelHeader, "pushx,growx,span 2");
        add(saveAPI, "wrap");

        add(apiURL, "growx, pushx");
        add(requestTypeComboBox);
        add(sendButton, "wrap");
        add(tabPanel, "growx, pushx, span 3, wrap");
        add(responseTabPanel, "growx, growy, pushy, pushx, span 3, h 500");

        sendButton.setResponseBodyPanel(this.responseTabPanel.body());
    }

    public ApiModel getModel() {
        return new ApiModel(title, apiURL, requestTypeComboBox, tabPanel, responseTabPanel);
    }
}
