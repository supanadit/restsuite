package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatLightLaf;
import com.supanadit.restsuite.component.*;
import net.miginfocom.swing.MigLayout;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatLightLaf.install();
            JFrame frame = new JFrame("Rest Suite");

            frame.add(new RequestApiPanel(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(1024, 600));
            frame.setVisible(true);
        });
    }
}
