package com.supanadit.restsuite.panel;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class SideApiPanel extends JPanel {
    public SideApiPanel() {
        this.setLayout(new MigLayout("w 300,insets 11 10 10 10"));
        this.add(new JLabel("Environment"), "wrap");
        this.add(new JComboBox<>(), "pushx,growx");
        this.add(new JButton("Manage"), "wrap");
        this.add(new JLabel("Collection"), "wrap");

        JTextField searchCollection = new JTextField();
        this.add(searchCollection, "growx, pushx, wrap, span");
        this.add(new JSeparator(), "wrap,span, growx, pushx");
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
        this.add(collection, "push, grow, span, wrap");
    }
}
