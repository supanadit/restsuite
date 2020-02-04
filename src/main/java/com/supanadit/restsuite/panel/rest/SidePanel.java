package com.supanadit.restsuite.panel.rest;

import com.supanadit.restsuite.entity.CollectionStructureEntity;
import com.supanadit.restsuite.listener.api.CollectionTreeMouseMenuListener;
import com.supanadit.restsuite.panel.rest.callback.RestCallback;
import com.supanadit.restsuite.panel.rest.dialog.renderer.CollectionTreeRenderer;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import net.miginfocom.swing.MigLayout;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

public class SidePanel extends JScrollPane implements RestCallback {
    DefaultMutableTreeNode root;
    DefaultTreeModel treeModel;
    JTree tree;

    public SidePanel(RestPanel restPanel) {
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
                    restPanel.setData(collectionStructureEntity.getCollectionEntity());
                }
            }
        });
        tree.addMouseListener(new CollectionTreeMouseMenuListener(tree));

        panel.add(tree, "push,grow");

        setViewportView(panel);
        setBorder(BorderFactory.createEmptyBorder());

        restPanel.addCallback(this);
        // Load Menu List
        loadData();
    }

    public void loadData() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<CollectionStructureEntity> projects = session.createQuery("from CollectionStructureEntity ", CollectionStructureEntity.class).list();
            root.removeAllChildren();
            projects.forEach(s -> {
                // Create Node Menu
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(s);
                // This is collection and cannot add sub collection inside collection
                node.setAllowsChildren(false);
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
