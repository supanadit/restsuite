package com.supanadit.restsuite.listener;

import com.supanadit.restsuite.component.RequestTable;

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
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                        if (node.getParent() != null) {
                            model.removeNodeFromParent(node);
                        }
                    }
                }
            });
            add(deleteData);
        }
    }
}