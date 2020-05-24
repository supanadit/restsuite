package com.supanadit.restsuite.component.table;

import com.supanadit.restsuite.model.RequestModel;
import com.supanadit.restsuite.panel.rest.request.tab.header.HeadersPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ParamsTable extends JScrollPane {
    private DefaultTableModel defaultTableModel;
    private JTable requestTable;

    public ParamsTable(boolean editable, HeadersPanel headersPanel) {
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
        };

        defaultTableModel.addColumn("Key");
        defaultTableModel.addColumn("Value");

        requestTable = new JTable(defaultTableModel);

        setViewportView(requestTable);

        requestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public ParamsTable(HeadersPanel headersPanel) {
        this(true, headersPanel);
    }

    public DefaultTableModel getModel() {
        return defaultTableModel;
    }

    public void deleteSelectedRow() {
        if (!(requestTable.getSelectedRow() < 0)) {
            getModel().removeRow(requestTable.getSelectedRow());
            if (getModel().getRowCount() != 0) {
                requestTable.requestFocus();
                requestTable.changeSelection(getModel().getRowCount() - 1, 0, true, false);
            }
        }
    }

    public void addNewEmptyRow() {
        addRow(new RequestModel("", ""));
    }

    public void addRow(RequestModel requestModel) {
        addRow(requestModel, true);
    }

    public void addRow(RequestModel requestModel, boolean withFocus) {
        getModel().addRow(new Object[]{requestModel.getKey(), requestModel.getValue()});
        if (withFocus) {
            if (getModel().getRowCount() != 0) {
                requestTable.editCellAt(getModel().getRowCount() - 1, 0);
                requestTable.requestFocus();
            }
        }
    }

    public void setFromRequestArrayList(ArrayList<RequestModel> requestModelArrayList) {
        int rowCount = getModel().getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            getModel().removeRow(i);
        }

        for (RequestModel requestModel : requestModelArrayList) {
            addRow(requestModel, false);
        }
    }

    public ArrayList<RequestModel> getRequest() {
        ArrayList<RequestModel> requestModels = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            String key = defaultTableModel.getValueAt(i, 0).toString();
            String value = defaultTableModel.getValueAt(i, 1).toString();
            requestModels.add(new RequestModel(key, value));
        }
        return requestModels;
    }
}
