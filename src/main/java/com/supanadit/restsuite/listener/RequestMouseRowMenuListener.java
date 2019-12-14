package com.supanadit.restsuite.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RequestMouseRowMenuListener extends MouseAdapter {
    protected JTable table;

    public RequestMouseRowMenuListener(JTable table) {
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
        RequestMouseRowMenu menu = new RequestMouseRowMenu(this.table);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

