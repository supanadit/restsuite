package com.supanadit.restsuite.panel;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ServerSentEventPanel extends JPanel {
    public ServerSentEventPanel() {
        this.setLayout(new MigLayout("fill"));
        this.add(new JLabel("SSE URL"), "growx,pushx,wrap");
        this.add(new JTextField(), "growx,pushx");
        this.add(new JButton("Connect"), "wrap");
        this.add(new JLabel("Message"), "pushx,growx,wrap");
        this.add(new JScrollPane(new JTextArea()), "push,grow,span");
    }
}
