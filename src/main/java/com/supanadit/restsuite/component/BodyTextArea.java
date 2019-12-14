package com.supanadit.restsuite.component;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import java.awt.*;

public class BodyTextArea extends RSyntaxTextArea {
    public BodyTextArea() {
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
        this.setCodeFoldingEnabled(true);
        this.setCurrentLineHighlightColor(Color.white);
    }
}
