package com.supanadit.restsuite.panel.rest;

import com.supanadit.restsuite.component.core.callback.ActionDialogCallback;
import com.supanadit.restsuite.entity.CollectionEntity;
import com.supanadit.restsuite.entity.CollectionStructureEntity;
import com.supanadit.restsuite.entity.CollectionStructureFolderEntity;
import com.supanadit.restsuite.listener.api.CollectionTreeMouseMenuListener;
import com.supanadit.restsuite.panel.rest.callback.RestCallback;
import com.supanadit.restsuite.panel.rest.dialog.FolderDialog;
import com.supanadit.restsuite.panel.rest.dialog.NewApiDialog;
import com.supanadit.restsuite.panel.rest.dialog.renderer.CollectionTreeRenderer;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import net.miginfocom.swing.MigLayout;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

public class SidePanel extends JScrollPane implements RestCallback {
    public DefaultMutableTreeNode root;
    public DefaultTreeModel treeModel;
    public JTree tree;
    public RestPanel restPanel;
    public FolderDialog folderDialog;
    public NewApiDialog newApiDialog;

    public SidePanel(RestPanel restPanel) {
        this.restPanel = restPanel;

        newApiDialog = new NewApiDialog();
        newApiDialog.addAction(new ActionDialogCallback() {
            @Override
            public void cancelAction() {
                newApiDialog.close();
            }

            @Override
            public void saveAction() {
                restPanel.clear();
                restPanel.titleButton.setText(newApiDialog.getName());
                newApiDialog.close();
            }
        });

        folderDialog = new FolderDialog();
        folderDialog.addAction(new ActionDialogCallback() {
            @Override
            public void cancelAction() {
                folderDialog.close();
            }

            @Override
            public void saveAction() {
                var collectionStructureFolderEntity = new CollectionStructureFolderEntity(folderDialog.getName());
                // Initialize Transaction
                Transaction transaction = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    // start a transaction
                    transaction = session.beginTransaction();
                    // save the project objects
                    session.saveOrUpdate(collectionStructureFolderEntity);
                    // commit transaction
                    transaction.commit();
                    // Refresh side panel
                    loadData();
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }
                folderDialog.close();
            }
        });

        Color background = UIManager.getColor("Panel.background");

        JPanel panel = new JPanel(new MigLayout("insets 10 0 0 0"));

        root = new DefaultMutableTreeNode("Collection");
        treeModel = new DefaultTreeModel(root);

        tree = new JTree(treeModel);
        tree.setBackground(background);
        tree.setCellRenderer(new CollectionTreeRenderer());
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null) {
                Object userValue = selectedNode.getUserObject();
                if (userValue instanceof CollectionEntity) {
                    CollectionEntity collectionEntity = (CollectionEntity) userValue;
                    restPanel.setData(collectionEntity);
                }
                if (selectedNode.getParent() == null) {
                    restPanel.clear();
                }
            }
        });
        tree.addMouseListener(new CollectionTreeMouseMenuListener(this));

        panel.add(tree, "push,grow");

        setViewportView(panel);
        setBorder(BorderFactory.createEmptyBorder());

        restPanel.addCallback(this);
        // Load Menu List
        loadData();
    }

    public void loadData() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Clear first
            root.removeAllChildren();
            // Folders
            List<CollectionStructureFolderEntity> folders = session.createQuery("from CollectionStructureFolderEntity", CollectionStructureFolderEntity.class).list();
            folders.forEach(s -> {
                // Create Node Menu
                DefaultMutableTreeNode folder = new DefaultMutableTreeNode(s);
                // This is folder and can add sub collection inside folder
                folder.setAllowsChildren(true);
                // Get all structure entity for this folder
                Hibernate.initialize(s.getStructure());
                for (CollectionStructureEntity structureEntity : s.getStructure()) {
                    // Create Node Menu
                    Hibernate.initialize(structureEntity.getCollectionEntity());
                    DefaultMutableTreeNode collection = new DefaultMutableTreeNode(structureEntity.getCollectionEntity());
                    // This is collection and cannot add sub collection inside collection
                    collection.setAllowsChildren(false);
                    // Add collection to folder
                    folder.add(collection);
                }
                // Get sub folder include collection
                for (CollectionStructureFolderEntity folderEntity : s.getChild()) {
                    Hibernate.initialize(s.getChild());
                    getSubFolder(folder, folderEntity);
                }
                // add folder to root tree
                root.add(folder);
            });
            // Collections
            List<CollectionStructureEntity> collections = session.createQuery("from CollectionStructureEntity where collectionStructureFolderEntity = null", CollectionStructureEntity.class).list();
            collections.forEach(s -> {
                // Create Node Menu
                DefaultMutableTreeNode folder = null;
                if (s.getCollectionStructureFolderEntity() != null) {
                    folder = new DefaultMutableTreeNode(s.getCollectionStructureFolderEntity());
                    folder.setAllowsChildren(true);
                }
                // Create Node Menu
                Hibernate.initialize(s.getCollectionEntity());
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(s.getCollectionEntity());
                // This is collection and cannot add sub collection inside collection
                node.setAllowsChildren(false);
                if (folder != null) {
                    folder.add(node);
                    root.add(folder);
                } else {
                    // add collection to root tree
                    root.add(node);
                }
            });
            treeModel.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCollection(DefaultMutableTreeNode folderNode, CollectionStructureFolderEntity folderEntity) {
        Hibernate.initialize(folderEntity.getStructure());
        for (CollectionStructureEntity collectionEntity : folderEntity.getStructure()) {
            // Create Node Menu
            Hibernate.initialize(collectionEntity.getCollectionEntity());
            DefaultMutableTreeNode collection = new DefaultMutableTreeNode(collectionEntity.getCollectionEntity());
            // This is collection and cannot add sub collection inside collection
            collection.setAllowsChildren(false);
            // Add collection to folder
            folderNode.add(collection);
        }
    }

    public void getSubFolder(DefaultMutableTreeNode parentFolderNode, CollectionStructureFolderEntity folderEntity) {
        Hibernate.initialize(folderEntity.getChild());
        for (CollectionStructureFolderEntity collectionStructureFolderEntity : folderEntity.getChild()) {
            // Create Node Menu
            Hibernate.initialize(collectionStructureFolderEntity);
            DefaultMutableTreeNode folder = new DefaultMutableTreeNode(collectionStructureFolderEntity);
            // This is collection and cannot add sub collection inside collection
            folder.setAllowsChildren(true);
            // Get Collection
            getCollection(folder, collectionStructureFolderEntity);
            // Add folder to folder
            parentFolderNode.add(folder);
        }
    }

    @Override
    public void saved() {
        loadData();
    }
}
