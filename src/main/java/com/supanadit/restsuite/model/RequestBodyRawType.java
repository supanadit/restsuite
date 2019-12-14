package com.supanadit.restsuite.model;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class RequestBodyRawType {
    protected String name;
    protected String syntax;

    public RequestBodyRawType(String name, String syntax) {
        this.name = name;
        this.syntax = syntax;
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

    public static RequestBodyRawType TEXT() {
        return new RequestBodyRawType("TEXT", SyntaxConstants.SYNTAX_STYLE_NONE);
    }

    public static RequestBodyRawType JSON() {
        return new RequestBodyRawType("JSON", SyntaxConstants.SYNTAX_STYLE_JSON);
    }

    public static RequestBodyRawType HTML() {
        return new RequestBodyRawType("HTML", SyntaxConstants.SYNTAX_STYLE_HTML);
    }

    public static RequestBodyRawType XML() {
        return new RequestBodyRawType("XML", SyntaxConstants.SYNTAX_STYLE_XML);
    }

    public static RequestBodyRawType JAVASCRIPT() {
        return new RequestBodyRawType("JAVASCRIPT", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
    }
}
