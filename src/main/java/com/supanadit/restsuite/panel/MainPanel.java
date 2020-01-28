package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.MenuBar;
import com.supanadit.restsuite.panel.api.ApiPanel;
import com.supanadit.restsuite.panel.socket.SocketIoPanel;
import com.supanadit.restsuite.panel.sse.ServerSentEventPanel;
import com.supanadit.restsuite.panel.websocket.WebsocketPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class MainPanel extends JTabbedPane {
    private static final String restAPI = "Rest API";
    private static final String webSocket = "Websocket";
    private static final String sse = "SSE";
    private static final String socketIO = "Socket IO";

    private MenuBar menuBar;

    public MainPanel(MenuBar menuBar) {
        this.menuBar = menuBar;

        Color background = UIManager.getColor("Panel.background");

        JPanel mainApiPanel = new JPanel(new MigLayout("insets 0 0 0 0"));

        JPanel rightApiPanel = new JPanel(new MigLayout("w 200"));

        JScrollPane scrollPane = new JScrollPane(rightApiPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JTree tree = new JTree();
        tree.setBackground(background);

        rightApiPanel.add(tree, "push,grow");

        ApiPanel apiPanel = new ApiPanel();

        mainApiPanel.add(apiPanel, "push,grow");
        mainApiPanel.add(scrollPane, "pushy,growy");

        add(restAPI, mainApiPanel);
        add(webSocket, new WebsocketPanel());
        add(sse, new ServerSentEventPanel());
        add(socketIO, new SocketIoPanel());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
