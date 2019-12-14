package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.model.Request;
import io.reactivex.subjects.BehaviorSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RequestKeyboardRowListener implements KeyListener {
    protected JTable table;
    protected BehaviorSubject<Request> subject;

    public RequestKeyboardRowListener(JTable table, BehaviorSubject<Request> subject) {
        this.table = table;
        this.subject = subject;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DELETE:
                DefaultTableModel model = (DefaultTableModel) this.table.getModel();
                if (!(this.table.getSelectedRow() < 0)) {
                    model.removeRow(this.table.getSelectedRow());
                    if (model.getRowCount() != 0) {
                        this.table.requestFocus();
                        this.table.changeSelection(model.getRowCount() - 1, 0, true, false);
                    }
                }
                break;
            case KeyEvent.VK_ENTER:
                this.subject.onNext(new Request("", ""));
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
