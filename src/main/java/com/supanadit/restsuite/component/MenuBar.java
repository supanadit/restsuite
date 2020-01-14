package com.supanadit.restsuite.component;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenu environment;
    private JMenu collection;

    // File Menu Item
    private JMenuItem exitMenuItem;
    // Environment Menu Item
    private JMenuItem environmentManageMenuItem;
    // Collection Menu Item
    private JMenuItem collectionManageMenuItem;

    public MenuBar() {
        // File
        file = new JMenu("File");
        exitMenuItem = new JMenuItem("Exit");
        // Environment
        environment = new JMenu("Environment");
        environmentManageMenuItem = new JMenuItem("Manage");
        // Collection
        collection = new JMenu("Collection");
        collectionManageMenuItem = new JMenuItem("Manage");

        // File
        file.add(exitMenuItem);
        // Environment
        environment.add(environmentManageMenuItem);
        // Collection
        collection.add(collectionManageMenuItem);

        add(file);
        add(environment);
        add(collection);
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

    public JMenu getCollectionMenu() {
        return collection;
    }

    public JMenuItem getCollectionManageMenuItem() {
        return collectionManageMenuItem;
    }
}
