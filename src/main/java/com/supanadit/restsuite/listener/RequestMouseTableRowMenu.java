package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.RequestTable;

import javax.swing.*;

class RequestMouseTableRowMenu extends JPopupMenu {
    protected RequestTable table;

    public RequestMouseTableRowMenu(RequestTable table) {
        this.table = table;
        JMenuItem deleteData = new JMenuItem("Delete");
        deleteData.addActionListener((e) -> {
            this.table.deleteSelectedRow();
        });
        add(deleteData);
    }
}