package com.supanadit.restsuite.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HeadersPanel extends JPanel {
    public HeadersPanel() {
        super(new MigLayout());

        this.add(new RequestHeaderTable(), "growx,pushx");
    }
}
