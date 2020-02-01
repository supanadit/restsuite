package com.supanadit.restsuite.panel.rest.dialog.renderer;

import com.supanadit.restsuite.panel.rest.dialog.model.FolderItem;

import javax.swing.*;
import java.awt.*;

public class FolderComboBoxRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof FolderItem) {
            value = ((FolderItem) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
