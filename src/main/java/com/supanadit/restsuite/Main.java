package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatLightLaf;
import com.supanadit.restsuite.component.*;
import net.miginfocom.swing.MigLayout;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatLightLaf.install();
            JFrame frame = new JFrame("Rest Suite");

            JLabel apiName = new JLabel("API Name");
            InputTextURL apiUrl = InputTextURL.getComponent();
            JButton sendButton = new JButton("Send");

            JPanel panel = new JPanel(new MigLayout());

            panel.add(apiName, "wrap");
            panel.add(apiUrl, "growx, pushx");
            panel.add(RequestTypeComboBox.getComponent());
            panel.add(sendButton, "wrap");

            OkHttpClient client = new OkHttpClient();

            JTabbedPane tabRequest = new JTabbedPane();

            JPanel panelParams = new JPanel(new MigLayout());
            JPanel panelHeaders = new JPanel(new MigLayout());
            JPanel panelBody = new JPanel(new MigLayout());

            tabRequest.add("Params", panelParams);
            tabRequest.add("Header", panelHeaders);
            tabRequest.add("Body", panelBody);

            // Table Params
            panelParams.add(new RequestParamTable(), "growx,pushx");

            // Table Headers
            panelHeaders.add(new RequestHeaderTable(), "growx,pushx");

            // Body
            panelBody.add(RequestBodyTypeComboBox.getComponent());
            panelBody.add(RequestBodyRawTypeComboBox.getComponent(), "wrap");

            panelBody.add(new BodyRequestTextArea(), "grow, push, span 3");

            panel.add(tabRequest, "growx, pushx, span 3, wrap, hmax 250");

            JTabbedPane tabResponse = new JTabbedPane();

            JPanel panelBodyResponse = new JPanel(new MigLayout("fill"));

            tabResponse.add("Response", panelBodyResponse);

            BodyResponseTextArea bodyResponse = new BodyResponseTextArea();

            panelBodyResponse.add(bodyResponse, "grow, push");

            panel.add(tabResponse, "growx, growy, pushy, pushx, span 3");

            sendButton.addActionListener((e) -> {
                System.out.println(apiUrl.getText());
                Request request = new Request.Builder()
                        .url(apiUrl.getText())
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    bodyResponse.setText(Objects.requireNonNull(response.body()).string());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            frame.add(panel, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(1024, 600));
            frame.setVisible(true);
        });
    }
}
