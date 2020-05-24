package com.supanadit.restsuite.panel.rest.dialog;

import com.supanadit.restsuite.component.core.ActionDialog;

import javax.swing.*;

public class NewApiDialog extends ActionDialog {
    JTextField name;

    public NewApiDialog() {
        super("Create new collection");

        name = new JTextField();

        getPanel().add(new JLabel("Name"), "wrap");
        getPanel().add(name, "pushx,growx,wrap");
    }

    public String getName() {
        return name.getText();
    }

    public void setName(String name) {
        this.name.setText(name);
    }
}
