package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.supanadit.restsuite.listener.DragListener;
import com.supanadit.restsuite.panel.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class Main {
    public static Dimension dimension = new Dimension(1024, 600);
    public static boolean customTitleBar = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.install();
            JFrame frame = new JFrame("Rest Suite");

            frame.setUndecorated(customTitleBar);

            if (customTitleBar) {
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
                Color lineColor = UIManager.getColor("Table.gridColor");
                panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, lineColor));
                frame.add(panel, BorderLayout.NORTH);
            }

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            frame.add(new MainPanel(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(dimension);
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
            frame.setVisible(true);
        });
    }
}