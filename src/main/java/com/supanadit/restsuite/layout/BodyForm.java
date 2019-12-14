package com.supanadit.restsuite.layout;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class BodyForm {
    public Dimension dimension = new Dimension(300, 200);

    public BodyForm() {
        SwingUtilities.invokeLater(() -> {
            JDialog dialog = new JDialog();

            int x = (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - dimension.width;
            int y = (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - dimension.height;

            dialog.setLayout(new MigLayout("", "[grow]", "[]"));

            dialog.add(new JTextField("Test"), "grow");

            dialog.setLocation(x, y);
            dialog.setSize(dimension);
            dialog.setVisible(true);
        });
    }
}
