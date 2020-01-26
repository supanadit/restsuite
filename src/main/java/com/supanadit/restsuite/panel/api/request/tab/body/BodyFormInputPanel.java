package com.supanadit.restsuite.panel.api.request.tab.body;

import com.supanadit.restsuite.component.input.api.InputBodyKey;
import com.supanadit.restsuite.component.input.api.InputBodyValue;
import com.supanadit.restsuite.component.combobox.RequestBodyFormTypeComboBox;
import com.supanadit.restsuite.model.BodyFormTypeModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.File;

public class BodyFormInputPanel extends JPanel implements DocumentListener {
    int id;
    RequestBodyFormTypeComboBox typeComboBox;
    InputBodyKey keyField;
    InputBodyValue valueField;
    BodyFormPanel bodyFormPanel;

    public BodyFormInputPanel(BodyFormPanel bodyFormPanel) {
        this.bodyFormPanel = bodyFormPanel;
        setLayout(new MigLayout("insets 0 0 0 0", "[]5[100]5[100]5[]5[]"));
        typeComboBox = new RequestBodyFormTypeComboBox();
        add(typeComboBox, "growx");
        keyField = new InputBodyKey();
        valueField = new InputBodyValue();

        add(keyField, "pushx,growx");
        add(valueField, "pushx,growx");

        keyField.getDocument().addDocumentListener(this);
        valueField.getDocument().addDocumentListener(this);

        JButton browseButton = new JButton("Browse");
        JButton removeButton = new JButton("X");

        removeButton.addActionListener(e -> {
            bodyFormPanel.formGroupPanel.remove(this);
            bodyFormPanel.listInputPanel.remove(this);
            bodyFormPanel.formGroupPanel.updateUI();
            bodyFormPanel.updateChange();
        });

        add(browseButton, "growx");
        add(removeButton, "growx");

        browseButton.setEnabled(false);

        typeComboBox.addActionListener(e -> {
            BodyFormTypeModel type = (BodyFormTypeModel) this.typeComboBox.getSelectedItem();
            assert type != null;
            valueField.setText(null);
            boolean isFile = type.getName().equals(BodyFormTypeModel.FILE().getName());
            browseButton.setEnabled(isFile);
            valueField.setEditable(!isFile);
        });

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                valueField.setText(selectedFile.getAbsolutePath());
            }
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RequestBodyFormTypeComboBox getTypeComboBox() {
        return typeComboBox;
    }

    public InputBodyKey getKeyField() {
        return keyField;
    }

    public void setKeyField(InputBodyKey keyField) {
        this.keyField = keyField;
    }

    public InputBodyValue getValueField() {
        return valueField;
    }

    public void setValueField(InputBodyValue valueField) {
        this.valueField = valueField;
    }

    public BodyFormPanel getBodyFormPanel() {
        return bodyFormPanel;
    }

    public void setBodyFormPanel(BodyFormPanel bodyFormPanel) {
        this.bodyFormPanel = bodyFormPanel;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        bodyFormPanel.updateChange();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        bodyFormPanel.updateChange();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        bodyFormPanel.updateChange();
    }
}
