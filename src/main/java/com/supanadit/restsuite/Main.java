package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.supanadit.restsuite.component.CoreFrame;
import com.supanadit.restsuite.component.MenuBar;
import com.supanadit.restsuite.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.install();

            CoreFrame frame = new CoreFrame("Rest Suite");
            frame.setName("Rest API Testing for Professional");

            MenuBar menuBar = new MenuBar();
            menuBar.getExitMenuItem().addActionListener(e -> {
                frame.dispose();
            });

            // frame.setJMenuBar(menuBar);

            frame.add(new MainPanel(menuBar), BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}