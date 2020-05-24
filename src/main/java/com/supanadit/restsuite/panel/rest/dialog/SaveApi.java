package com.supanadit.restsuite.panel.rest.dialog;

import com.supanadit.restsuite.component.core.ActionDialog;
import com.supanadit.restsuite.entity.CollectionStructureFolderEntity;
import com.supanadit.restsuite.panel.rest.dialog.renderer.FolderComboBoxRenderer;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SaveApi extends ActionDialog {
    JComboBox<CollectionStructureFolderEntity> folderList;
    DefaultComboBoxModel<CollectionStructureFolderEntity> comboBoxModel;
    JCheckBox checkBox;

    public SaveApi() {
        super("Rename API");
        comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement(CollectionStructureFolderEntity.RootFolder());

        folderList = new JComboBox<>(comboBoxModel);
        folderList.setRenderer(new FolderComboBoxRenderer());

        getPanel().add(new JLabel("Select Root Folder"), "wrap");
        getPanel().add(folderList, "growx,pushx");

        JPanel sideBottomPanel = new JPanel(new MigLayout("insets n 0 n 0"));
        checkBox = new JCheckBox("Save as new");
        sideBottomPanel.add(checkBox);
        getBottomPanel().add(sideBottomPanel, "push,grow");
    }
}
