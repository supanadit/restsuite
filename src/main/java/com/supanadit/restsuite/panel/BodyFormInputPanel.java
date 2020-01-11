package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputBodyKey;
import com.supanadit.restsuite.component.InputBodyValue;
import com.supanadit.restsuite.component.RequestBodyFormTypeComboBox;
import com.supanadit.restsuite.model.RequestBodyFormInputModel;
import com.supanadit.restsuite.model.RequestBodyFormType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.io.File;

public class BodyFormInputPanel extends JPanel {
    RequestBodyFormTypeComboBox comboBox;
    InputBodyKey keyField;
    InputBodyValue valueField;

    public BodyFormInputPanel(JPanel parentPanel) {
        setLayout(new MigLayout("", "[]5[100]5[100]5[]5[]"));
        comboBox = new RequestBodyFormTypeComboBox();
        add(comboBox, "growx");
        keyField = new InputBodyKey();
        valueField = new InputBodyValue();

        add(keyField, "pushx,growx");
        add(valueField, "pushx,growx");

        JButton browseButton = new JButton("Browse");
        JButton removeButton = new JButton("X");
        removeButton.addActionListener(e -> {
            parentPanel.remove(this);
            parentPanel.updateUI();
        });

        add(browseButton, "growx");
        add(removeButton, "growx");

        browseButton.setEnabled(false);

        comboBox.addActionListener(e -> {
            RequestBodyFormType type = (RequestBodyFormType) comboBox.getSelectedItem();
            assert type != null;
            if (type.getName().equals(RequestBodyFormType.FILE().getName())) {
                browseButton.setEnabled(true);
                valueField.setEditable(false);
            } else {
                browseButton.setEnabled(false);
                valueField.setEditable(true);
            }
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
}
