package com.supanadit.restsuite.component;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenuItem exitMenuItem;

    public MenuBar() {
        JMenu file = new JMenu("File");

        file.add(new JMenuItem("New Project"));
        file.add(new JMenuItem("Open Project"));
        file.addSeparator();
        file.add(new JMenuItem("Export"));
        file.add(new JMenuItem("Import"));
        file.addSeparator();

        exitMenuItem = new JMenuItem("Exit");
        file.add(exitMenuItem);
        add(file);
    }

    public JMenuItem getExitMenu() {
        return exitMenuItem;
    }
}
