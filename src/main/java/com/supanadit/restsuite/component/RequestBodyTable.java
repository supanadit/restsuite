package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.EditableCellFocusAction;
import com.supanadit.restsuite.listener.RequestKeyboardTableRowListener;
import com.supanadit.restsuite.listener.RequestMouseScrollPaneMenuListener;
import com.supanadit.restsuite.listener.RequestMouseTableRowMenuListener;
import com.supanadit.restsuite.model.Request;
import io.reactivex.subjects.PublishSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class RequestBodyTable extends JScrollPane {
    protected DefaultTableModel defaultTableModel;
    protected JTable requestTable;
    protected PublishSubject<JTable> subject;


    public RequestBodyTable(boolean mouseAction, boolean keyboardAction, boolean editable) {
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
    }

    public RequestBodyTable() {
        this(true, true, true);
    }

    public void setSubject(PublishSubject<JTable> subject) {
        this.subject = subject;
    }

    public void publishTable(JTable table) {
        if (subject != null) {
            subject.onNext(table);
        }
    }

    public DefaultTableModel getModel() {
        return (DefaultTableModel) requestTable.getModel();
    }

    public void deleteSelectedRow() {
        if (!(requestTable.getSelectedRow() < 0)) {
            getModel().removeRow(requestTable.getSelectedRow());
            if (getModel().getRowCount() != 0) {
                requestTable.requestFocus();
                requestTable.changeSelection(getModel().getRowCount() - 1, 0, true, false);
            }
        }
        publishTable(requestTable);
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
        publishTable(requestTable);
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
}
