package com.supanadit.restsuite.panel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JTabbedPane {
    public MainPanel() {
        JPanel coreApiPanel = new JPanel();

        coreApiPanel.setLayout(new BorderLayout());
        coreApiPanel.add(new SideApiPanel(), BorderLayout.LINE_END);
        coreApiPanel.add(new ApiPanel(), BorderLayout.CENTER);

        this.add("Rest API", coreApiPanel);
        this.add("Websocket", new WebsocketPanel());
        this.add("Socket IO", new SocketIoPanel());
        this.add("SSE", new ServerSentEventPanel());
    }
}
