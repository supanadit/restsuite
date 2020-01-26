package com.supanadit.restsuite.panel.api.request.tab.header;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class HeadersFormPanel extends JScrollPane {
    public ArrayList<HeadersFormInputPanel> listInputPanel = new ArrayList<>();
    public JPanel formGroupPanel;

    public HeadersFormPanel() {
        formGroupPanel = new JPanel(new MigLayout("", "", "[]0[]"));

        JButton addField = new JButton("Add Field");
        addField.addActionListener(k -> {
            HeadersFormInputPanel bodyFormInputPanel = new HeadersFormInputPanel(this);
            formGroupPanel.remove(addField);
            formGroupPanel.add(bodyFormInputPanel, "pushx,growx,wrap");
            formGroupPanel.add(addField, "pushx,growx,wrap");
            listInputPanel.add(bodyFormInputPanel);
            updateChange();
        });
        formGroupPanel.add(addField, "pushx,growx,wrap");

        setViewportView(formGroupPanel);
    }

    public JPanel getPanel() {
        return formGroupPanel;
    }

    public void updateChange() {
        formGroupPanel.updateUI();
    }
}
