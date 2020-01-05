package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputSocketIoListener;
import com.supanadit.restsuite.component.InputSocketIoMessage;
import com.supanadit.restsuite.component.InputSocketIoURL;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.util.Arrays;

public class SocketIoPanel extends JPanel {
    Socket socket;

    public SocketIoPanel() {
        this.setLayout(new MigLayout("fill,insets 10 10 10 10"));

        Color background = UIManager.getColor("Table.background");
        Color lineColor = UIManager.getColor("Table.gridColor");
        Color fontColor = UIManager.getColor("FormattedTextField.foreground");
        Color selectionColor = UIManager.getColor("FormattedTextField.selectionBackground");

        JButton connectDisconnectButton = new JButton("Connect");

        JComboBox<String> messageSendType = new JComboBox<>();
        messageSendType.addItem("String");
        messageSendType.addItem("JSON");
        messageSendType.addItem("Array");
        messageSendType.addItem("Object");
        messageSendType.addItem("Number");
        messageSendType.addItem("Boolean");

        InputSocketIoMessage emitChannel = InputSocketIoMessage.getComponent();

        JPanel socketIoHeadPanel = new JPanel(new MigLayout("fill"));
        socketIoHeadPanel.add(new JLabel("SocketIO URL"), "growx,pushx,wrap");
        InputSocketIoURL socketIoURL = InputSocketIoURL.getComponent();
        socketIoHeadPanel.add(socketIoURL, "growx,pushx");
        socketIoHeadPanel.add(connectDisconnectButton);
        JPanel socketIoLeftPanel = new JPanel(new MigLayout("w 200"));
        socketIoLeftPanel.add(new JLabel("Send Message"), "growx,pushx,wrap");
        socketIoLeftPanel.add(emitChannel, "growx,pushx,wrap");
        socketIoLeftPanel.add(messageSendType, "growx,pushx,wrap");

        RSyntaxTextArea emitBody = new RSyntaxTextArea();
        emitBody.setCodeFoldingEnabled(true);
        emitBody.setCurrentLineHighlightColor(background);
        emitBody.setBackground(background);
        emitBody.setTabLineColor(background);
        emitBody.setBorder(BorderFactory.createLineBorder(lineColor));
        emitBody.setForeground(fontColor);
        emitBody.setSelectionColor(selectionColor);
        // This Code let selected text to be white color
        emitBody.setUseSelectedTextColor(true);
        emitBody.setSelectedTextColor(Color.white);

        JButton emitButton = new JButton("Emit");
        InputSocketIoListener inputListener = InputSocketIoListener.getComponent();
        RTextScrollPane emitBodyScrollPane = new RTextScrollPane(emitBody);

        socketIoLeftPanel.add(emitBodyScrollPane, "grow,push,wrap");
        socketIoLeftPanel.add(emitButton, "growx,pushx,wrap");
        JPanel socketIoRightPanel = new JPanel(new MigLayout("w 200"));
        socketIoRightPanel.add(new JLabel("Listener"), "pushx,growx,wrap");
        socketIoRightPanel.add(inputListener, "pushx,growx,wrap");
        socketIoRightPanel.add(new JButton("Add Listener"), "pushx,growx,wrap");
        socketIoRightPanel.add(new JSeparator(), "pushx,growx,wrap");
        socketIoRightPanel.add(new JLabel("Listener List"), "pushx,growx,wrap");
        this.add(new JLabel("Response Message"), "growx,pushx,wrap");

        RSyntaxTextArea responseBody = new RSyntaxTextArea();
        responseBody.setCodeFoldingEnabled(true);
        responseBody.setCurrentLineHighlightColor(background);
        responseBody.setBackground(background);
        responseBody.setTabLineColor(background);
        responseBody.setBorder(BorderFactory.createLineBorder(lineColor));
        responseBody.setForeground(fontColor);
        responseBody.setSelectionColor(selectionColor);
        // This Code let selected text to be white color
        responseBody.setUseSelectedTextColor(true);
        responseBody.setSelectedTextColor(Color.white);

        RTextScrollPane responseBodyScrollPane = new RTextScrollPane(responseBody);

        this.add(responseBodyScrollPane, "grow,push");
        this.add(socketIoHeadPanel, "north");
        this.add(socketIoLeftPanel, "west");
        this.add(socketIoRightPanel, "east");

        connectDisconnectButton.addActionListener(e -> {
            String url = socketIoURL.getText();
            if (!url.isEmpty()) {
                try {
                    socket = IO.socket(url);
                    socket.on("reply", args -> {
                        String body = Arrays.toString(args)
                                .replace(",", "")
                                .replace("[", "")
                                .replace("]", "")
                                .trim();

                        responseBody.append("reply".concat(" : ").concat(body).concat("\n"));
                    });
                    socket.connect();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        emitButton.addActionListener(e -> {
            if (socket != null) {
                if (!(emitChannel.getText().isEmpty() && emitBody.getText().isEmpty())) {
                    socket.emit(emitChannel.getText(), emitBody.getText());
                }
            }
        });
    }
}
