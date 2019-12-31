package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.InputTextUrlListener;
import io.reactivex.subjects.PublishSubject;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class InputSseURL extends JTextField {
    public PublishSubject<String> urlSubject = PublishSubject.create();

    public static InputSseURL getComponent() {
        final InputSseURL tf = new InputSseURL();
        tf.setPlaceholder("URL");
        tf.getDocument().addDocumentListener(new InputTextUrlListener(tf, tf.urlSubject));
        return tf;
    }

    private String placeholder;

    public InputSseURL() {
    }

    public InputSseURL(
            final Document pDoc,
            final String pText,
            final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public InputSseURL(final int pColumns) {
        super(pColumns);
    }

    public InputSseURL(final String pText) {
        super(pText);
    }

    public InputSseURL(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }
}
