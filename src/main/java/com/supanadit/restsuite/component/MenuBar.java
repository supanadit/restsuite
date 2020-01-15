package com.supanadit.restsuite.component;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenu environment;

    // File Menu Item
    private JMenuItem exitMenuItem;
    // Environment Menu Item
    private JMenuItem environmentManageMenuItem;

    public MenuBar() {
        // File
        file = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        // Environment
        environment = new JMenu("Environment");
        environmentManageMenuItem = new JMenuItem("Manage");

        // File
        file.add(exitMenuItem);
        // Environment
        environment.add(environmentManageMenuItem);

        add(file);
        // add(environment);
    }

    public JMenu getFileMenu() {
        return file;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JMenu getEnvironmentMenu() {
        return environment;
    }

    public JMenuItem getEnvironmentManageMenuItem() {
        return environmentManageMenuItem;
    }
}
