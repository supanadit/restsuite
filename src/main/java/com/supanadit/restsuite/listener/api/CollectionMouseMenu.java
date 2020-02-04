package com.supanadit.restsuite.listener.api;

import com.supanadit.restsuite.entity.CollectionStructureEntity;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

class CollectionMouseMenu extends JPopupMenu {
    public JTree collection;

    public CollectionMouseMenu(JTree collection) {
        this.collection = collection;
        if (collection.getSelectionCount() != 0) {
            JMenuItem deleteData = new JMenuItem("Delete");
            deleteData.addActionListener((e) -> {
                DefaultTreeModel model = (DefaultTreeModel) collection.getModel();
                TreePath[] paths = collection.getSelectionPaths();
                if (paths != null) {
                    for (TreePath path : paths) {
                        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (selectedNode.getParent() != null) {
                            Object userValue = selectedNode.getUserObject();
                            if (userValue instanceof CollectionStructureEntity) {
                                CollectionStructureEntity collectionStructureEntity = ((CollectionStructureEntity) userValue);
                                // Initialize Transaction
                                Transaction transaction = null;
                                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                                    // Start a transaction
                                    transaction = session.beginTransaction();
                                    session.delete(collectionStructureEntity);
                                    // commit transaction
                                    transaction.commit();
                                    model.removeNodeFromParent(selectedNode);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });
            add(deleteData);
        }
    }
}