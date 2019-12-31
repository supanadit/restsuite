package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.panel.EnvironmentListPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnvironmentListScrollPaneMenuListener extends MouseAdapter {
    protected EnvironmentListPanel panel;

    public EnvironmentListScrollPaneMenuListener(EnvironmentListPanel panel) {
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
        EnvironmentListScrollPaneMenu menu = new EnvironmentListScrollPaneMenu(this.panel);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

