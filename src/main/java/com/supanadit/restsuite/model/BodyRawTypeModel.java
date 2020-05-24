package com.supanadit.restsuite.model;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class BodyRawTypeModel {
    protected String name;
    protected String syntax;
    protected String header;

    public BodyRawTypeModel(String name, String syntax, String header) {
        this.name = name;
        this.syntax = syntax;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getHeader() {
        return header;
    }

    public static BodyRawTypeModel TEXT() {
        return new BodyRawTypeModel("TEXT", SyntaxConstants.SYNTAX_STYLE_NONE, "text/plain;charset=utf-8");
    }

    public static BodyRawTypeModel JSON() {
        return new BodyRawTypeModel("JSON", SyntaxConstants.SYNTAX_STYLE_JSON, "application/json;charset=utf-8");
    }

    public static BodyRawTypeModel HTML() {
        return new BodyRawTypeModel("HTML", SyntaxConstants.SYNTAX_STYLE_HTML, "text/html;charset=utf-8");
    }

    public static BodyRawTypeModel XML() {
        return new BodyRawTypeModel("XML", SyntaxConstants.SYNTAX_STYLE_XML, "application/xml;charset=utf-8");
    }

    public static BodyRawTypeModel JAVASCRIPT() {
        return new BodyRawTypeModel("JAVASCRIPT", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT, "application/javascript;charset=utf-8");
    }
}
