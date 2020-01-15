package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.panel.ApiSidePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CollectionTreeMouseMenuListener extends MouseAdapter {
    public ApiSidePanel apiSidePanel;

    public CollectionTreeMouseMenuListener(ApiSidePanel apiSidePanel) {
        this.apiSidePanel = apiSidePanel;
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
        CollectionMouseMenu menu = new CollectionMouseMenu(apiSidePanel);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

