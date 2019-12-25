package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.supanadit.restsuite.component.ApiPanel;
import com.supanadit.restsuite.listener.DragListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static Dimension dimension = new Dimension(1024, 600);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatDarculaLaf.install();
            JFrame frame = new JFrame("Rest Suite");
            frame.setUndecorated(true);

            DragListener drag = new DragListener();
            frame.addMouseListener(drag);
            frame.addMouseMotionListener(drag);

            JButton minimize = new JButton("Minimize");
            JButton close = new JButton("Close");

            minimize.addActionListener((e) -> {
                frame.setState(Frame.ICONIFIED);
            });

            close.addActionListener((e) -> {
                frame.dispose();
            });

            JPanel panel = new JPanel(new MigLayout());
            panel.add(new JLabel("Rest Suite - Rest API Testing for Professional"), "push");
            panel.add(minimize);
            panel.add(close);

            frame.add(panel, BorderLayout.NORTH);
            frame.add(new ApiPanel(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(dimension);
            frame.setVisible(true);
        });
    }
}