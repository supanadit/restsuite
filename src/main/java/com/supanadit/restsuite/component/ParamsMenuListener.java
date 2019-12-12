package com.supanadit.restsuite.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ParamsMenuListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        ParamsMenu menu = new ParamsMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

