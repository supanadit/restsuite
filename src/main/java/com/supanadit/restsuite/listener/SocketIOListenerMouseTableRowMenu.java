package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.panel.SocketIoPanel;

import javax.swing.*;

class SocketIOListenerMouseTableRowMenu extends JPopupMenu {
    public SocketIOListenerMouseTableRowMenu(SocketIoPanel panel) {
        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener((e) -> {
            panel.deleteSelectedRowListener();
        });
        add(delete);
    }
}