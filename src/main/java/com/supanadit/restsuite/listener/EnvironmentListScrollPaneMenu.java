package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.panel.EnvironmentListPanel;

import javax.swing.*;

class EnvironmentListScrollPaneMenu extends JPopupMenu {
    public EnvironmentListScrollPaneMenu(EnvironmentListPanel panel) {
        JMenuItem add = new JMenuItem("Add");
        add.addActionListener((e) -> {
            panel.openAddNewDialog();
        });
        add(add);
    }
}