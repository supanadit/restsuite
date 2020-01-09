package com.supanadit.restsuite.helper;

import com.supanadit.restsuite.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class DefaultIcon extends ImageIcon {
    public DefaultIcon() {
        URL iconURL = Main.class.getClassLoader().getResource("icon/icon.png");
        assert iconURL != null;
        setImage(Toolkit.getDefaultToolkit().getImage(iconURL));
    }
}
