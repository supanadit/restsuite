package com.supanadit.restsuite.component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class RequestApiButton extends JButton {
    protected InputTextURL inputTextURL;
    protected OkHttpClient client;
    protected BodyPanel bodyPanel;

    public RequestApiButton(InputTextURL inputTextURL) {
        this.setText("Send");

        this.inputTextURL = inputTextURL;

        this.client = new OkHttpClient();

        this.addActionListener((e) -> {
            Request request = new Request.Builder().url(this.inputTextURL.getText()).build();

            try (Response response = client.newCall(request).execute()) {
                if (this.bodyPanel == null) {
                    System.out.println(Objects.requireNonNull(response.body()).string());
                } else {
                    this.bodyPanel.setText(Objects.requireNonNull(response.body()).string());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void setBodyPanel(BodyPanel bodyPanel) {
        this.bodyPanel = bodyPanel;
    }
}
