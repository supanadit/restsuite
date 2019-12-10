package com.supanadit.restsuite.helper;

import com.supanadit.restsuite.model.RequestType;

import javax.swing.*;
import java.awt.*;

public class RequestTypeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof RequestType) {
            value = ((RequestType) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
