package com.supanadit.restsuite.component;

import javax.swing.*;

class ParamsMenu extends JPopupMenu {
    JMenuItem anItem;

    public ParamsMenu() {
        anItem = new JMenuItem("Add New Params");
        add(anItem);
    }
}