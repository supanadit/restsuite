package com.supanadit.restsuite.panel;

import com.jidesoft.swing.JideSplitButton;
import com.supanadit.restsuite.Main;
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

    public ApiPanel() {
        super(new MigLayout("insets 10 10 10 10"));

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
        title.setIcon(editIcon);

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
