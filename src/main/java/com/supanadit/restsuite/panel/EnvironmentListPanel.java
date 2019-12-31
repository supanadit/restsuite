package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.listener.EnvironmentListScrollPaneMenuListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EnvironmentListPanel extends JDialog {
    DefaultTableModel defaultTableModel;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame dialogNew;
    JTable table;

    public EnvironmentListPanel() {
        this.setTitle("Environment");
        this.setResizable(false);
        this.setLayout(new MigLayout("fill,insets 0 0 0 0"));

        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Environment Variable");

        table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.addMouseListener(new EnvironmentListScrollPaneMenuListener(this));

        this.add(scrollPane, "grow");
        this.setSize(500, 500);
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);


        // Dialog New
        dialogNew = new JFrame();
        dialogNew.setLayout(new MigLayout("fill,insets 0 0 0 0"));
        dialogNew.setTitle("Add New Environment");
        dialogNew.setResizable(false);

        JPanel body = new JPanel(new MigLayout());

        JTextField environmentName = new JTextField();
        JButton add = new JButton("Add");
        JButton cancel = new JButton("Cancel");
        add.setEnabled(false);
        add.addActionListener(e -> {
            DefaultTableModel dtm = (DefaultTableModel) this.table.getModel();
            dtm.addRow(new Object[]{environmentName.getText()});
            dialogNew.setVisible(false);
            environmentName.setText(null);
        });

        environmentName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!environmentName.getText().equals("")) {
                    add.setEnabled(true);
                } else {
                    add.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!environmentName.getText().equals("")) {
                    add.setEnabled(true);
                } else {
                    add.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        body.add(new JLabel("Environment Name"), "wrap");
        body.add(environmentName, "pushx,growx,wrap");
        body.add(new JLabel("Variable"), "wrap");
        DefaultTableModel variableEnvironmentTableModel = new DefaultTableModel();
        variableEnvironmentTableModel.addColumn("Key");
        variableEnvironmentTableModel.addColumn("Value");
        body.add(new JScrollPane(new JTable(variableEnvironmentTableModel)), "grow,push");
        JPanel footer = new JPanel(new MigLayout("rtl"));
        cancel.addActionListener(e -> {
            dialogNew.setVisible(false);
        });
        footer.add(cancel);
        footer.add(add);

        dialogNew.setSize(500, 500);
        dialogNew.add(body, "grow,push");
        dialogNew.add(footer, "south");
    }

    public void openAddNewDialog() {
        dialogNew.setVisible(true);
        dialogNew.setLocation(dim.width / 2 - dialogNew.getSize().width / 2, dim.height / 2 - dialogNew.getSize().height / 2);
    }
}
