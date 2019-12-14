package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.RequestTable;

import javax.swing.*;

class RequestMouseScrollPaneMenu extends JPopupMenu {
    JMenuItem addData;
    protected RequestTable table;

    public RequestMouseScrollPaneMenu(RequestTable table) {
        this.table = table;
        addData = new JMenuItem("Add");
        addData.addActionListener((e) -> {
            this.table.addNewEmptyRow();
        });
        add(addData);
    }
}