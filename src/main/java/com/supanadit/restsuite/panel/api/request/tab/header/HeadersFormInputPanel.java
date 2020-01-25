package com.supanadit.restsuite.panel.api.request.tab.header;

import com.supanadit.restsuite.component.input.api.InputBodyKey;
import com.supanadit.restsuite.component.input.api.InputBodyValue;
import com.supanadit.restsuite.model.RequestHeadersFormInputModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HeadersFormInputPanel extends JPanel implements DocumentListener {
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
            headersFormPanel.getListInputPanel().remove(this);
            headersFormPanel.getPanel().updateUI();
            headersFormPanel.updateChange();
        });

        add(removeButton);
    }

    public RequestHeadersFormInputModel getModel() {
        String key = keyField.getText();
        String value = valueField.getText();
        return new RequestHeadersFormInputModel(key, value);
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
