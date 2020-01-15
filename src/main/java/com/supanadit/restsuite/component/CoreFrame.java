package com.supanadit.restsuite.component;

import com.supanadit.restsuite.helper.DefaultIcon;

import javax.swing.*;
import java.awt.*;

public class CoreFrame extends JFrame {
    public static int defaultWidth = 1024;
    public static int defaultHeight = 600;
    public Dimension dimension;

    public CoreFrame(String title, Boolean customTitleBar, int width, int height) {
        setTitle(title);
        setIconImage(new DefaultIcon().getImage());

        dimension = new Dimension(width, height);

        setUndecorated(customTitleBar);

        if (customTitleBar) {
            new CustomTitleBar(this);
        }

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(dimension);
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    }

    public CoreFrame(String title, int width, int height) {
        this(title, false, width, height);
    }

    public CoreFrame(String title) {
        this(title, false, CoreFrame.defaultWidth, CoreFrame.defaultHeight);
    }
}
