package com.supanadit.restsuite.panel.api.request.tab.header;

import com.supanadit.restsuite.model.RequestHeadersFormInputModel;
import com.supanadit.restsuite.model.RequestHeadersFormModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class HeadersFormPanel extends JScrollPane {
    private ArrayList<HeadersFormInputPanel> listInputPanel = new ArrayList<>();
    private JPanel formGroupPanel;

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

    public ArrayList<HeadersFormInputPanel> getListInputPanel() {
        return listInputPanel;
    }

    public JPanel getPanel() {
        return formGroupPanel;
    }

    public void updateChange() {
        formGroupPanel.updateUI();
    }

    public RequestHeadersFormModel getModel() {
        ArrayList<RequestHeadersFormInputModel> requestBodyFormInputModels = new ArrayList<>();
        for (HeadersFormInputPanel panel : listInputPanel) {
            requestBodyFormInputModels.add(panel.getModel());
        }
        return new RequestHeadersFormModel(requestBodyFormInputModels);
    }
}
