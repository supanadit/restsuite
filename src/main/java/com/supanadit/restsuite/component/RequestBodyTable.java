package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.EditableCellFocusAction;
import com.supanadit.restsuite.model.Request;
import io.reactivex.subjects.PublishSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

public class RequestBodyTable extends JScrollPane {
    protected DefaultTableModel defaultTableModel;
    protected JTable requestBodyTable;
    protected PublishSubject<JTable> subject;


    public RequestBodyTable(boolean mouseAction, boolean keyboardAction, boolean editable) {
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
        };

        defaultTableModel.addColumn("Type");
        defaultTableModel.addColumn("Key");
        defaultTableModel.addColumn("Value");

        requestBodyTable = new JTable(defaultTableModel);

        setViewportView(requestBodyTable);

        TableColumn comboCol1 = requestBodyTable.getColumnModel().getColumn(0);
        comboCol1.setCellEditor(new RequestBodyComboBoxTypeColumn());

        addNewEmptyRow();

        new EditableCellFocusAction(requestBodyTable, KeyStroke.getKeyStroke("TAB"));
        new EditableCellFocusAction(requestBodyTable, KeyStroke.getKeyStroke("shift TAB"));
        new EditableCellFocusAction(requestBodyTable, KeyStroke.getKeyStroke("RIGHT"));
        new EditableCellFocusAction(requestBodyTable, KeyStroke.getKeyStroke("LEFT"));
        new EditableCellFocusAction(requestBodyTable, KeyStroke.getKeyStroke("UP"));
        new EditableCellFocusAction(requestBodyTable, KeyStroke.getKeyStroke("DOWN"));
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
        return (DefaultTableModel) requestBodyTable.getModel();
    }

    public void deleteSelectedRow() {
        if (!(requestBodyTable.getSelectedRow() < 0)) {
            getModel().removeRow(requestBodyTable.getSelectedRow());
            if (getModel().getRowCount() != 0) {
                requestBodyTable.requestFocus();
                requestBodyTable.changeSelection(getModel().getRowCount() - 1, 0, true, false);
            }
        }
        publishTable(requestBodyTable);
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
                requestBodyTable.editCellAt(getModel().getRowCount() - 1, 0);
                requestBodyTable.requestFocus();
            }
        }
        publishTable(requestBodyTable);
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
