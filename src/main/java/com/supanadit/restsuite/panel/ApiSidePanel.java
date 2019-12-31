package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.InputSearchCollection;
import com.supanadit.restsuite.helper.FontLoader;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ApiSidePanel extends JPanel {
    public ApiSidePanel() {
        this.setLayout(new MigLayout("w 300,insets 11 10 10 10"));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JComboBox<String> environmentComboBox = new JComboBox<>();
        JButton manageEnvironment = new JButton("Manage");

        JDialog environment = new JDialog();
        environment.setTitle("Environment");
        environment.setResizable(false);
        environment.setAlwaysOnTop(true);
        environment.setLayout(new MigLayout("fill,insets 0 0 0 0", "[]0[]"));

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Environment Name");

        environment.add(new JScrollPane(new JTable(defaultTableModel)), "grow");
        JPanel rightPanel = new JPanel(new MigLayout("flowy"));
        rightPanel.add(new JButton("+"), "pushx,growx");
        rightPanel.add(new JButton("-"), "pushx,growx");
        environment.add(rightPanel, "pushy,growy");
        environment.setSize(500, 500);
        environment.setLocation(dim.width / 2 - environment.getSize().width / 2, dim.height / 2 - environment.getSize().height / 2);
        manageEnvironment.addActionListener((e) -> {
            environment.setVisible(true);
        });

        JLabel environmentLabel = new JLabel("Environment");
        this.add(environmentLabel, "wrap");
        this.add(environmentComboBox, "pushx,growx");
        this.add(manageEnvironment, "wrap");
        this.add(new JLabel("Collection"), "wrap");

        this.add(InputSearchCollection.getComponent(), "growx, pushx, wrap, span");
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
