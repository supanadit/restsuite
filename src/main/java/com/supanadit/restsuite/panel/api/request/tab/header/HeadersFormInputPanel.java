package com.supanadit.restsuite.panel.api.request.tab.header;

import com.supanadit.restsuite.component.input.api.InputBodyKey;
import com.supanadit.restsuite.component.input.api.InputBodyValue;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HeadersFormInputPanel extends JPanel implements DocumentListener {
    int id;
    InputBodyKey keyField;
    InputBodyValue valueField;
    HeadersFormPanel headersFormPanel;

    public HeadersFormInputPanel(HeadersFormPanel headersFormPanel) {
        this.headersFormPanel = headersFormPanel;
        setLayout(new MigLayout("insets 0 0 0 0", "[135]5[100]5[]"));

        keyField = new InputBodyKey();
        valueField = new InputBodyValue();

        add(keyField, "pushx,growx");
        add(valueField, "pushx,growx");

        keyField.getDocument().addDocumentListener(this);
        valueField.getDocument().addDocumentListener(this);

        JButton removeButton = new JButton("X");

        removeButton.addActionListener(e -> {
            headersFormPanel.getPanel().remove(this);
            headersFormPanel.listInputPanel.remove(this);
            headersFormPanel.getPanel().updateUI();
            headersFormPanel.updateChange();
        });

        add(removeButton);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InputBodyKey getKeyField() {
        return keyField;
    }

    public InputBodyValue getValueField() {
        return valueField;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        headersFormPanel.updateChange();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        headersFormPanel.updateChange();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        headersFormPanel.updateChange();
    }
}
