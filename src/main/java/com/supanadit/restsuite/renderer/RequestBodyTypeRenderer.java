package com.supanadit.restsuite.renderer;

import com.supanadit.restsuite.model.BodyTypeModel;

import javax.swing.*;
import java.awt.*;

public class RequestBodyTypeRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof BodyTypeModel) {
            value = ((BodyTypeModel) value).getName();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}
