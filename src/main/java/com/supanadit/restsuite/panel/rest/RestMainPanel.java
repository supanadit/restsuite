package com.supanadit.restsuite.panel.rest;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class RestMainPanel extends JPanel {
    public RestMainPanel() {
        setLayout(new MigLayout("insets 0 0 0 0", "[][200!]"));
        add(new RestPanel(), "push,grow");
        add(new SidePanel(), "push,grow");
    }
}
