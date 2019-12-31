package com.supanadit.restsuite.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnvironmentListScrollPaneMenuListener extends MouseAdapter {
    protected JTable table;

    public EnvironmentListScrollPaneMenuListener(JTable table) {
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
        EnvironmentListScrollPaneMenu menu = new EnvironmentListScrollPaneMenu(this.table);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

