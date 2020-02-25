package com.supanadit.restsuite.helper;

import com.supanadit.restsuite.Main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
    public static Font getDefaultFont() {
        Font font = null;
        try {
            InputStream inputFile = Main.class.getClassLoader().getResourceAsStream("font/ui/NotoSans-Regular.ttf");
            assert inputFile != null;
            font = Font.createFont(Font.TRUETYPE_FONT, inputFile).deriveFont(13f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        if (font == null) {
            font = Font.getFont(Font.MONOSPACED);
        }
        return font;
    }

    public static Font getCodeFont() {
        Font font = null;
        try {
            InputStream inputFile = Main.class.getClassLoader().getResourceAsStream("font/code/JetBrainsMono-Regular.ttf");
            assert inputFile != null;
            font = Font.createFont(Font.TRUETYPE_FONT, inputFile).deriveFont(13f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        if (font == null) {
            font = Font.getFont(Font.MONOSPACED);
        }
        return font;
    }
}
