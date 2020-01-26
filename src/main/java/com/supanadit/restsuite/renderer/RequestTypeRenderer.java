package com.supanadit.restsuite.renderer;

import com.supanadit.restsuite.model.RequestTypeModel;

import javax.swing.*;
import java.awt.*;

public class RequestTypeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof RequestTypeModel) {
            value = ((RequestTypeModel) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
