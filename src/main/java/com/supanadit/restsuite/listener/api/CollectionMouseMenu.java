package com.supanadit.restsuite.listener.api;

import com.supanadit.restsuite.entity.CollectionStructureEntity;
import com.supanadit.restsuite.panel.rest.SidePanel;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

class CollectionMouseMenu extends JPopupMenu {
    public SidePanel sidePanel;

    public CollectionMouseMenu(SidePanel sidePanel) {
        // Declare JTree Collection variable
        this.sidePanel = sidePanel;
        // Make sure collection tree have a data
        if (sidePanel.tree.getSelectionCount() != 0) {
            // Get model from tree collection
            DefaultTreeModel model = (DefaultTreeModel) sidePanel.tree.getModel();
            // Get selection path
            TreePath[] paths = sidePanel.tree.getSelectionPaths();
            // Make sure selection path not null
            if (paths != null) {
                // Looping Path
                for (TreePath path : paths) {
                    // Get Selected Node
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                    // Make sure the selected node has a parent
                    if (selectedNode.getParent() != null) {
                        // Get User Object
                        Object userValue = selectedNode.getUserObject();
                        // If not type of folder
                        if (!selectedNode.getAllowsChildren()) {
                            // Make sure user value is type of collection structure entity
                            if (userValue instanceof CollectionStructureEntity) {
                                JMenuItem deleteCollection, duplicateCollection, moveCollectionToFolder;
                                // Create Menu for Delete Collection
                                deleteCollection = new JMenuItem("Delete Collection");
                                // Create Menu for Duplicate Collection
                                duplicateCollection = new JMenuItem("Duplicate Collection");
                                // Create Menu for Move Collection to another folder
                                moveCollectionToFolder = new JMenuItem("Move Collection to Folder");
                                // Menu Delete Action
                                deleteCollection.addActionListener((e) -> {
                                    // Create collection structure entity from userValue
                                    CollectionStructureEntity collectionStructureEntity = ((CollectionStructureEntity) userValue);
                                    // Initialize Transaction
                                    Transaction transaction = null;
                                    // Open Session
                                    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                                        // Start a transaction
                                        transaction = session.beginTransaction();
                                        // Delete collection structure entity with the relationship
                                        session.delete(collectionStructureEntity);
                                        // commit transaction
                                        transaction.commit();
                                        // delete from side panel collection menu
                                        model.removeNodeFromParent(selectedNode);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                });
                                // Add Duplicate collection
                                add(duplicateCollection);
                                // Add Move Collection menu
                                add(moveCollectionToFolder);
                                // Add Separator
                                addSeparator();
                                // Add Delete menu to parent Menu
                                add(deleteCollection);
                            }
                        } else {
                            JMenuItem deleteFolder, duplicateFolder, moveFolder;
                            JMenuItem addNewCollection;

                            deleteFolder = new JMenuItem("Delete Folder");
                            duplicateFolder = new JMenuItem("Duplicate Folder");
                            moveFolder = new JMenuItem("Move Folder");

                            addNewCollection = new JMenuItem("Add New Collection");

                            add(addNewCollection);
                            addSeparator();

                            add(duplicateFolder);
                            add(moveFolder);

                            addSeparator();
                            add(deleteFolder);
                        }
                    } else {
                        // Declare Variable
                        JMenuItem addNewCollection, addNewFolder;
                        // Create Menu New Collection
                        addNewCollection = new JMenuItem("Add New Collection");
                        addNewCollection.addActionListener(e -> {
                            sidePanel.restPanel.clear();
                        });
                        // Create Menu New Folder
                        addNewFolder = new JMenuItem("Add New Folder");
                        // Add menu new collection
                        add(addNewCollection);
                        // Add menu new folder
                        add(addNewFolder);
                    }
                }
            }
        }
    }
}