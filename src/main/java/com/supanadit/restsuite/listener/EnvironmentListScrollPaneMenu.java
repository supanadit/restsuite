package com.supanadit.restsuite.listener;

import javax.swing.*;

class EnvironmentListScrollPaneMenu extends JPopupMenu {
    JMenuItem addData;
    protected JTable table;

    public EnvironmentListScrollPaneMenu(JTable table) {
        this.table = table;
        addData = new JMenuItem("Add");
        addData.addActionListener((e) -> {

        });
        add(addData);
    }
}