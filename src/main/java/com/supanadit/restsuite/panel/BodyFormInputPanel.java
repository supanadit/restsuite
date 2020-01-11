package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputBodyKey;
import com.supanadit.restsuite.component.InputBodyValue;
import com.supanadit.restsuite.component.RequestBodyFormTypeComboBox;
import com.supanadit.restsuite.model.RequestBodyFormInputModel;
import com.supanadit.restsuite.model.RequestBodyFormType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.File;

public class BodyFormInputPanel extends JPanel implements DocumentListener {
    RequestBodyFormTypeComboBox comboBox;
    InputBodyKey keyField;
    InputBodyValue valueField;
    BodyFormPanel bodyFormPanel;

    public BodyFormInputPanel(BodyFormPanel bodyFormPanel) {
        this.bodyFormPanel = bodyFormPanel;
        setLayout(new MigLayout("insets 0 0 0 0", "[]5[100]5[100]5[]5[]"));
        comboBox = new RequestBodyFormTypeComboBox();
        add(comboBox, "growx");
        keyField = new InputBodyKey();
        valueField = new InputBodyValue();

        add(keyField, "pushx,growx");
        add(valueField, "pushx,growx");

        keyField.getDocument().addDocumentListener(this);
        valueField.getDocument().addDocumentListener(this);

        JButton browseButton = new JButton("Browse");
        JButton removeButton = new JButton("X");

        removeButton.addActionListener(e -> {
            bodyFormPanel.getPanel().remove(this);
            bodyFormPanel.getListInputPanel().remove(this);
            bodyFormPanel.getPanel().updateUI();
            bodyFormPanel.updateChange();
        });

        add(browseButton, "growx");
        add(removeButton, "growx");

        browseButton.setEnabled(false);

        comboBox.addActionListener(e -> {
            RequestBodyFormType type = (RequestBodyFormType) comboBox.getSelectedItem();
            assert type != null;
            valueField.setText(null);
            boolean isFile = type.getName().equals(RequestBodyFormType.FILE().getName());
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

    public RequestBodyFormInputModel getModel() {
        RequestBodyFormType comboBoxType = (RequestBodyFormType) comboBox.getSelectedItem();
        assert comboBoxType != null;
        String type = comboBoxType.getName();
        String key = keyField.getText();
        String value = valueField.getText();
        return new RequestBodyFormInputModel(type, key, value);
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
