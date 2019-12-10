package com.supanadit.restsuite.component;

import com.supanadit.restsuite.model.RequestHeader;

import javax.swing.*;

public class RequestParamTable extends JScrollPane {
    protected RequestHeader[][] dataParams = {};
    protected String[] columnParams = {"Key", "Value"};
    protected JTable requestHeaderTable;

    public RequestParamTable() {
        this.requestHeaderTable = new JTable(this.dataParams, this.columnParams);
        this.setViewportView(this.requestHeaderTable);
        this.addMouseListener(new ParamsMenuListener());
    }
}
