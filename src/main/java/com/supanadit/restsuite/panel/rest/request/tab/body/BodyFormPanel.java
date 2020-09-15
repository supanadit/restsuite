package com.supanadit.restsuite.panel.rest.request.tab.body;
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

    public void updateChange() {
        formGroupPanel.updateUI();
    }
}
