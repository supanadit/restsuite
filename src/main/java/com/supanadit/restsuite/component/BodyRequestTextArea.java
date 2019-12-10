package com.supanadit.restsuite.component;

import javax.swing.*;

public class BodyRequestTextArea extends JScrollPane {
    protected JTextArea bodyRequest;

    public BodyRequestTextArea() {
        this.bodyRequest = new JTextArea();
        this.setViewportView(this.bodyRequest);
    }
}
