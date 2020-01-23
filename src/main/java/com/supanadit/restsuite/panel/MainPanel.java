package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.MenuBar;
import com.supanadit.restsuite.panel.api.ApiPanel;
import com.supanadit.restsuite.panel.socket.SocketIoPanel;
import com.supanadit.restsuite.panel.sse.ServerSentEventPanel;
import com.supanadit.restsuite.panel.websocket.WebsocketPanel;

import javax.swing.*;

public class MainPanel extends JTabbedPane {
    private static final String restAPI = "Rest API";
    private static final String webSocket = "Websocket";
    private static final String sse = "SSE";
    private static final String socketIO = "Socket IO";

    private MenuBar menuBar;

    public MainPanel(MenuBar menuBar) {
        this.menuBar = menuBar;

        add(restAPI, new ApiPanel());
        add(webSocket, new WebsocketPanel());
        add(sse, new ServerSentEventPanel());
        add(socketIO, new SocketIoPanel());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
