package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.RequestTable;

import javax.swing.*;

class RequestMouseTableRowMenu extends JPopupMenu {
    public RequestMouseTableRowMenu(RequestTable table) {
        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener((e) -> {
            table.deleteSelectedRow();
        });
        add(delete);
    }
}