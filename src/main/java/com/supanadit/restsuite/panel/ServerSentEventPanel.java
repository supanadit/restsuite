package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputSseURL;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ServerSentEventPanel extends JPanel {
    public ServerSentEventPanel() {
        this.setLayout(new MigLayout("fill"));
        this.add(new JLabel("SSE URL"), "growx,pushx,wrap");
        this.add(InputSseURL.getComponent(), "growx,pushx");
        this.add(new JButton("Connect"), "wrap");
        this.add(new JLabel("Message"), "pushx,growx,wrap");
        this.add(new JScrollPane(new JTextArea()), "push,grow,span");
    }
}
