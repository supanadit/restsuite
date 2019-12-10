package com.supanadit.restsuite.component;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class InputTextURL extends JTextField {

    public static InputTextURL getComponent() {
        final InputTextURL tf = new InputTextURL();
        tf.setPlaceholder("Type URL");
        return tf;
    }

    private String placeholder;

    public InputTextURL() {
    }

    public InputTextURL(
            final Document pDoc,
            final String pText,
            final int pColumns) {
        super(pDoc, pText, pColumns);
    }

    public InputTextURL(final int pColumns) {
        super(pColumns);
    }

    public InputTextURL(final String pText) {
        super(pText);
    }

    public InputTextURL(final String pText, final int pColumns) {
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
