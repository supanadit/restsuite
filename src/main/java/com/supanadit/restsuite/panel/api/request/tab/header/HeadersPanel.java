package com.supanadit.restsuite.panel.api.request.tab.header;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HeadersPanel extends JPanel {
    HeadersFormPanel headersFormPanel;

    public HeadersPanel() {
        super(new MigLayout());
        headersFormPanel = new HeadersFormPanel();
        add(headersFormPanel, "grow,push");
    }

    public HeadersFormPanel getHeadersFormPanel() {
        return headersFormPanel;
    }
}
