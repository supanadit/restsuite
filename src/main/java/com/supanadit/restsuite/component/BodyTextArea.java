package com.supanadit.restsuite.component;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import java.awt.*;

public class BodyTextArea extends RSyntaxTextArea {
    public BodyTextArea() {
        Color background = UIManager.getColor("Table.background");
        Color lineColor = UIManager.getColor("Table.gridColor");
        Color fontColor = UIManager.getColor("FormattedTextField.foreground");
        Color selectionColor = UIManager.getColor("FormattedTextField.selectionBackground");

        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        this.setCodeFoldingEnabled(true);
        this.setCurrentLineHighlightColor(background);
        this.setBackground(background);
        this.setTabLineColor(background);
        this.setBorder(BorderFactory.createLineBorder(lineColor));
        this.setForeground(fontColor);
        this.setSelectionColor(selectionColor);
        // This Code let selected text be white
        this.setUseSelectedTextColor(true);
        this.setSelectedTextColor(Color.white);
    }
}
