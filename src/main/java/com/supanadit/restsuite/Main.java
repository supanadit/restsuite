package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.supanadit.restsuite.component.MenuBar;
import com.supanadit.restsuite.component.core.Frame;
import com.supanadit.restsuite.helper.FontLoader;
import com.supanadit.restsuite.panel.MainPanel;
import com.supanadit.restsuite.system.Restsuite;
import org.apache.batik.transcoder.TranscoderException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "lcd");
        System.setProperty("swing.aatext", "true");
        System.setProperty("sun.java2d.renderer", "sun.java2d.marlin.MarlinRenderingEngine");

        Restsuite.createWorkspaceDirectory();

        UIManager.put("Button.font", FontLoader.getDefaultFont());
        UIManager.put("ToggleButton.font", FontLoader.getDefaultFont());
        UIManager.put("RadioButton.font", FontLoader.getDefaultFont());
        UIManager.put("CheckBox.font", FontLoader.getDefaultFont());
        UIManager.put("ColorChooser.font", FontLoader.getDefaultFont());
        UIManager.put("ComboBox.font", FontLoader.getDefaultFont());
        UIManager.put("Label.font", FontLoader.getDefaultFont());
        UIManager.put("List.font", FontLoader.getDefaultFont());
        UIManager.put("MenuBar.font", FontLoader.getDefaultFont());
        UIManager.put("MenuItem.font", FontLoader.getDefaultFont());
        UIManager.put("RadioButtonMenuItem.font", FontLoader.getDefaultFont());
        UIManager.put("CheckBoxMenuItem.font", FontLoader.getDefaultFont());
        UIManager.put("Menu.font", FontLoader.getDefaultFont());
        UIManager.put("PopupMenu.font", FontLoader.getDefaultFont());
        UIManager.put("OptionPane.font", FontLoader.getDefaultFont());
        UIManager.put("Panel.font", FontLoader.getDefaultFont());
        UIManager.put("ProgressBar.font", FontLoader.getDefaultFont());
        UIManager.put("ScrollPane.font", FontLoader.getDefaultFont());
        UIManager.put("Viewport.font", FontLoader.getDefaultFont());
        UIManager.put("TabbedPane.font", FontLoader.getDefaultFont());
        UIManager.put("Table.font", FontLoader.getDefaultFont());
        UIManager.put("TableHeader.font", FontLoader.getDefaultFont());
        UIManager.put("TextField.font", FontLoader.getDefaultFont());
        UIManager.put("PasswordField.font", FontLoader.getDefaultFont());
        UIManager.put("TextArea.font", FontLoader.getDefaultFont());
        UIManager.put("TextPane.font", FontLoader.getDefaultFont());
        UIManager.put("EditorPane.font", FontLoader.getDefaultFont());
        UIManager.put("TitledBorder.font", FontLoader.getDefaultFont());
        UIManager.put("ToolBar.font", FontLoader.getDefaultFont());
        UIManager.put("ToolTip.font", FontLoader.getDefaultFont());
        UIManager.put("Tree.font", FontLoader.getDefaultFont());

        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.install();

            Frame frame = new Frame("Rest Suite");
            frame.setName("Rest API Testing for Professional");

            MenuBar menuBar = new MenuBar();
            menuBar.getExitMenuItem().addActionListener(e -> {
                frame.dispose();
            });

            // frame.setJMenuBar(menuBar);

            try {
                frame.add(new MainPanel(), BorderLayout.CENTER);
            } catch (IOException | TranscoderException e) {
                e.printStackTrace();
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}