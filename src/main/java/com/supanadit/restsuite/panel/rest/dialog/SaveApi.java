package com.supanadit.restsuite.panel.rest.dialog;

import com.supanadit.restsuite.component.core.ActionDialog;
import com.supanadit.restsuite.panel.rest.dialog.model.FolderItem;
import com.supanadit.restsuite.panel.rest.dialog.renderer.FolderComboBoxRenderer;

import javax.swing.*;

public class SaveApi extends ActionDialog {
    JComboBox<FolderItem> folderList;

    public SaveApi() {
        super("Rename API");
        DefaultComboBoxModel<FolderItem> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement(FolderItem.RootFolder());

        folderList = new JComboBox<>(comboBoxModel);
        folderList.setRenderer(new FolderComboBoxRenderer());

        getPanel().add(new JLabel("Select Root Folder"), "wrap");
        getPanel().add(folderList, "growx,pushx");
    }

    public FolderItem getSelectedItem() {
        return (FolderItem) folderList.getSelectedItem();
    }
}
