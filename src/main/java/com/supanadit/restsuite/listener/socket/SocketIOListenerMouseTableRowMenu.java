package com.supanadit.restsuite.listener.socket;

import com.supanadit.restsuite.panel.socket.SocketIoPanel;

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