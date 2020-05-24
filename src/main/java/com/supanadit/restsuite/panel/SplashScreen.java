package com.supanadit.restsuite.panel;

import com.supanadit.restsuite.helper.DefaultIcon;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    public static int defaultWidth = 500;
    public static int defaultHeight = 300;
    public Dimension dimension;

    private long startTime;
    private int minimumMilliseconds;

    public SplashScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dimension = new Dimension(defaultWidth, defaultHeight);

        setAlwaysOnTop(true);
        setIconImage(new DefaultIcon().getImage());
        setSize(dimension);
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    }

    public void show(int minimumMilliseconds) {
        this.minimumMilliseconds = minimumMilliseconds;
        setVisible(true);
        startTime = System.currentTimeMillis();
    }

    public void close() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        try {
            Thread.sleep(Math.max(minimumMilliseconds - elapsedTime, 0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
    }
}
