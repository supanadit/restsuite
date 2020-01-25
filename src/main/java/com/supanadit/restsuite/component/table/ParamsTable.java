package com.supanadit.restsuite.component.table;

import com.supanadit.restsuite.model.Request;
import com.supanadit.restsuite.panel.api.request.tab.header.HeadersPanel;

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
        addRow(new Request("", ""));
    }

    public void addRow(Request request) {
        addRow(request, true);
    }

    public void addRow(Request request, boolean withFocus) {
        getModel().addRow(new Object[]{request.getKey(), request.getValue()});
        if (withFocus) {
            if (getModel().getRowCount() != 0) {
                requestTable.editCellAt(getModel().getRowCount() - 1, 0);
                requestTable.requestFocus();
            }
        }
    }

    public void setFromRequestArrayList(ArrayList<Request> requestArrayList) {
        int rowCount = getModel().getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            getModel().removeRow(i);
        }

        for (Request request : requestArrayList) {
            addRow(request, false);
        }
    }

    public ArrayList<Request> getRequest() {
        ArrayList<Request> requests = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            String key = defaultTableModel.getValueAt(i, 0).toString();
            String value = defaultTableModel.getValueAt(i, 1).toString();
            requests.add(new Request(key, value));
        }
        return requests;
    }
}
