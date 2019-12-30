package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.supanadit.restsuite.listener.DragListener;
import com.supanadit.restsuite.panel.MainPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    public static Dimension dimension = new Dimension(1024, 600);
    public static boolean customTitleBar = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatLightLaf.install();
            JFrame frame = new JFrame("Rest Suite");

            frame.setUndecorated(customTitleBar);

            if (customTitleBar) {
                DragListener drag = new DragListener();
                frame.addMouseListener(drag);
                frame.addMouseMotionListener(drag);

                JButton minimize = new JButton("Minimize");
                JButton close = new JButton("Close");

                minimize.addActionListener((e) -> {
                    frame.setState(Frame.ICONIFIED);
                });

                close.addActionListener((e) -> {
                    frame.dispose();
                });
                JPanel panel = new JPanel(new MigLayout("insets 0 10 0 0"));

                panel.add(new JLabel("Rest Suite - Rest API Testing for Professional"), "push");
                // Button Minimize
                JPanel buttonMinimize = new JPanel(new MigLayout("fill"));
                JLabel minimizeLabel = new JLabel("Minimize");
                minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                buttonMinimize.add(minimizeLabel, "push,grow");
                Color minimizeColor = buttonMinimize.getBackground();
                buttonMinimize.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        frame.setState(Frame.ICONIFIED);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        frame.setState(Frame.ICONIFIED);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        minimizeLabel.setForeground(Color.white);
                        Color background = UIManager.getColor("Panel.background");
                        buttonMinimize.setBackground(background.darker());
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        minimizeLabel.setForeground(Color.black);
                        buttonMinimize.setBackground(minimizeColor);
                    }
                });
                panel.add(buttonMinimize, "pushy,growy,w 55,h 28");
                // End Button Minimize

                // Button Close
                JPanel buttonClose = new JPanel(new MigLayout("fill"));
                JLabel buttonLabel = new JLabel("Close");
                buttonLabel.setHorizontalAlignment(SwingConstants.CENTER);
                buttonClose.add(buttonLabel, "push,grow");
                Color buttonColor = buttonClose.getBackground();
                buttonClose.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        frame.dispose();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        frame.dispose();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        buttonLabel.setForeground(Color.white);
                        buttonClose.setBackground(Color.decode("#E81123"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        buttonLabel.setForeground(Color.black);
                        buttonClose.setBackground(buttonColor);
                    }
                });
                panel.add(buttonClose, "pushy,growy,w 55,h 28");
                // End Button Close
                Color lineColor = UIManager.getColor("Table.gridColor");
                panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, lineColor));

                frame.add(panel, BorderLayout.NORTH);
            }

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            frame.add(new MainPanel(), BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(dimension);
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
            frame.setVisible(true);
        });
    }
}