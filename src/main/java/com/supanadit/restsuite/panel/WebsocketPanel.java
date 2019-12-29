package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.listener.WebsocketTestListener;
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

    public WebsocketPanel() {
        JTextArea logMessage = new JTextArea();

        this.setLayout(new MigLayout("insets 10 10 10 10"));
        this.add(new JLabel("Websocket URL"), "growx,pushx,wrap");
        JTextField socketURL = new JTextField("ws://example.com/ws");
        this.add(socketURL, "growx,pushx");
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener((e) -> {
            request = new Request.Builder().url(socketURL.getText()).build();
            wsListener = new WebsocketTestListener(logMessage);
            ws = client.newWebSocket(request, wsListener);
            logMessage.append("Connected to ".concat(socketURL.getText()).concat("\n"));
        });
        this.add(connectButton, "wrap");

        this.add(new JLabel("Message"), "pushx,growx,wrap");
        this.add(new JScrollPane(logMessage), "push,grow,span,wrap");
        JTextField message = new JTextField();
        this.add(message, "pushx,growx");

        JButton sendButton = new JButton("Send");

        sendButton.addActionListener((e) -> {
            if (request != null && wsListener != null && ws != null) {
                String messageSender = message.getText();
                ws.send(messageSender);
                logMessage.append("Send Message : ".concat(messageSender).concat("\n"));
            } else {
                logMessage.append("Please connect first \n");
            }
        });

        this.add(sendButton);
    }
}
