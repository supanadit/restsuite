package com.supanadit.restsuite.renderer;

import com.supanadit.restsuite.model.RequestBodyFormTypeModel;

import javax.swing.*;
import java.awt.*;

public class RequestBodyFormTypeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof RequestBodyFormTypeModel) {
            value = ((RequestBodyFormTypeModel) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
