package com.supanadit.restsuite.component;

import javax.swing.*;

public class BodyResponseTextArea extends JScrollPane {
    protected JTextArea bodyResponse;

    public BodyResponseTextArea() {
        this.bodyResponse = new JTextArea();
        this.setViewportView(this.bodyResponse);
    }

    public void setText(String text) {
        this.bodyResponse.setText(text);
    }
}
