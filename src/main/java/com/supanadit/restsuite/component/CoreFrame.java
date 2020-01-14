package com.supanadit.restsuite.component;

import com.supanadit.restsuite.helper.DefaultIcon;

import javax.swing.*;

public class CoreFrame extends JFrame {
    public CoreFrame(String title, Boolean customTitleBar) {
        setTitle(title);
        setIconImage(new DefaultIcon().getImage());

        setUndecorated(customTitleBar);

        if (customTitleBar) {
            new CustomTitleBar(this);
        }
    }

    public CoreFrame(String title) {
        this(title, false);
    }
}
