package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.panel.SocketIoPanel;

import javax.swing.*;

class SocketIOListenerMouseTableRowMenu extends JPopupMenu {
    protected SocketIoPanel panel;

    public SocketIOListenerMouseTableRowMenu(SocketIoPanel panel) {
        this.panel = panel;
        JMenuItem deleteData = new JMenuItem("Delete");
        deleteData.addActionListener((e) -> {
            panel.deleteSelectedRowListener();
        });
        add(deleteData);
    }
}