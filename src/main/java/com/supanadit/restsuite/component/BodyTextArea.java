package com.supanadit.restsuite.component;

import javax.swing.*;

public class BodyTextArea extends JScrollPane {
    protected JTextArea bodyRequest;

    public BodyTextArea() {
        this.bodyRequest = new JTextArea();
        this.setViewportView(this.bodyRequest);
    }
}
