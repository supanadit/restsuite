package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.supanadit.restsuite.component.ApiPanel;
import com.supanadit.restsuite.listener.DragListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class Main {
    public static Dimension dimension = new Dimension(1024, 600);
    public static boolean customTitleBar = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatDarculaLaf.install();
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

            JPanel sidePanel = new JPanel(new MigLayout("w 300,insets 11 10 10 10"));
            sidePanel.add(new JLabel("Environment"), "wrap");
            sidePanel.add(new JComboBox<>(), "pushx,growx");
            sidePanel.add(new JButton("Manage"), "wrap");
            sidePanel.add(new JLabel("Collection"), "wrap");

            JTextField searchCollection = new JTextField();
            sidePanel.add(searchCollection, "growx, pushx, wrap, span");
            sidePanel.add(new JSeparator(), "wrap,span, growx, pushx");
            DefaultMutableTreeNode style = new DefaultMutableTreeNode("Geo Smart");
            DefaultMutableTreeNode color = new DefaultMutableTreeNode("Point");
            DefaultMutableTreeNode font = new DefaultMutableTreeNode("Get Unique ID");
            style.add(color);
            style.add(font);
            DefaultMutableTreeNode red = new DefaultMutableTreeNode("Set");
            DefaultMutableTreeNode blue = new DefaultMutableTreeNode("SSE");
            DefaultMutableTreeNode black = new DefaultMutableTreeNode("Get All");
            color.add(red);
            color.add(blue);
            color.add(black);
            JTree collection = new JTree(style);
            Color background = UIManager.getColor("Panel.background");
            collection.setBackground(background);
            sidePanel.add(collection, "push, grow, span, wrap");

            frame.add(sidePanel, BorderLayout.LINE_END);
            frame.add(new ApiPanel(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(dimension);
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
            frame.setVisible(true);
        });
    }
}