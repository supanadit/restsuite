package com.supanadit.restsuite.layout;

import javax.swing.*;
import java.awt.*;

public class BodyForm {
    public BodyForm() {
        SwingUtilities.invokeLater(() -> {
            JDialog dialog = new JDialog();

            dialog.setUndecorated(true);
            dialog.setSize(new Dimension(1024 / 2, 600 / 2));
            dialog.setVisible(true);
        });
    }
}
