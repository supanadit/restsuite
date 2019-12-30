package com.supanadit.restsuite.panel;

import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;

public class SocketIoPanel extends JPanel {
    public SocketIoPanel() {
        this.setLayout(new MigLayout("fill,insets 10 10 10 10"));

        JPanel socketIoHeadPanel = new JPanel(new MigLayout("fill"));
        socketIoHeadPanel.add(new JLabel("SocketIO URL"), "growx,pushx,wrap");
        socketIoHeadPanel.add(new JTextField(), "growx,pushx");
        socketIoHeadPanel.add(new JButton("Connect"));
        JPanel socketIoLeftPanel = new JPanel(new MigLayout("w 200"));
        socketIoLeftPanel.add(new JLabel("Send Message"), "growx,pushx,wrap");
        socketIoLeftPanel.add(new JTextField(), "growx,pushx,wrap");
        socketIoLeftPanel.add(new JComboBox<>(), "growx,pushx,wrap");
        RTextScrollPane bodySocket = new RTextScrollPane(new RSyntaxTextArea());
        socketIoLeftPanel.add(bodySocket, "grow,push,wrap");
        socketIoLeftPanel.add(new JButton("Emit"), "growx,pushx,wrap");
        JPanel socketIoRightPanel = new JPanel(new MigLayout("w 200"));
        socketIoRightPanel.add(new JLabel("Listener"), "pushx,growx,wrap");
        socketIoRightPanel.add(new JTextField(), "pushx,growx,wrap");
        socketIoRightPanel.add(new JButton("Add Listener"), "pushx,growx,wrap");
        socketIoRightPanel.add(new JSeparator(), "pushx,growx,wrap");
        socketIoRightPanel.add(new JLabel("Listener List"), "pushx,growx,wrap");
        this.add(new JLabel("Message Listener"), "growx,pushx,wrap");
        this.add(new JTextArea(), "grow,push");
        this.add(socketIoHeadPanel, "north");
        this.add(socketIoLeftPanel, "west");
        this.add(socketIoRightPanel, "east");
    }
}
