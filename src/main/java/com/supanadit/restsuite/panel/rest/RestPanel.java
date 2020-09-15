package com.supanadit.restsuite.panel.rest;

import com.supanadit.restsuite.Main;
import com.supanadit.restsuite.component.button.RequestApiButton;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.model.ApiModel;
import com.supanadit.restsuite.panel.rest.callback.RestCallback;
import com.supanadit.restsuite.panel.rest.request.TabPanel;
import com.supanadit.restsuite.panel.rest.response.ResponseTabPanel;
import com.supanadit.restsuite.util.Converter;
import net.miginfocom.swing.MigLayout;
import org.apache.batik.transcoder.TranscoderException;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class RestPanel extends JPanel {
    public int id;
    public JButton titleButton;
    public TabPanel tabPanel;
    public InputTextURL apiURL;
    public ResponseTabPanel responseTabPanel;
    public RequestApiButton sendButton;
    public RequestTypeComboBox requestTypeComboBox;
    public RestCallback restCallback;

    public RestPanel() throws IOException, TranscoderException {
        super(new MigLayout("insets 10 10 10 10"));

        Icon saveIcon = svgIcon("floppy-disk.svg");
        Icon sendIcon = svgIcon("right-arrow.svg");

        apiURL = new InputTextURL();

        tabPanel = new TabPanel(apiURL);
        responseTabPanel = new ResponseTabPanel();
        requestTypeComboBox = new RequestTypeComboBox();

        sendButton = new RequestApiButton(this);
        sendButton.setIcon(sendIcon);

        JButton saveAPI = new JButton("Save");
        saveAPI.setIcon(saveIcon);

        add(apiURL, "growx, pushx");
        add(requestTypeComboBox);
        add(sendButton, "wrap");
        add(tabPanel, "growx, pushx, span 3, wrap");
        add(responseTabPanel, "growx, growy, pushy, pushx, span 3, h 500");

        sendButton.setResponseBodyPanel(this.responseTabPanel.body());
    }

    public ImageIcon getIcon(String iconName) {
        String fullIconName = "icon/".concat(iconName);
        // Get Resources URL
        URL iconURL = Main.class.getClassLoader().getResource(fullIconName);
        // Make sure the url does not return null value
        assert iconURL != null;
        // Create Image icon from URL
        return new ImageIcon(new ImageIcon(iconURL).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
    }

    public ImageIcon svgIcon(String iconName) throws IOException, TranscoderException {
        String fullIconName = "icon/svg/".concat(iconName);
        // Get Resources URL
        URL iconURL = Main.class.getClassLoader().getResource(fullIconName);
        // Make sure the url does not return null value
        assert iconURL != null;
        // Create Image icon from URL
        BufferedImage iconImage = Converter.convertSVGToPNG(iconURL.toString());
        // Convert Buffered image to Image icon
        return new ImageIcon(iconImage);
    }

    public ApiModel getModel() {
        return new ApiModel(id, titleButton, apiURL, requestTypeComboBox, tabPanel, responseTabPanel);
    }
}
