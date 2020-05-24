package com.supanadit.restsuite.listener.api;

import com.supanadit.restsuite.panel.rest.SidePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CollectionTreeMouseMenuListener extends MouseAdapter {
    public SidePanel sidePanel;
    public CollectionTreeMouseMenuListener(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
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
        CollectionMouseMenu menu = new CollectionMouseMenu(sidePanel);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
