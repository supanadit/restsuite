package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatDarculaLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//            FlatDarculaLaf.install();
            JFrame frame = new JFrame("Rest Suite");

            JPanel panel = new JPanel(new MigLayout());
            panel.add(new Label("API Name"), "wrap");
            panel.add(new JTextField("http://www.google.com"), "growx,pushx");
            panel.add(new JButton("Send"), "wrap");

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

            // Table Headers

            String[][] dataHeaders = {};
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

            panelBody.add(bodyRequest, "grow, push, span 2");

            panel.add(tabRequest, "growx, pushx, span 2, wrap, hmax 250");

            JTabbedPane tabResponse = new JTabbedPane();

            JPanel panelBodyResponse = new JPanel(new MigLayout("fill"));

            tabResponse.add("Response", panelBodyResponse);

            JTextArea responseBody = new JTextArea();

            panelBodyResponse.add(responseBody, "grow, push");

            panel.add(tabResponse, "growx, growy, pushy, pushx, span 2");

            frame.add(panel, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(600, 600));
            frame.setVisible(true);
        });
    }
}
