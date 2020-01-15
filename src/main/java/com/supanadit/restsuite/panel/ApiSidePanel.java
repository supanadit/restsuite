package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.CoreDialog;
import com.supanadit.restsuite.listener.CollectionTreeMouseMenuListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;

public class ApiSidePanel extends JPanel {
    private JTree collection;
    private DefaultMutableTreeNode rootCollection;
    CoreDialog createNewCollection;
    JTextField collectionNameField;

    public ApiSidePanel() {
        setLayout(new MigLayout("fill,insets 3 5 0 0"));

        Color background = UIManager.getColor("Panel.background");

        JPanel panel = new JPanel(new MigLayout());
        panel.add(new JLabel("Collection"), "wrap");
        JButton addCollection = new JButton("Add Collection");

        addCollection.addActionListener(e -> {
            openNewDialog();
        });

        panel.add(addCollection, "pushx,growx");

        add(panel, "pushx,growx,wrap");

        createNewCollection = new CoreDialog("Create Collection", 300, 130);
        createNewCollection.setResizable(false);

        JLabel collectionNameLabel = new JLabel("Collection Name");
        createNewCollection.add(collectionNameLabel, "wrap");
        collectionNameField = new JTextField();
        createNewCollection.add(collectionNameField, "pushx,growx,wrap");
        JPanel bottomPanel = new JPanel(new MigLayout("rtl, insets 0 0 0 0"));
        createNewCollection.add(bottomPanel, "grow,push");
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        bottomPanel.add(cancelButton);
        bottomPanel.add(addButton);

        addButton.setFocusable(false);
        cancelButton.setFocusable(false);

        cancelButton.addActionListener(e -> {
            createNewCollection.setVisible(false);
        });

        addButton.addActionListener(e -> {
            addNewCollection(collectionNameField.getText());
            createNewCollection.setVisible(false);
        });

        JPanel collectionListPanel = new JPanel(new MigLayout("insets 0 5 0 0"));

        JScrollPane scrollPane = new JScrollPane(collectionListPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        rootCollection = new DefaultMutableTreeNode("Collection");
        rootCollection.setAllowsChildren(true);

        collection = new JTree(rootCollection);
        collection.setRootVisible(false);
        collection.addMouseListener(new CollectionTreeMouseMenuListener(this));
        collection.setBackground(background);

        collectionListPanel.add(collection, "push, grow, span, wrap");
        add(scrollPane, "push,grow");
    }

    public void openNewDialog() {
        collectionNameField.setText(null);
        collectionNameField.setRequestFocusEnabled(true);
        createNewCollection.setVisible(true);
    }

    public void addNewCollection(String name) {
        DefaultTreeModel model = (DefaultTreeModel) collection.getModel();
        rootCollection.add(new DefaultMutableTreeNode(name));
        model.reload((TreeNode) model.getRoot());
    }

    public JTree getCollection() {
        return collection;
    }
}
