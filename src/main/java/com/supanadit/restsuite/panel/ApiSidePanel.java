package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.component.CoreDialog;
import com.supanadit.restsuite.listener.CollectionTreeMouseMenuListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Arrays;

public class ApiSidePanel extends JPanel {
    private JTree collection;
    private DefaultMutableTreeNode rootCollection;
    CoreDialog createNewCollection;
    JTextField collectionNameField;
    private DefaultMutableTreeNode selectedNode;

    private DefaultMutableTreeNode parentNode;

    JRadioButton collectionFolder;
    JRadioButton collectionItem;

    public ApiSidePanel() {
        setLayout(new MigLayout("fill,insets 4 5 0 0"));

        Color background = UIManager.getColor("Panel.background");

        JPanel panel = new JPanel(new MigLayout("", "", "[][fill,grow]"));
        JButton addCollection = new JButton("Add Collection");

        addCollection.addActionListener(e -> {
            openNewDialog(rootCollection);
        });

        panel.add(addCollection, "pushx,growx");

        add(panel, "pushx,growx,wrap");

        createNewCollection = new CoreDialog("Create Collection", 400, 130);
        createNewCollection.setResizable(false);

        collectionFolder = new JRadioButton("Folder");
        collectionItem = new JRadioButton("Collection");

        collectionFolder.setSelected(true);

        ButtonGroup group = new ButtonGroup();

        group.add(collectionFolder);
        group.add(collectionItem);

        JLabel collectionNameLabel = new JLabel("Collection Name");
        createNewCollection.add(collectionNameLabel, "wrap");
        collectionNameField = new JTextField();
        createNewCollection.add(collectionNameField, "pushx,growx,wrap");
        JPanel bottomPanel = new JPanel(new MigLayout("rtl, insets 0 0 0 0"));
        createNewCollection.add(bottomPanel, "grow,push");

        JPanel radioSelectionPanel = new JPanel(new MigLayout("insets n 0 n 0"));
        radioSelectionPanel.add(collectionFolder);
        radioSelectionPanel.add(collectionItem);

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        bottomPanel.add(cancelButton);
        bottomPanel.add(addButton);
        bottomPanel.add(radioSelectionPanel, "push,grow");


        addButton.setFocusable(false);
        cancelButton.setFocusable(false);

        cancelButton.addActionListener(e -> {
            createNewCollection.setVisible(false);
        });

        addButton.addActionListener(e -> {
            addNewCollection(collectionNameField.getText());
            createNewCollection.setVisible(false);
            collectionFolder.setSelected(true);
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

        collection.addTreeSelectionListener(e -> {
            selectedNode = (DefaultMutableTreeNode) collection.getLastSelectedPathComponent();
        });

        collectionListPanel.add(collection, "push, grow, span, wrap");
        add(scrollPane, "push,grow");
    }

    public void openNewDialog(DefaultMutableTreeNode parentNode) {
        this.parentNode = parentNode;
        collectionNameField.setText(null);
        collectionNameField.setRequestFocusEnabled(true);
        createNewCollection.setVisible(true);
    }

    public void addNewCollection(String name) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(name);
        
        newNode.setAllowsChildren(collectionFolder.isSelected());
        parentNode.add(newNode);
        reloadJTree();
    }

    public void reloadJTree() {
        DefaultTreeModel model = (DefaultTreeModel) collection.getModel();
        model.reload((TreeNode) model.getRoot());
    }

    public void addNewChildSubCollection() {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Test");
        selectedNode.add(newNode);
        System.out.println(rootCollection.getIndex(selectedNode));
    }

    public JTree getCollection() {
        return collection;
    }

    public DefaultMutableTreeNode getSelectedNode() {
        return selectedNode;
    }
}
