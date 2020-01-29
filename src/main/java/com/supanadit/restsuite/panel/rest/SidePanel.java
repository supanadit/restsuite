package com.supanadit.restsuite.panel.rest;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JScrollPane {
    public SidePanel() {
        Color background = UIManager.getColor("Panel.background");

        JPanel panel = new JPanel(new MigLayout("insets 10 0 0 0"));

        JTree tree = new JTree();
        tree.setBackground(background);
        panel.add(tree, "push,grow");

        setViewportView(panel);
        setBorder(BorderFactory.createEmptyBorder());
    }
}
