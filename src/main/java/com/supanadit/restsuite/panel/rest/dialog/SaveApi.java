package com.supanadit.restsuite.panel.rest.dialog;

import com.supanadit.restsuite.component.core.ActionDialog;
import com.supanadit.restsuite.entity.CollectionStructureFolderEntity;
import com.supanadit.restsuite.panel.rest.dialog.renderer.FolderComboBoxRenderer;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import net.miginfocom.swing.MigLayout;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

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

    public void reloadFolder() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Folders
            List<CollectionStructureFolderEntity> folders = session.createQuery("from CollectionStructureFolderEntity", CollectionStructureFolderEntity.class).list();
            comboBoxModel.removeAllElements();
            comboBoxModel.addElement(CollectionStructureFolderEntity.RootFolder());
            folders.forEach(s -> {
                comboBoxModel.addElement(s);
            });
            folderList.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CollectionStructureFolderEntity getSelectedItem() {
        return (CollectionStructureFolderEntity) folderList.getSelectedItem();
    }

    public boolean isChecked() {
        return checkBox.isSelected();
    }
}
