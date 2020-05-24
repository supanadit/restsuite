package com.supanadit.restsuite.component.core;

import com.supanadit.restsuite.component.core.callback.ActionDialogCallback;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ActionDialog extends Dialog {
    protected ActionDialogCallback action;
    protected JPanel panel;
    protected JPanel bottomPanel;

    public ActionDialog(String title) {
        super(title, 400, 140);
        setResizable(false);

        panel = new JPanel(new MigLayout("insets 0 0 0 0"));

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        bottomPanel = new JPanel(new MigLayout("rtl, insets 0 0 0 0"));
        bottomPanel.add(cancelButton);
        bottomPanel.add(saveButton);

        add(panel, "grow,push,wrap");
        add(bottomPanel, "growx,pushx");

        cancelButton.addActionListener(e -> {
            if (action != null) {
                action.cancelAction();
            }
        });

        saveButton.addActionListener(e -> {
            if (action != null) {
                action.saveAction();
            }
        });
    }

    public void addAction(ActionDialogCallback action) {
        this.action = action;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }
}
