package com.supanadit.restsuite.component;

import io.reactivex.subjects.PublishSubject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HeadersPanel extends JPanel {
    protected RequestTable requestTable;

    public HeadersPanel() {
        super(new MigLayout());
        requestTable = new RequestTable();

        add(requestTable, "growx,pushx");
    }

    public void setSubject(PublishSubject<JTable> table) {
        requestTable.setSubject(table);
    }
}
