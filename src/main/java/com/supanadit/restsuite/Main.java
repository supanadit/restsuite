package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.supanadit.restsuite.component.CustomTitleBar;
import com.supanadit.restsuite.component.MenuBar;
import com.supanadit.restsuite.helper.DefaultIcon;
import com.supanadit.restsuite.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static Dimension dimension = new Dimension(1024, 600);
    public static boolean customTitleBar = false;

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.install();

            JFrame frame = new JFrame("Rest Suite");
            frame.setName("Rest API Testing for Professional");
            frame.setIconImage(new DefaultIcon().getImage());
            frame.setUndecorated(customTitleBar);

            if (customTitleBar) {
                new CustomTitleBar(frame);
            }

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            MenuBar menuBar = new MenuBar();

            menuBar.getExitMenu().addActionListener(e -> {
                frame.dispose();
            });

            frame.setJMenuBar(menuBar);
            frame.add(new MainPanel(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(dimension);
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
            frame.setVisible(true);
        });
    }
}