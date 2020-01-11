package com.supanadit.restsuite.panel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JTabbedPane {
    public MainPanel() {
        // JPanel coreApiPanel = new JPanel();

        // coreApiPanel.setLayout(new BorderLayout());
        // coreApiPanel.add(new ApiSidePanel(), BorderLayout.LINE_END);
        // coreApiPanel.add(new ApiPanel(), BorderLayout.CENTER);

        add("Rest API", new ApiPanel());
        add("Websocket", new WebsocketPanel());
        add("SSE", new ServerSentEventPanel());
        add("Socket IO", new SocketIoPanel());
    }
}
