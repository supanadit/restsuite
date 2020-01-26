package com.supanadit.restsuite.renderer;

import com.supanadit.restsuite.model.RequestBodyRawTypeModel;

import javax.swing.*;
import java.awt.*;

public class RequestBodyRawTypeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof RequestBodyRawTypeModel) {
            value = ((RequestBodyRawTypeModel) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
