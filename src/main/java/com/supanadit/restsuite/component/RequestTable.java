package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.EditableCellFocusAction;
import com.supanadit.restsuite.listener.RequestKeyboardRowListener;
import com.supanadit.restsuite.listener.RequestMouseMenuListener;
import com.supanadit.restsuite.listener.RequestMouseRowMenuListener;
import com.supanadit.restsuite.model.Request;
import io.reactivex.subjects.BehaviorSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RequestTable extends JScrollPane {
    protected DefaultTableModel defaultTableModel;
    protected JTable requestTable;

    protected BehaviorSubject<Request> subject = BehaviorSubject.create();

    public RequestTable() {
        this.defaultTableModel = new DefaultTableModel();

        this.defaultTableModel.addColumn("Key");
        this.defaultTableModel.addColumn("Value");

        this.requestTable = new JTable(this.defaultTableModel);

        subject.subscribe((e) -> {
            DefaultTableModel model = (DefaultTableModel) this.requestTable.getModel();
            model.addRow(new Object[]{e.getKey(), e.getValue()});
            if (model.getRowCount() != 0) {
                this.requestTable.editCellAt(model.getRowCount() - 1, 0);
                this.requestTable.requestFocus();
            }
        });

        this.setViewportView(this.requestTable);

        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("TAB"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("shift TAB"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("RIGHT"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("LEFT"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("UP"));
        new EditableCellFocusAction(this.requestTable, KeyStroke.getKeyStroke("DOWN"));

        this.requestTable.addMouseListener(new RequestMouseRowMenuListener(this.requestTable));
        this.requestTable.addKeyListener(new RequestKeyboardRowListener(this.requestTable, this.subject));

        this.addMouseListener(new RequestMouseMenuListener(subject));
    }
}
