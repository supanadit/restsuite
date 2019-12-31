package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.panel.EnvironmentListPanel;

import javax.swing.*;

class EnvironmentListScrollPaneMenu extends JPopupMenu {
    JMenuItem addData;
    protected EnvironmentListPanel panel;

    public EnvironmentListScrollPaneMenu(EnvironmentListPanel panel) {
        this.panel = panel;
        addData = new JMenuItem("Add");
        addData.addActionListener((e) -> {
            this.panel.openAddNewDialog();
        });
        add(addData);
    }
}