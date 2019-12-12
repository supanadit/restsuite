package com.supanadit.restsuite.component;

import javax.swing.*;

class ParamsMenu extends JPopupMenu {
    JMenuItem addData;

    public ParamsMenu() {
        addData = new JMenuItem("Add");
        add(addData);
    }
}