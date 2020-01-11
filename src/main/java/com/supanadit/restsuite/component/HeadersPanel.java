package com.supanadit.restsuite.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HeadersPanel extends JPanel {
    private RequestTable requestTable;

    public HeadersPanel() {
        super(new MigLayout());
        requestTable = new RequestTable();

        add(requestTable, "growx,pushx");
    }

    public RequestTable getRequestTable() {
        return requestTable;
    }
}
