package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestBodyFormType;

import javax.swing.*;
import java.awt.*;

public class RequestBodyComboBoxTypeColumn extends DefaultCellEditor {

    public RequestBodyComboBoxTypeColumn() {
        super(new JComboBox<String>());
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) ((JComboBox<String>) getComponent()).getModel();
        model.addElement(RequestBodyFormType.FIELD().getName());
        model.addElement(RequestBodyFormType.FILE().getName());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}