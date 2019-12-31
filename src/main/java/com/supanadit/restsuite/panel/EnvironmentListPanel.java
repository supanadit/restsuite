package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.listener.EnvironmentListScrollPaneMenuListener;
import com.supanadit.restsuite.listener.RequestMouseScrollPaneMenuListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EnvironmentListPanel extends JDialog {
    public EnvironmentListPanel() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Environment");
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLayout(new MigLayout("fill,insets 0 0 0 0"));

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Environment Name");

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.addMouseListener(new EnvironmentListScrollPaneMenuListener(table));

        this.add(scrollPane, "grow");
        this.setSize(500, 500);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
}
