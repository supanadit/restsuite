package com.supanadit.restsuite.panel.rest;

import com.supanadit.restsuite.Main;
import com.supanadit.restsuite.component.button.RequestApiButton;
import com.supanadit.restsuite.component.combobox.RequestTypeComboBox;
import com.supanadit.restsuite.component.core.callback.ActionDialogCallback;
import com.supanadit.restsuite.component.input.api.InputTextURL;
import com.supanadit.restsuite.entity.*;
import com.supanadit.restsuite.model.ApiModel;
import com.supanadit.restsuite.panel.rest.callback.RestCallback;
import com.supanadit.restsuite.panel.rest.dialog.RenameApi;
import com.supanadit.restsuite.panel.rest.dialog.SaveApi;
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
    public int structureID;
    public JButton titleButton;
    public TabPanel tabPanel;
    public InputTextURL apiURL;
    public ResponseTabPanel responseTabPanel;
    public RequestApiButton sendButton;
    public RequestTypeComboBox requestTypeComboBox;
    public SaveApi saveApiDialog;
    public RestCallback restCallback;

    public RestPanel() {
        super(new MigLayout("insets 10 10 10 0"));

        RenameApi renameApiDialog = new RenameApi();
        saveApiDialog = new SaveApi();

        Icon saveIcon = getIcon("save.png");
        Icon editIcon = getIcon("edit.png");
        Icon sendIcon = getIcon("send.png");

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
            renameApiDialog.open();
            renameApiDialog.setName(titleButton.getText());
        });

        saveAPI.addActionListener(e -> {
            saveApiDialog.setVisible(true);
        });

        saveApiDialog.addAction(new ActionDialogCallback() {
            @Override
            public void cancelAction() {
                saveApiDialog.close();
            }

            @Override
            public void saveAction() {
                save();
                saveApiDialog.close();
            }
        });

        renameApiDialog.addAction(new ActionDialogCallback() {
            @Override
            public void cancelAction() {
                renameApiDialog.close();
            }

            @Override
            public void saveAction() {
                titleButton.setText(renameApiDialog.getName());
                renameApiDialog.close();
            }
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

    public ImageIcon getIcon(String iconName) {
        String fullIconName = "icon/".concat(iconName);
        // Get Resources URL
        URL iconURL = Main.class.getClassLoader().getResource(fullIconName);
        // Make sure the url does not return null value
        assert iconURL != null;
        // Create Image icon from URL
        return new ImageIcon(new ImageIcon(iconURL).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
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
            if (!saveApiDialog.isChecked()) {
                collection.setId(id);
            }
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
                CollectionHeaderEntity headerEntity = new CollectionHeaderEntity(collection, key, value);
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
                CollectionBodyEntity bodyEntity = new CollectionBodyEntity(collection, type, key, value);
                // Set Body ID
                if (body.getId() != 0) {
                    bodyEntity.setId(body.getId());
                }
                session.saveOrUpdate(bodyEntity);
                // Set the ID to the List
                body.setId(bodyEntity.getId());
            }
            // Get Folder ID
            CollectionStructureFolderEntity folder = saveApiDialog.getSelectedItem();
            // Create Structure Entity for Menu
            CollectionStructureEntity structureEntity = new CollectionStructureEntity(collection, (folder.getId() != 0) ? folder : null);
            // Set Existing ID, if exist
            if (structureID != 0) {
                if (!saveApiDialog.isChecked()) {
                    structureEntity.setId(structureID);
                }
            }
            // Save Structure Entity
            session.saveOrUpdate(structureEntity);
            // Get Structure ID
            structureID = structureEntity.getId();
            // Set Structure ID
            collection.setCollectionStructure(structureEntity);
            // Update Collection
            session.saveOrUpdate(collection);
            // commit transaction
            transaction.commit();
            // Call the Callback
            if (restCallback != null) {
                restCallback.saved();
            }
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

    public void addCallback(RestCallback restCallback) {
        this.restCallback = restCallback;
    }
}
