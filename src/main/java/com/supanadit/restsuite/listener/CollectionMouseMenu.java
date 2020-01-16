package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.panel.ApiSidePanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

class CollectionMouseMenu extends JPopupMenu {
    public ApiSidePanel apiSidePanel;

    public CollectionMouseMenu(ApiSidePanel apiSidePanel) {
        this.apiSidePanel = apiSidePanel;
        if (apiSidePanel.getCollection().getSelectionCount() != 0) {
            // Delete
            JMenuItem deleteData = new JMenuItem("Delete");
            deleteData.addActionListener((e) -> {
                DefaultTreeModel model = (DefaultTreeModel) apiSidePanel.getCollection().getModel();
                TreePath[] paths = apiSidePanel.getCollection().getSelectionPaths();
                if (paths != null) {
                    for (TreePath path : paths) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (node.getParent() != null) {
                            model.removeNodeFromParent(node);
                        }
                    }
                }
            });
            add(deleteData);
            if (apiSidePanel.getSelectedNode() != null) {
                DefaultMutableTreeNode node = apiSidePanel.getSelectedNode();
                if (node.getAllowsChildren()) {
                    JMenuItem addCollection = new JMenuItem("Add");
                    addCollection.addActionListener(e -> {
                        apiSidePanel.openNewDialog(node);
                    });
                    add(addCollection);
                }
            }
        }
    }
}