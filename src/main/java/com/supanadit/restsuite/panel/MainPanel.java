package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.MenuBar;
import com.supanadit.restsuite.model.Collection;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MainPanel extends JTabbedPane {
    private static final String restAPI = "Rest API";
    private static final String webSocket = "Websocket";
    private static final String sse = "SSE";
    private static final String socketIO = "Socket IO";

    private MenuBar menuBar;

    public MainPanel(MenuBar menuBar) {
        this.menuBar = menuBar;

        JPanel coreApiPanel = new JPanel();
        ApiSidePanel apiSidePanel = new ApiSidePanel();
        ApiPanel apiPanel = new ApiPanel();

        coreApiPanel.setLayout(new BorderLayout());
        coreApiPanel.add(apiSidePanel, BorderLayout.LINE_END);
        coreApiPanel.add(apiPanel, BorderLayout.CENTER);

        add(restAPI, coreApiPanel);
        add(webSocket, new WebsocketPanel());
        add(sse, new ServerSentEventPanel());
        add(socketIO, new SocketIoPanel());

        addChangeListener(e -> {
            menuBar.getCollectionMenu().setEnabled(getSelectedIndex() == 0);
        });

        menuBar.getCollectionSaveMenu().addActionListener(e -> {
            Collection collection = Collection.fromApiModel(apiPanel.getModel());
            apiSidePanel.getCollectionRoot().add(new DefaultMutableTreeNode("New Collection"));
            apiSidePanel.getCollectionTree().updateUI();
        });
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
