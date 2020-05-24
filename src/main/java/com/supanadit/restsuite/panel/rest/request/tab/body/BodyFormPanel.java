package com.supanadit.restsuite.panel.rest.request.tab.body;

import com.supanadit.restsuite.entity.CollectionBodyEntity;
import com.supanadit.restsuite.entity.CollectionHeaderEntity;
import com.supanadit.restsuite.panel.rest.request.tab.header.HeadersFormInputPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class BodyFormPanel extends JScrollPane {
    public ArrayList<BodyFormInputPanel> listInputPanel = new ArrayList<>();
    public ArrayList<BodyFormInputPanel> listRemovedInputPanel = new ArrayList<>();

    public JPanel formGroupPanel;
    public JButton addField;

    public BodyFormPanel() {
        formGroupPanel = new JPanel(new MigLayout("", "", "[]0[]"));

        addField = new JButton("Add Field");
        addField.addActionListener(k -> {
            addFormInput(new BodyFormInputPanel(this));
        });
        formGroupPanel.add(addField, "pushx,growx,wrap");

        setViewportView(formGroupPanel);
    }

    public void addFormInput(BodyFormInputPanel bodyFormInputPanel) {
        formGroupPanel.remove(addField);
        formGroupPanel.add(bodyFormInputPanel, "pushx,growx,wrap");
        formGroupPanel.add(addField, "pushx,growx,wrap");
        listInputPanel.add(bodyFormInputPanel);
        updateChange();
    }

    public void addFormInput(CollectionBodyEntity collectionBodyEntity) {
        // Get Type
        String type = collectionBodyEntity.getType();
        // Get Key
        String key = collectionBodyEntity.getKey();
        // Get Value
        String value = collectionBodyEntity.getValue();
        // Declare body form input panel
        BodyFormInputPanel bodyFormInputPanel = new BodyFormInputPanel(this, type, key, value);
        // Set ID
        bodyFormInputPanel.setId(collectionBodyEntity.getId());
        // Add Form Input
        addFormInput(bodyFormInputPanel);
    }

    public void clearFormInput() {
        // Clone
        ArrayList<BodyFormInputPanel> listInputPanelClone = listInputPanel;
        // Clear original variable
        listInputPanel = new ArrayList<>();
        // Looping
        for (BodyFormInputPanel formInputPanel : listInputPanelClone) {
            formInputPanel.remove();
        }
        updateChange();
    }

    public void updateChange() {
        formGroupPanel.updateUI();
    }
}
