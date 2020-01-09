package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.RequestTable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RequestKeyboardTableRowListener implements KeyListener {
    protected RequestTable table;

    public RequestKeyboardTableRowListener(RequestTable table) {
        this.table = table;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DELETE:
                table.deleteSelectedRow();
                break;
            case KeyEvent.VK_ENTER:
                table.addNewEmptyRow();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
