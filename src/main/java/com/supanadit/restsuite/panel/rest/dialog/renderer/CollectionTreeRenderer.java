package com.supanadit.restsuite.panel.rest.dialog.renderer;

import com.supanadit.restsuite.entity.CollectionStructureEntity;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CollectionTreeRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object userValue = node.getUserObject();

            if (userValue instanceof CollectionStructureEntity) {
                setText(((CollectionStructureEntity) userValue).getCollectionEntity().getTitle());
            }
        }
        return this;
    }
}