package com.supanadit.restsuite.panel.rest;

import com.supanadit.restsuite.Main;
import com.supanadit.restsuite.component.button.RequestApiButton;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.component.core.Dialog;
import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.entity.CollectionBodyEntity;
import com.supanadit.restsuite.entity.CollectionEntity;
import com.supanadit.restsuite.entity.CollectionHeaderEntity;
import com.supanadit.restsuite.model.ApiModel;
import com.supanadit.restsuite.panel.rest.request.TabPanel;
import com.supanadit.restsuite.panel.rest.request.tab.body.BodyFormInputPanel;
import com.supanadit.restsuite.panel.rest.request.tab.body.BodyFormPanel;
import com.supanadit.restsuite.panel.rest.request.tab.header.HeadersFormInputPanel;
import com.supanadit.restsuite.panel.rest.request.tab.header.HeadersFormPanel;
import com.supanadit.restsuite.panel.rest.response.ResponseTabPanel;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import net.miginfocom.swing.MigLayout;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class RestPanel extends JPanel {
    public int id;
    public JButton titleButton;
    public TabPanel tabPanel;
    public InputTextURL apiURL;
    public ResponseTabPanel responseTabPanel;
    public RequestApiButton sendButton;
    public RequestTypeComboBox requestTypeComboBox;

    public RestPanel() {
        super(new MigLayout("insets 10 10 10 0"));

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

        tabPanel = new TabPanel(apiURL);
        responseTabPanel = new ResponseTabPanel();
        requestTypeComboBox = new RequestTypeComboBox();

        sendButton = new RequestApiButton(this);
        sendButton.setIcon(sendIcon);

        JButton saveAPI = new JButton("Save");
        saveAPI.setIcon(saveIcon);

        titleButton = new JButton("Untitled");
        titleButton.setSize(300, titleButton.getHeight());
        titleButton.setIcon(editIcon);
        titleButton.setIconTextGap(5);
        titleButton.addActionListener(e -> {
            renameAPI.setVisible(true);
            apiName.setText(titleButton.getText());
        });

        cancelButton.addActionListener(e -> {
            renameAPI.setVisible(false);
        });

        saveButton.addActionListener(e -> {
            titleButton.setText(apiName.getText());
            renameAPI.setVisible(false);
        });

        saveAPI.addActionListener(e -> {
            save();
        });

        JPanel panelHeader = new JPanel(new MigLayout("insets 0 0 0 0"));
        panelHeader.add(titleButton);

        add(panelHeader, "pushx,growx,span 2");
        add(saveAPI, "wrap");

        add(apiURL, "growx, pushx");
        add(requestTypeComboBox);
        add(sendButton, "wrap");
        add(tabPanel, "growx, pushx, span 3, wrap");
        add(responseTabPanel, "growx, growy, pushy, pushx, span 3, h 500");

        sendButton.setResponseBodyPanel(this.responseTabPanel.body());
    }

    public void save() {
        // Get Title
        String title = titleButton.getText();
        // Get URL
        String url = apiURL.getText();
        // Get Request Method whether is GET, POST, PUT, or DELETE
        String method = requestTypeComboBox.toString();
        // Get Type of Body whether is Form or Raw
        String bodyType = tabPanel.bodyPanel.getRequestBodyType().getName();
        // Get All Body Form Input
        BodyFormPanel bodyForm = tabPanel.bodyPanel.bodyFormPanel;
        // Get Raw Type whether JSON, HTML, XML, Javascript, or Plain Text
        String bodyRawType = tabPanel.bodyPanel.requestBodyRawTypeComboBox.toString();
        // Get Raw Value
        String bodyRawValue = tabPanel.bodyPanel.getRequestBodyRawValue();
        // Create Collection Entity
        CollectionEntity collection = new CollectionEntity(title, url, method, bodyType, bodyRawType, bodyRawValue);
        if (id != 0) {
            collection.setId(id);
        }
        // Initialize Transaction
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the project objects
            session.saveOrUpdate(collection);
            // Set ID
            id = collection.getId();
            // Get Headers Panel
            HeadersFormPanel headers = tabPanel.headersPanel.headersFormPanel;
            // Save Headers
            for (HeadersFormInputPanel header : headers.listInputPanel) {
                // Get Key
                String key = header.getKeyField().getText();
                // Get Value
                String value = header.getValueField().getText();
                // Set ID Collection, Key Name and Value
                CollectionHeaderEntity headerEntity = new CollectionHeaderEntity(id, key, value);
                // Set Collection ID
                if (header.getId() != 0) {
                    headerEntity.setId(header.getId());
                }
                // Let Hibernate pick whether Save or Update
                session.saveOrUpdate(headerEntity);
                // Set ID on List
                header.setId(headerEntity.getId());
            }
            // Save Body
            for (BodyFormInputPanel body : bodyForm.listInputPanel) {
                // Get Type
                String type = body.getTypeComboBox().toString();
                // Get Key
                String key = body.getKeyField().getText();
                // Get Value
                String value = body.getValueField().getText();
                // Set ID Collection, Key Name and Value
                CollectionBodyEntity bodyEntity = new CollectionBodyEntity(id, type, key, value);
                // Set Body ID
                if (body.getId() != 0) {
                    bodyEntity.setId(body.getId());
                }
                session.saveOrUpdate(bodyEntity);
                // Set the ID to the List
                body.setId(bodyEntity.getId());
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public ApiModel getModel() {
        return new ApiModel(id, titleButton, apiURL, requestTypeComboBox, tabPanel, responseTabPanel);
    }
}
