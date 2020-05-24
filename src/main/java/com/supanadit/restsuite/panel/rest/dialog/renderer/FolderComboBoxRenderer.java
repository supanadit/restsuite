package com.supanadit.restsuite.panel.rest.dialog.renderer;

import com.supanadit.restsuite.entity.CollectionStructureFolderEntity;

import javax.swing.*;
import java.awt.*;

public class FolderComboBoxRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof CollectionStructureFolderEntity) {
            value = ((CollectionStructureFolderEntity) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
