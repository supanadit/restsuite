package com.supanadit.restsuite.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ParamsPanel extends JPanel {
    public ParamsPanel() {
        super(new MigLayout());

        this.add(new RequestTable(), "growx,pushx");
    }
}
