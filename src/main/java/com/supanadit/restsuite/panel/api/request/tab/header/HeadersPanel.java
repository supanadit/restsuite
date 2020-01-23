package com.supanadit.restsuite.panel.api.request.tab.header;

import com.supanadit.restsuite.Main;
import com.supanadit.restsuite.component.table.RequestTable;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HeadersPanel extends JPanel {
    private RequestTable requestTable;
    private JButton add;
    private JButton remove;

    public HeadersPanel() {
        super(new MigLayout());
        requestTable = new RequestTable(this);

        JPanel containerButton = new JPanel(new MigLayout("flowy,insets 0 0 0 0"));
        URL addIconURL = Main.class.getClassLoader().getResource("icon/add.png");
        URL removeIconURL = Main.class.getClassLoader().getResource("icon/remove.png");

        assert addIconURL != null;
        Icon addIcon = new ImageIcon(new ImageIcon(addIconURL).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
        assert removeIconURL != null;
        Icon removeIcon = new ImageIcon(new ImageIcon(removeIconURL).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));

        add = new JButton(addIcon);
        remove = new JButton(removeIcon);

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

        add(requestTable, "grow,push");
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
