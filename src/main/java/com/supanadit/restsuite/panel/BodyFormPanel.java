package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.model.RequestBodyFormInputModel;
import com.supanadit.restsuite.model.RequestBodyFormModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class BodyFormPanel extends JScrollPane {
    ArrayList<BodyFormInputPanel> listInputPanel = new ArrayList<>();

    public BodyFormPanel() {
        JPanel formGroupPanel = new JPanel(new MigLayout());

        JButton addField = new JButton("Add Field");
        addField.addActionListener(k -> {
            BodyFormInputPanel bodyFormInputPanel = new BodyFormInputPanel(formGroupPanel);
            formGroupPanel.remove(addField);
            formGroupPanel.add(bodyFormInputPanel, "pushx,growx,wrap");
            formGroupPanel.add(addField, "pushx,growx,wrap");
            formGroupPanel.updateUI();

            listInputPanel.add(bodyFormInputPanel);
        });
        formGroupPanel.add(addField, "pushx,growx,wrap");

        setViewportView(formGroupPanel);
    }

    public RequestBodyFormModel getModel() {
        ArrayList<RequestBodyFormInputModel> requestBodyFormInputModels = new ArrayList<>();
        for (BodyFormInputPanel panel : listInputPanel) {
            requestBodyFormInputModels.add(panel.getModel());
        }
        return new RequestBodyFormModel(requestBodyFormInputModels);
    }
}
