package com.supanadit.restsuite.panel.websocket;

import com.supanadit.restsuite.component.input.InputWebsocketMessage;
import com.supanadit.restsuite.component.input.InputWebsocketURL;
import com.supanadit.restsuite.listener.websocket.WebsocketTestListener;
import net.miginfocom.swing.MigLayout;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import javax.swing.*;

public class WebsocketPanel extends JPanel {
    OkHttpClient client = new OkHttpClient();
    Request request;
    WebsocketTestListener wsListener;
    WebSocket ws;

    Boolean isConnected = false;

    public WebsocketPanel() {
        JTextArea logMessage = new JTextArea();

        setLayout(new MigLayout("insets 10 10 10 10"));
        InputWebsocketURL socketURL = new InputWebsocketURL();
        add(socketURL, "growx,pushx");

        JButton sendButton = new JButton("Send");
        JButton connectButton = new JButton("Connect");

        sendButton.setEnabled(false);

        connectButton.addActionListener((e) -> {
            if (!isConnected) {
                request = new Request.Builder().url(socketURL.getText()).build();
                wsListener = new WebsocketTestListener(logMessage);
                ws = client.newWebSocket(request, wsListener);
                logMessage.append("Connected to ".concat(socketURL.getText()).concat("\n"));
                connectButton.setText("Disconnect");
            } else {
                logMessage.append("Disconnected from ".concat(socketURL.getText()).concat("\n"));
                connectButton.setText("Connect");
            }
            isConnected = !isConnected;
            // Some Logic
            socketURL.setEnabled(!isConnected);
            sendButton.setEnabled(isConnected);
        });

        add(connectButton, "wrap");

        add(new JLabel("Message"), "pushx,growx,wrap");
        add(new JScrollPane(logMessage), "push,grow,span,wrap");
        InputWebsocketMessage message = new InputWebsocketMessage();
        add(message, "pushx,growx");

        sendButton.addActionListener((e) -> {
            if (request != null && wsListener != null && ws != null) {
                String messageSender = message.getText();
                ws.send(messageSender);
                logMessage.append("Send Message : ".concat(messageSender).concat("\n"));
            } else {
                logMessage.append("Please connect first \n");
            }
        });

        add(sendButton, "growx");
    }
}
