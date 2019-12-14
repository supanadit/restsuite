package com.supanadit.restsuite.listener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class RequestMouseRowMenu extends JPopupMenu {
    protected JTable table;


    public RequestMouseRowMenu(JTable table) {
        this.table = table;
        JMenuItem deleteData = new JMenuItem("Delete");
        deleteData.addActionListener((e) -> {
            DefaultTableModel model = (DefaultTableModel) this.table.getModel();
            model.removeRow(this.table.getSelectedRow());
        });
        add(deleteData);
    }
}