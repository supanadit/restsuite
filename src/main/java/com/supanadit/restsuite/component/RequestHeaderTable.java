package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestHeader;

import javax.swing.*;

public class RequestHeaderTable extends JScrollPane {
    protected RequestHeader[][] dataParams = {};
    protected String[] columnParams = {"Key", "Value"};
    protected JTable requestHeaderTable;

    public RequestHeaderTable() {
        this.requestHeaderTable = new JTable(this.dataParams, this.columnParams);
        this.setViewportView(this.requestHeaderTable);
    }
}
