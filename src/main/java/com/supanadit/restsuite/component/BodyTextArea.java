package com.supanadit.restsuite.component;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaUI;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.IconRowHeader;
import org.fife.ui.rtextarea.LineNumberList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        LineNumberList numberList = new LineNumberList(this, Color.white);
    }
}
