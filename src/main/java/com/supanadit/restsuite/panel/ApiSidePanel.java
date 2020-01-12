package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.listener.CollectionTreeMouseMenuListener;
import com.supanadit.restsuite.model.EnvironmentItem;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ApiSidePanel extends JPanel {
    private DefaultMutableTreeNode collectionRoot;
    private JTree collectionTree;

    public ApiSidePanel() {
        setLayout(new MigLayout("w 300,insets 11 10 10 10"));
        Color background = UIManager.getColor("Panel.background");


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
        collectionRoot = new DefaultMutableTreeNode("Collection");
        DefaultMutableTreeNode font = new DefaultMutableTreeNode("Get Unique ID");
        collectionRoot.add(font);

        collectionTree = new JTree(collectionRoot);
        collectionTree.addMouseListener(new CollectionTreeMouseMenuListener(collectionTree));
        collectionTree.setBackground(background);

        add(collectionTree, "push, grow, span, wrap");
    }

    public JTree getCollectionTree() {
        return collectionTree;
    }

    public DefaultMutableTreeNode getCollectionRoot() {
        return collectionRoot;
    }
}
