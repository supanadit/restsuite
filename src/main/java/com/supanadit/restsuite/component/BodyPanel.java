package com.supanadit.restsuite.component;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class BodyPanel extends JPanel {
    protected BodyRequestTextArea bodyRequestTextArea;
    protected boolean withOptions;

    public BodyPanel(boolean withOptions) {
        super(new MigLayout());

        this.withOptions = withOptions;
        this.bodyRequestTextArea = new BodyRequestTextArea();

        if (this.withOptions) {
            this.add(RequestBodyTypeComboBox.getComponent());
            this.add(RequestBodyRawTypeComboBox.getComponent(), "wrap");
        }
        this.add(this.bodyRequestTextArea, "grow, push, span 3");
    }

    public void setText(String text) {
        this.bodyRequestTextArea.bodyRequest.setText(text);
    }
}
