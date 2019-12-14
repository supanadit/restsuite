package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.RequestTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RequestMouseTableRowMenuListener extends MouseAdapter {
    protected RequestTable table;

    public RequestMouseTableRowMenuListener(RequestTable table) {
        this.table = table;
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        RequestMouseTableRowMenu menu = new RequestMouseTableRowMenu(this.table);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

