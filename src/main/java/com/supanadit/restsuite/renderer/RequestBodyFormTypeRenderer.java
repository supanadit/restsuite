package com.supanadit.restsuite.renderer;

import com.supanadit.restsuite.model.RequestBodyFormType;

import javax.swing.*;
import java.awt.*;

public class RequestBodyFormTypeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof RequestBodyFormType) {
            value = ((RequestBodyFormType) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
