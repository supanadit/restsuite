package com.supanadit.restsuite.panel;

import com.jidesoft.swing.JideSplitButton;
import com.supanadit.restsuite.Main;
import com.supanadit.restsuite.component.CoreDialog;
import com.supanadit.restsuite.component.InputTextURL;
import com.supanadit.restsuite.component.RequestApiButton;
import com.supanadit.restsuite.component.RequestTypeComboBox;
import com.supanadit.restsuite.model.ApiModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ApiPanel extends JPanel {
    private InputTextURL apiURL;
    private RequestTabPanel requestTabPanel;
    private ResponseTabPanel responseTabPanel;
    private RequestTypeComboBox requestTypeComboBox;
    private CoreDialog renameAPI;
    private JTextField apiName;

    public ApiPanel() {
        super(new MigLayout("insets 10 10 10 10"));

        apiName = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        renameAPI = new CoreDialog("Rename API", 400, 130);
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

        URL saveIconURL = Main.class.getClassLoader().getResource("icon/save.png");
        URL editIconURL = Main.class.getClassLoader().getResource("icon/edit.png");

        assert saveIconURL != null;
        Icon saveIcon = new ImageIcon(new ImageIcon(saveIconURL).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
        assert editIconURL != null;
        Icon editIcon = new ImageIcon(editIconURL);

        JButton saveAPI = new JButton(saveIcon);
        JButton title = new JButton("Untitled");
        title.setSize(300, title.getHeight());
        Dimension defaultDimension = new Dimension();
        defaultDimension.setSize(200, title.getHeight());
        title.setMinimumSize(defaultDimension);

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

        restApiHeader.add(title);
        restApiHeader.add(saveAPI);
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
