package com.supanadit.restsuite.panel;

import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;
import com.supanadit.restsuite.component.InputSseURL;
import net.miginfocom.swing.MigLayout;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class ServerSentEventPanel extends JPanel {
    ServerSentEvent sse;
    OkSse okSse;
    String connectDisconnect = "Connect";
    boolean isConnected = false;
    JButton connectDisconnectButton;

    public ServerSentEventPanel() {
        this.setLayout(new MigLayout("insets 10 10 10 10"));
        InputSseURL inputURL = InputSseURL.getComponent();
        connectDisconnectButton = new JButton(connectDisconnect);
        this.add(new JLabel("SSE URL"), "growx,pushx,wrap");
        this.add(inputURL, "growx,pushx");
        this.add(connectDisconnectButton, "wrap");
        this.add(new JLabel("Message"), "pushx,growx,wrap");
        JTextArea messageTextArea = new JTextArea();
        this.add(new JScrollPane(messageTextArea), "push,grow,span");

        ServerSentEvent.Listener listener = new ServerSentEvent.Listener() {
            @Override
            public void onOpen(ServerSentEvent sse, Response response) {
                System.out.println("A connection just opened");
            }

            @Override
            public void onMessage(ServerSentEvent sse, String id, String event, String message) {
                messageTextArea.append(message.concat("\n"));
            }

            @Override
            public void onComment(ServerSentEvent sse, String comment) {
                // When a comment is received
                System.out.printf("Have a comment %s \n", comment);
            }

            @Override
            public boolean onRetryTime(ServerSentEvent sse, long milliseconds) {
                System.out.println("Retry Time");
                return true; // True to use the new retry time received by SSE
            }

            @Override
            public boolean onRetryError(ServerSentEvent sse, Throwable throwable, Response response) {
                boolean continueProcess = true;
                if (throwable.getCause() != null) {
                    continueProcess = false;
                    messageTextArea.append(throwable.getMessage().concat("\n"));
                    setStatus(false);
                }
                return continueProcess;
            }

            @Override
            public void onClosed(ServerSentEvent sse) {
                System.out.println("Connection is Closed");
            }

            @Override
            public Request onPreRetry(ServerSentEvent serverSentEvent, Request request) {
                System.out.println("Pre Requesting");
                return request;
            }
        };

        connectDisconnectButton.addActionListener(e -> {
            if (!isConnected) {
                String url = inputURL.getText();
                if (!url.isEmpty()) {
                    Request request = new Request.Builder().url(inputURL.getText()).build();
                    okSse = new OkSse();
                    sse = okSse.newServerSentEvent(request, listener);
                    setStatus(true);
                }
            } else {
                setStatus(false);
            }
        });
    }

    public void setStatus(boolean status) {
        isConnected = status;
        if (isConnected) {
            connectDisconnectButton.setText(getButtonText());
        } else {
            if (sse != null) {
                connectDisconnectButton.setText(getButtonText());
                sse.close();
            }
        }
    }

    public String getButtonText() {
        return (isConnected) ? "Disconnect" : "Connect";
    }
}
