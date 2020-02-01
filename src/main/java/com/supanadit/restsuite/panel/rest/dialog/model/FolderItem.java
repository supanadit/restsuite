package com.supanadit.restsuite.panel.rest.dialog.model;

public class FolderItem {
    Integer id;
    String name;

    public final static String ROOT_FOLDER = "Root";

    public FolderItem(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public FolderItem(String name) {
        this(null, name);
    }

    public static FolderItem RootFolder() {
        return new FolderItem(ROOT_FOLDER);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
