package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputSocketIoListener;
import com.supanadit.restsuite.component.InputSocketIoMessage;
import com.supanadit.restsuite.component.InputSocketIoURL;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

public class SocketIoPanel extends JPanel {
    public SocketIoPanel() {
        this.setLayout(new MigLayout("fill,insets 10 10 10 10"));

        JPanel socketIoHeadPanel = new JPanel(new MigLayout("fill"));
        socketIoHeadPanel.add(new JLabel("SocketIO URL"), "growx,pushx,wrap");
        InputSocketIoURL socketIoURL = InputSocketIoURL.getComponent();
        socketIoHeadPanel.add(socketIoURL, "growx,pushx");
        socketIoHeadPanel.add(new JButton("Connect"));
        JPanel socketIoLeftPanel = new JPanel(new MigLayout("w 200"));
        socketIoLeftPanel.add(new JLabel("Send Message"), "growx,pushx,wrap");
        socketIoLeftPanel.add(InputSocketIoMessage.getComponent(), "growx,pushx,wrap");
        socketIoLeftPanel.add(new JComboBox<>(), "growx,pushx,wrap");

        RSyntaxTextArea editor = new RSyntaxTextArea();
        editor.setCodeFoldingEnabled(true);
        Color background = UIManager.getColor("Table.background");
        editor.setCurrentLineHighlightColor(background);
        editor.setBackground(background);
        editor.setTabLineColor(background);
        Color lineColor = UIManager.getColor("Table.gridColor");
        editor.setBorder(BorderFactory.createLineBorder(lineColor));
        Color fontColor = UIManager.getColor("FormattedTextField.foreground");
        editor.setForeground(fontColor);
        Color selectionColor = UIManager.getColor("FormattedTextField.selectionBackground");
        editor.setSelectionColor(selectionColor);
        // This Code let selected text be white
        editor.setUseSelectedTextColor(true);
        editor.setSelectedTextColor(Color.white);

        RTextScrollPane bodySocket = new RTextScrollPane(editor);
        socketIoLeftPanel.add(bodySocket, "grow,push,wrap");
        socketIoLeftPanel.add(new JButton("Emit"), "growx,pushx,wrap");
        JPanel socketIoRightPanel = new JPanel(new MigLayout("w 200"));
        socketIoRightPanel.add(new JLabel("Listener"), "pushx,growx,wrap");
        socketIoRightPanel.add(InputSocketIoListener.getComponent(), "pushx,growx,wrap");
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
