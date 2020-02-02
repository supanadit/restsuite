package com.supanadit.restsuite.panel.rest;

import com.supanadit.restsuite.entity.CollectionStructureEntity;
import com.supanadit.restsuite.panel.rest.callback.RestCallback;
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
                String title = s.getCollectionEntity().getTitle();
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(title);
                root.add(node);
                System.out.println(title);
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
