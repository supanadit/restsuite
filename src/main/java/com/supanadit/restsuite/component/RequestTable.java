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

public class RequestTable extends JScrollPane {
    protected DefaultTableModel defaultTableModel;
    protected JTable requestTable;
    protected PublishSubject<JTable> subject;


    public RequestTable(boolean mouseAction, boolean keyboardAction, boolean editable) {
        this.defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return editable;
            }
        };

        this.defaultTableModel.addColumn("Key");
        this.defaultTableModel.addColumn("Value");

        this.requestTable = new JTable(this.defaultTableModel);

        this.setViewportView(this.requestTable);

        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("TAB"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("shift TAB"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("RIGHT"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("LEFT"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("UP"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("DOWN"));

        if (mouseAction) {
            this.requestTable.addMouseListener(new RequestMouseTableRowMenuListener(this));
        }
        if (keyboardAction) {
            this.requestTable.addKeyListener(new RequestKeyboardTableRowListener(this));
        }

        if (mouseAction) {
            this.addMouseListener(new RequestMouseScrollPaneMenuListener(this));
        }
    }

    public RequestTable() {
        this(true, true, true);
    }

    public void setSubject(PublishSubject<JTable> subject) {
        this.subject = subject;
    }

    public void publishTable(JTable table) {
        if (this.subject != null) {
            this.subject.onNext(table);
        }
    }

    public DefaultTableModel getModel() {
        return (DefaultTableModel) this.requestTable.getModel();
    }

    public void deleteSelectedRow() {
        if (!(this.requestTable.getSelectedRow() < 0)) {
            this.getModel().removeRow(this.requestTable.getSelectedRow());
            if (this.getModel().getRowCount() != 0) {
                this.requestTable.requestFocus();
                this.requestTable.changeSelection(this.getModel().getRowCount() - 1, 0, true, false);
            }
        }
        this.publishTable(this.requestTable);
    }

    public void addNewEmptyRow() {
        this.addRow(new Request("", ""));
    }

    public void addRow(Request request) {
        this.addRow(request, true);
    }

    public void addRow(Request request, boolean withFocus) {
        this.getModel().addRow(new Object[]{request.getKey(), request.getValue()});
        if (withFocus) {
            if (this.getModel().getRowCount() != 0) {
                this.requestTable.editCellAt(this.getModel().getRowCount() - 1, 0);
                this.requestTable.requestFocus();
            }
        }
        this.publishTable(this.requestTable);
    }

    public void setFromRequestArrayList(ArrayList<Request> requestArrayList) {
        int rowCount = this.getModel().getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            this.getModel().removeRow(i);
        }

        for (Request request : requestArrayList) {
            this.addRow(request, false);
        }
    }
}
