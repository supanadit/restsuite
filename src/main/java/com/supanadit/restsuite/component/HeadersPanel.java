package com.supanadit.restsuite.component;

import io.reactivex.subjects.PublishSubject;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HeadersPanel extends JPanel {
    protected RequestTable requestTable;

    public HeadersPanel() {
        super(new MigLayout());
        this.requestTable = new RequestTable();

        this.add(this.requestTable, "growx,pushx");
    }

    public void setSubject(PublishSubject<JTable> table) {
        this.requestTable.setSubject(table);
    }
}
