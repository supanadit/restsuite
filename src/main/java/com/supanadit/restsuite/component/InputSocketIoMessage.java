package com.supanadit.restsuite.component;

import com.supanadit.restsuite.listener.InputTextUrlListener;
import io.reactivex.subjects.PublishSubject;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class InputSocketIoMessage extends JTextField {
    public PublishSubject<String> urlSubject = PublishSubject.create();

    public static InputSocketIoMessage getComponent() {
        final InputSocketIoMessage tf = new InputSocketIoMessage();
        tf.setPlaceholder("Channel");
        tf.getDocument().addDocumentListener(new InputTextUrlListener(tf, tf.urlSubject));
        return tf;
    }

    private String placeholder;

    public InputSocketIoMessage() {
    }

    public InputSocketIoMessage(
            final Document pDoc,
            final String pText,
            final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public InputSocketIoMessage(final int pColumns) {
        super(pColumns);
    }

    public InputSocketIoMessage(final String pText) {
        super(pText);
    }

    public InputSocketIoMessage(final String pText, final int pColumns) {
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
