package com.supanadit.restsuite.helper;

import com.supanadit.restsuite.model.RequestBodyRawType;

import javax.swing.*;
import java.awt.*;

public class RequestBodyRawTypeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof RequestBodyRawType) {
            value = ((RequestBodyRawType) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}