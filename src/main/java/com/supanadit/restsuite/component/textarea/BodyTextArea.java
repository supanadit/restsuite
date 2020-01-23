package com.supanadit.restsuite.component.textarea;

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

        setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        setCodeFoldingEnabled(true);
        setCurrentLineHighlightColor(background);
        setBackground(background);
        setTabLineColor(background);
        setBorder(BorderFactory.createLineBorder(lineColor));
        setForeground(fontColor);
        setSelectionColor(selectionColor);
        // This Code let selected text be white
        setUseSelectedTextColor(true);
        setSelectedTextColor(Color.white);
    }
}
