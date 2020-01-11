package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputSearchCollection;
import com.supanadit.restsuite.listener.CollectionTreeMouseMenuListener;
import com.supanadit.restsuite.model.EnvironmentItem;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ApiSidePanel extends JPanel {
    public ApiSidePanel() {
        setLayout(new MigLayout("w 300,insets 11 10 10 10"));


        JComboBox<EnvironmentItem> environmentComboBox = new JComboBox<>();
        JButton manageEnvironment = new JButton("Manage");

        EnvironmentListPanel environment = new EnvironmentListPanel();
        manageEnvironment.addActionListener((e) -> {
            environment.setVisible(true);
        });

        JLabel environmentLabel = new JLabel("Environment");
        add(environmentLabel, "wrap");
        add(environmentComboBox, "pushx,growx");
        add(manageEnvironment, "wrap");
        add(new JSeparator(), "wrap,span, growx, pushx");
        DefaultMutableTreeNode style = new DefaultMutableTreeNode("Collection");
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
        collection.addMouseListener(new CollectionTreeMouseMenuListener(collection));
        Color background = UIManager.getColor("Panel.background");
        collection.setBackground(background);

        add(collection, "push, grow, span, wrap");
    }
}
