package com.supanadit.restsuite.listener.api;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CollectionTreeMouseMenuListener extends MouseAdapter {
    public JTree collection;
    public CollectionTreeMouseMenuListener(JTree collection) {
        this.collection = collection;
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
        CollectionMouseMenu menu = new CollectionMouseMenu(collection);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
