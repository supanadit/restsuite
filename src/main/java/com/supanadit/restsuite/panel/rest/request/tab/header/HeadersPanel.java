package com.supanadit.restsuite.panel.rest.request.tab.header;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HeadersPanel extends JPanel {
    public HeadersFormPanel headersFormPanel;

    public HeadersPanel() {
        super(new MigLayout());

        headersFormPanel = new HeadersFormPanel();

        add(headersFormPanel, "grow,push");
    }

    public HeadersFormPanel getHeadersFormPanel() {
        return headersFormPanel;
    }
}
