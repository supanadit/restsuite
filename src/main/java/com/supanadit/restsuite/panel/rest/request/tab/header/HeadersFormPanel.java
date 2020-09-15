package com.supanadit.restsuite.panel.rest.request.tab.header;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class HeadersFormPanel extends JScrollPane {
    public ArrayList<HeadersFormInputPanel> listInputPanel = new ArrayList<>();
    public ArrayList<HeadersFormInputPanel> listRemovedInputPanel = new ArrayList<>();

    public JPanel formGroupPanel;
    public JButton addField;

    public HeadersFormPanel() {
        formGroupPanel = new JPanel(new MigLayout("", "", "[]0[]"));

        addField = new JButton("Add Field");
        addField.addActionListener(k -> {
            addFormInput(new HeadersFormInputPanel(this));
        });
        formGroupPanel.add(addField, "pushx,growx,wrap");

        setViewportView(formGroupPanel);
    }

    public JPanel getPanel() {
        return formGroupPanel;
    }

    public void addFormInput(HeadersFormInputPanel headersFormInputPanel) {
        // remove button add field
        formGroupPanel.remove(addField);
        // add form input
        formGroupPanel.add(headersFormInputPanel, "pushx,growx,wrap");
        // add back the button add field
        formGroupPanel.add(addField, "pushx,growx,wrap");
        // add to list input
        listInputPanel.add(headersFormInputPanel);
        // refresh ui
        updateChange();
    }

    public void updateChange() {
        formGroupPanel.updateUI();
    }
}
