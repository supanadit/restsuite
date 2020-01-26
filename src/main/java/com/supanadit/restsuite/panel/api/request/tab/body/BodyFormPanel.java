package com.supanadit.restsuite.panel.api.request.tab.body;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class BodyFormPanel extends JScrollPane {
    public ArrayList<BodyFormInputPanel> listInputPanel = new ArrayList<>();
    public JPanel formGroupPanel;

    public BodyFormPanel() {
        formGroupPanel = new JPanel(new MigLayout("", "", "[]0[]"));

        JButton addField = new JButton("Add Field");
        addField.addActionListener(k -> {
            BodyFormInputPanel bodyFormInputPanel = new BodyFormInputPanel(this);
            formGroupPanel.remove(addField);
            formGroupPanel.add(bodyFormInputPanel, "pushx,growx,wrap");
            formGroupPanel.add(addField, "pushx,growx,wrap");
            listInputPanel.add(bodyFormInputPanel);
            updateChange();
        });
        formGroupPanel.add(addField, "pushx,growx,wrap");

        setViewportView(formGroupPanel);
    }

    public void updateChange() {
        formGroupPanel.updateUI();
    }
}
