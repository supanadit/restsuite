package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.model.RequestBodyFormInputModel;
import com.supanadit.restsuite.model.RequestBodyFormModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class BodyFormPanel extends JScrollPane {
    private ArrayList<BodyFormInputPanel> listInputPanel = new ArrayList<>();
    private JPanel formGroupPanel;

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

    public ArrayList<BodyFormInputPanel> getListInputPanel() {
        return listInputPanel;
    }

    public JPanel getPanel() {
        return formGroupPanel;
    }

    public void updateChange() {
        formGroupPanel.updateUI();
    }

    public RequestBodyFormModel getModel() {
        ArrayList<RequestBodyFormInputModel> requestBodyFormInputModels = new ArrayList<>();
        for (BodyFormInputPanel panel : listInputPanel) {
            requestBodyFormInputModels.add(panel.getModel());
        }
        return new RequestBodyFormModel(requestBodyFormInputModels);
    }
}
