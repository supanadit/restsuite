package com.supanadit.restsuite.component;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import java.awt.*;

public class BodyTextArea extends RSyntaxTextArea {
    public BodyTextArea() {
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        this.setCodeFoldingEnabled(true);
        Color background = UIManager.getColor("Table.background");
        this.setCurrentLineHighlightColor(background);
        this.setBackground(background);
        this.setTabLineColor(background);
        Color lineColor = UIManager.getColor("Table.gridColor");
        this.setBorder(BorderFactory.createLineBorder(lineColor));
        Color fontColor = UIManager.getColor("FormattedTextField.foreground");
        this.setForeground(fontColor);
        Color selectionColor = UIManager.getColor("FormattedTextField.selectionBackground");
        this.setSelectionColor(selectionColor);
    }
}
