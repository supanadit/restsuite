package com.supanadit.restsuite.model;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class RequestBodyRawType {
    protected String name;
    protected String syntax;
    protected String header;

    public RequestBodyRawType(String name, String syntax, String header) {
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

    public static RequestBodyRawType TEXT() {
        return new RequestBodyRawType("TEXT", SyntaxConstants.SYNTAX_STYLE_NONE, "text/plain;charset=utf-8");
    }

    public static RequestBodyRawType JSON() {
        return new RequestBodyRawType("JSON", SyntaxConstants.SYNTAX_STYLE_JSON, "application/json;charset=utf-8");
    }

    public static RequestBodyRawType HTML() {
        return new RequestBodyRawType("HTML", SyntaxConstants.SYNTAX_STYLE_HTML, "text/html;charset=utf-8");
    }

    public static RequestBodyRawType XML() {
        return new RequestBodyRawType("XML", SyntaxConstants.SYNTAX_STYLE_XML, "application/xml;charset=utf-8");
    }

    public static RequestBodyRawType JAVASCRIPT() {
        return new RequestBodyRawType("JAVASCRIPT", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT, "application/javascript;charset=utf-8");
    }
}
