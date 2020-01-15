package com.supanadit.restsuite.component;

import com.supanadit.restsuite.helper.DefaultIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CoreDialog extends JDialog {
    public Dimension dimension;

    public CoreDialog(String title, int width, int height) {
        setTitle(title);
        setIconImage(new DefaultIcon().getImage());

        dimension = new Dimension(width, height);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(dimension);
        setLayout(new MigLayout());
        setAlwaysOnTop(true);
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    }


    public CoreDialog(String title) {
        this(title, CoreFrame.defaultWidth, CoreFrame.defaultHeight);
    }
}
