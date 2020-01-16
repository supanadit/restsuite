package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.MenuBar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class MainPanel extends JTabbedPane {
    private static final String restAPI = "Rest API";
    private static final String webSocket = "Websocket";
    private static final String sse = "SSE";
    private static final String socketIO = "Socket IO";

    private MenuBar menuBar;

    public MainPanel(MenuBar menuBar) {
        this.menuBar = menuBar;

        JPanel coreApiPanel = new JPanel();

        coreApiPanel.setLayout(new MigLayout("insets 0 0 0 0", "[fill,20%!][fill,grow]"));
        coreApiPanel.add(new ApiSidePanel(), "pushy,growy");
        coreApiPanel.add(new ApiPanel());

        add(restAPI, coreApiPanel);
        add(webSocket, new WebsocketPanel());
        add(sse, new ServerSentEventPanel());
        add(socketIO, new SocketIoPanel());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
