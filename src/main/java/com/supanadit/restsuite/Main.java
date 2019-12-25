package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.supanadit.restsuite.component.ApiPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static Dimension dimension = new Dimension(1024, 600);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatDarculaLaf.install();
            JFrame frame = new JFrame("Rest Suite");

            frame.add(new ApiPanel(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(dimension);
            frame.setVisible(true);
        });
    }
}
