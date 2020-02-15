package com.supanadit.restsuite.panel.rest;

import com.supanadit.restsuite.component.core.callback.ActionDialogCallback;
import com.supanadit.restsuite.entity.CollectionEntity;
import com.supanadit.restsuite.entity.CollectionHeaderEntity;
import com.supanadit.restsuite.entity.CollectionStructureEntity;
import com.supanadit.restsuite.entity.CollectionStructureFolderEntity;
import com.supanadit.restsuite.listener.api.CollectionTreeMouseMenuListener;
import com.supanadit.restsuite.panel.rest.callback.RestCallback;
import com.supanadit.restsuite.panel.rest.dialog.FolderDialog;
import com.supanadit.restsuite.panel.rest.dialog.renderer.CollectionTreeRenderer;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import net.miginfocom.swing.MigLayout;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public SidePanel(RestPanel restPanel) {
        this.restPanel = restPanel;

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
                if (userValue instanceof CollectionStructureEntity) {
                    CollectionStructureEntity collectionStructureEntity = ((CollectionStructureEntity) userValue);
                    CollectionEntity collectionEntity = collectionStructureEntity.getCollectionEntity();
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
                // add folder to root tree
                root.add(folder);
            });
            // Collections
            List<CollectionStructureEntity> collections = session.createQuery("from CollectionStructureEntity", CollectionStructureEntity.class).list();
            collections.forEach(s -> {
                // Create Node Menu
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(s);
                // This is collection and cannot add sub collection inside collection
                node.setAllowsChildren(false);
                // add collection to root tree
                root.add(node);
            });
            treeModel.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saved() {
        loadData();
    }
}
