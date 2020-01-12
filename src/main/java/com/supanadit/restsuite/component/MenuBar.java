package com.supanadit.restsuite.component;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenu collection;

    private JMenuItem exitMenuItem;
    private JMenuItem collectionSaveMenuItem;

    public MenuBar() {
        file = new JMenu("File");
        collection = new JMenu("Collection");
        collectionSaveMenuItem = new JMenuItem("Save");

        exitMenuItem = new JMenuItem("Exit");
        file.add(exitMenuItem);

        collection.add(collectionSaveMenuItem);

        add(file);
        add(collection);
    }

    public JMenu getFileMenu() {
        return file;
    }

    public JMenuItem getExitMenu() {
        return exitMenuItem;
    }

    public JMenu getCollectionMenu() {
        return collection;
    }

    public JMenuItem getCollectionSaveMenu() {
        return collectionSaveMenuItem;
    }
}
