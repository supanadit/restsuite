package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.RequestTable;
import com.supanadit.restsuite.panel.SocketIoPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SocketIOListenerTableRowMenuListener extends MouseAdapter {
    protected SocketIoPanel panel;

    public SocketIOListenerTableRowMenuListener(SocketIoPanel panel) {
        this.panel = panel;
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
        SocketIOListenerMouseTableRowMenu menu = new SocketIOListenerMouseTableRowMenu(this.panel);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

