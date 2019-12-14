package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.EditableCellFocusAction;
import com.supanadit.restsuite.listener.RequestKeyboardTableRowListener;
import com.supanadit.restsuite.listener.RequestMouseScrollPaneMenuListener;
import com.supanadit.restsuite.listener.RequestMouseTableRowMenuListener;
import io.reactivex.subjects.PublishSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RequestTable extends JScrollPane {
    protected DefaultTableModel defaultTableModel;
    protected JTable requestTable;
    protected PublishSubject<JTable> subject;

    public RequestTable() {
        this.defaultTableModel = new DefaultTableModel();

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

        this.requestTable.addMouseListener(new RequestMouseTableRowMenuListener(this));
        this.requestTable.addKeyListener(new RequestKeyboardTableRowListener(this));

        this.addMouseListener(new RequestMouseScrollPaneMenuListener(this));
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
        this.getModel().addRow(new Object[]{"", ""});
        if (this.getModel().getRowCount() != 0) {
            this.requestTable.editCellAt(this.getModel().getRowCount() - 1, 0);
            this.requestTable.requestFocus();
        }
        this.publishTable(this.requestTable);
    }
}
