package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.supanadit.restsuite.component.ParamsMenuListener;
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
            FlatDarculaLaf.install();
            JFrame frame = new JFrame("Rest Suite");

            JLabel apiName = new JLabel("API Name");
            JTextField apiUrl = new JTextField("http://www.google.com");
            JButton sendButton = new JButton("Send");

            JComboBox<String> apiType = new JComboBox<>();

            apiType.addItem("Get");
            apiType.addItem("Post");
            apiType.addItem("Put");
            apiType.addItem("Delete");

            JPanel panel = new JPanel(new MigLayout());
            panel.add(apiName, "wrap");
            panel.add(apiUrl, "growx, pushx");
            panel.add(apiType);
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

            String[][] dataParams = {};
            String[] columnParams = {"Key", "Value"};
            JTable tableParams = new JTable(dataParams, columnParams);

            JScrollPane scrollPaneTableParams = new JScrollPane(tableParams);

            panelParams.add(scrollPaneTableParams, "growx,pushx");

            scrollPaneTableParams.addMouseListener(new ParamsMenuListener());

            // Table Headers

            String[][] dataHeaders = {
                    {"accept", "application/text"}
            };
            String[] columnHeaders = {"Key", "Value"};
            JTable tableHeaders = new JTable(dataHeaders, columnHeaders);

            JScrollPane scrollPaneTableHeaders = new JScrollPane(tableHeaders);

            panelHeaders.add(scrollPaneTableHeaders, "growx,pushx");

            // Body

            JComboBox<String> bodyType = new JComboBox<>();
            bodyType.addItem("Raw");
            bodyType.addItem("Form");

            panelBody.add(bodyType);

            JComboBox<String> rawType = new JComboBox<>();
            rawType.addItem("Text");
            rawType.addItem("JSON");
            rawType.addItem("HTML");
            rawType.addItem("XML");
            rawType.addItem("Javascript");

            panelBody.add(rawType, "wrap");

            JTextArea bodyRequest = new JTextArea();

            JScrollPane scrollRequestBody = new JScrollPane(bodyRequest);

            panelBody.add(scrollRequestBody, "grow, push, span 3");

            panel.add(tabRequest, "growx, pushx, span 3, wrap, hmax 250");

            JTabbedPane tabResponse = new JTabbedPane();

            JPanel panelBodyResponse = new JPanel(new MigLayout("fill"));

            tabResponse.add("Response", panelBodyResponse);

            JTextArea responseBody = new JTextArea();

            JScrollPane scrollResponseBody = new JScrollPane(responseBody);

//            TextLineNumber textLineNumberResponseBody = new TextLineNumber(responseBody);

//            scrollResponseBody.setRowHeaderView(textLineNumberResponseBody);

            panelBodyResponse.add(scrollResponseBody, "grow, push");

            panel.add(tabResponse, "growx, growy, pushy, pushx, span 3");

//            JPanel panelApi = new JPanel(new MigLayout("w 250, wrap"));
//
//            JPanel panelApiSpaceTop = new JPanel(new MigLayout("h 1"));
//            JPanel panelApiSpaceLeft = new JPanel(new MigLayout("w 1"));
//            JPanel panelApiSpaceRight = new JPanel(new MigLayout("w 1"));
//
//            panelApi.add(panelApiSpaceTop, "north");
//            panelApi.add(panelApiSpaceLeft, "west");
//            panelApi.add(panelApiSpaceRight, "east");
//
//            JLabel apiCollection = new JLabel("API Collection");
//            panelApi.add(apiCollection);
//
//            JTextField searchAPI = new JTextField();
//            panelApi.add(searchAPI, "growx, pushx");
//
//            panel.add(panelApi, "east");
//
//            JPanel spaceTop = new JPanel(new MigLayout("h 1"));
//            JPanel spaceLeft = new JPanel(new MigLayout("w 1"));
//
//            panel.add(spaceTop, "north");
//            panel.add(spaceLeft, "west");

            sendButton.addActionListener((e) -> {
                System.out.println(apiUrl.getText());
                Request request = new Request.Builder()
                        .url(apiUrl.getText())
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    responseBody.setText(Objects.requireNonNull(response.body()).string());
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
