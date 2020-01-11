package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.EditableCellFocusAction;
import com.supanadit.restsuite.listener.RequestKeyboardTableRowListener;
import com.supanadit.restsuite.listener.RequestMouseScrollPaneMenuListener;
import com.supanadit.restsuite.listener.RequestMouseTableRowMenuListener;
import com.supanadit.restsuite.model.Request;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class RequestTable extends JScrollPane {
    private DefaultTableModel defaultTableModel;
    private JTable requestTable;


    public RequestTable(boolean mouseAction, boolean keyboardAction, boolean editable) {
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

        new EditableCellFocusAction(requestTable, KeyStroke.getKeyStroke("TAB"));
        new EditableCellFocusAction(requestTable, KeyStroke.getKeyStroke("shift TAB"));
        new EditableCellFocusAction(requestTable, KeyStroke.getKeyStroke("RIGHT"));
        new EditableCellFocusAction(requestTable, KeyStroke.getKeyStroke("LEFT"));
        new EditableCellFocusAction(requestTable, KeyStroke.getKeyStroke("UP"));
        new EditableCellFocusAction(requestTable, KeyStroke.getKeyStroke("DOWN"));

        if (mouseAction) {
            requestTable.addMouseListener(new RequestMouseTableRowMenuListener(this));
        }
        if (keyboardAction) {
            requestTable.addKeyListener(new RequestKeyboardTableRowListener(this));
        }

        if (mouseAction) {
            addMouseListener(new RequestMouseScrollPaneMenuListener(this));
        }
    }

    public RequestTable() {
        this(true, true, true);
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
