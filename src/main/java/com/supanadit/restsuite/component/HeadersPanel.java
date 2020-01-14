package com.supanadit.restsuite.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HeadersPanel extends JPanel {
    private RequestTable requestTable;
    private JButton add;
    private JButton remove;

    public HeadersPanel() {
        super(new MigLayout());
        requestTable = new RequestTable(this);

        JPanel containerButton = new JPanel(new MigLayout("flowy,insets 0 0 0 0"));
        add = new JButton("Add");
        remove = new JButton("Remove");

        remove.setEnabled(false);

        add.addActionListener(e -> {
            requestTable.addNewEmptyRow();
        });

        remove.addActionListener(e -> {
            requestTable.deleteSelectedRow();
            if (requestTable.getRequest().size() == 0) {
                remove.setEnabled(false);
            }
        });

        containerButton.add(add);
        containerButton.add(remove);

        add(requestTable, "growx,pushx");
        add(containerButton, "growy");
    }

    public RequestTable getRequestTable() {
        return requestTable;
    }

    public JButton getAddButton() {
        return add;
    }

    public JButton getRemoveButton() {
        return remove;
    }
}
