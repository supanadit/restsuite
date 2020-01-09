package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.RequestTable;

import javax.swing.*;

class RequestMouseScrollPaneMenu extends JPopupMenu {

    public RequestMouseScrollPaneMenu(RequestTable table) {
        JMenuItem add = new JMenuItem("Add");
        add.addActionListener((e) -> {
            table.addNewEmptyRow();
        });
        add(add);
    }
}