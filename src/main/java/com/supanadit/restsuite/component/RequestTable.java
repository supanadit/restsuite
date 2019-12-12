package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.Request;

import javax.swing.*;

public class RequestTable extends JScrollPane {
    protected Request[][] dataParams = {};
    protected String[] columnParams = {"Key", "Value"};
    protected JTable requestHeaderTable;

    public RequestTable() {
        this.requestHeaderTable = new JTable(this.dataParams, this.columnParams);
        this.setViewportView(this.requestHeaderTable);

        this.addMouseListener(new ParamsMenuListener());
    }
}
