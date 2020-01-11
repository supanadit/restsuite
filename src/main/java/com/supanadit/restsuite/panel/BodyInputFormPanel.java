package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.RequestBodyFormTypeComboBox;
import com.supanadit.restsuite.model.RequestBodyFormType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class BodyInputFormPanel extends JPanel {
    public BodyInputFormPanel(JPanel parentPanel) {
        setLayout(new MigLayout("", "[]10[]10[]"));
        RequestBodyFormTypeComboBox comboBox = new RequestBodyFormTypeComboBox();
        add(comboBox, "growx");
        JTextField keyField = new JTextField();
        JTextField valueField = new JTextField();

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
    }
}
