package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.supanadit.restsuite.component.ApiPanel;
import com.supanadit.restsuite.listener.DragListener;
import com.supanadit.restsuite.panel.WebsocketPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class Main {
    public static Dimension dimension = new Dimension(1024, 600);
    public static boolean customTitleBar = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatDarculaLaf.install();
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

                JPanel panel = new JPanel(new MigLayout());
                panel.add(new JLabel("Rest Suite - Rest API Testing for Professional"), "push");
                panel.add(minimize);
                panel.add(close);
                Color lineColor = UIManager.getColor("Table.gridColor");
                panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, lineColor));
                frame.add(panel, BorderLayout.NORTH);
            }

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            JPanel sidePanel = new JPanel(new MigLayout("w 300,insets 11 10 10 10"));
            sidePanel.add(new JLabel("Environment"), "wrap");
            sidePanel.add(new JComboBox<>(), "pushx,growx");
            sidePanel.add(new JButton("Manage"), "wrap");
            sidePanel.add(new JLabel("Collection"), "wrap");

            JTextField searchCollection = new JTextField();
            sidePanel.add(searchCollection, "growx, pushx, wrap, span");
            sidePanel.add(new JSeparator(), "wrap,span, growx, pushx");
            DefaultMutableTreeNode style = new DefaultMutableTreeNode("Geo Smart");
            DefaultMutableTreeNode color = new DefaultMutableTreeNode("Point");
            DefaultMutableTreeNode font = new DefaultMutableTreeNode("Get Unique ID");
            style.add(color);
            style.add(font);
            DefaultMutableTreeNode red = new DefaultMutableTreeNode("Set");
            DefaultMutableTreeNode blue = new DefaultMutableTreeNode("SSE");
            DefaultMutableTreeNode black = new DefaultMutableTreeNode("Get All");
            color.add(red);
            color.add(blue);
            color.add(black);
            JTree collection = new JTree(style);
            Color background = UIManager.getColor("Panel.background");
            collection.setBackground(background);
            sidePanel.add(collection, "push, grow, span, wrap");

            JTabbedPane corePanel = new JTabbedPane();
            JPanel coreApiPanel = new JPanel();
            JPanel coreSocketIOPanel = new JPanel(new MigLayout("fill,insets 10 10 10 10"));
            JPanel coreSSEPanel = new JPanel(new MigLayout("fill"));

            JLabel comingSoon = new JLabel("Coming Soon");
            comingSoon.setHorizontalAlignment(SwingConstants.CENTER);
            comingSoon.setFont(new Font(comingSoon.getName(), Font.PLAIN, 15));

            JPanel websocketHeadPanel = new JPanel(new MigLayout("fill"));
            websocketHeadPanel.add(new JLabel("SocketIO URL"), "growx,pushx,wrap");
            websocketHeadPanel.add(new JTextField(), "growx,pushx");
            websocketHeadPanel.add(new JButton("Connect"));
            JPanel websocketLeftPanel = new JPanel(new MigLayout("w 200"));
            websocketLeftPanel.add(new JLabel("Send Message"), "growx,pushx,wrap");
            websocketLeftPanel.add(new JTextField(), "growx,pushx,wrap");
            websocketLeftPanel.add(new JComboBox<>(), "growx,pushx,wrap");
            websocketLeftPanel.add(new JTextArea(), "grow,push,wrap");
            websocketLeftPanel.add(new JButton("Emit"), "growx,pushx,wrap");
            JPanel websocketRightPanel = new JPanel(new MigLayout("w 200"));
            websocketRightPanel.add(new JLabel("Listener"), "pushx,growx,wrap");
            websocketRightPanel.add(new JTextField(), "pushx,growx,wrap");
            websocketRightPanel.add(new JButton("Add Listener"), "pushx,growx,wrap");
            websocketRightPanel.add(new JSeparator(), "pushx,growx,wrap");
            websocketRightPanel.add(new JLabel("Listener List"), "pushx,growx,wrap");
            coreSocketIOPanel.add(new JLabel("Message Listener"), "growx,pushx,wrap");
            coreSocketIOPanel.add(new JTextArea(), "grow,push");
            coreSocketIOPanel.add(websocketHeadPanel, "north");
            coreSocketIOPanel.add(websocketLeftPanel, "west");
            coreSocketIOPanel.add(websocketRightPanel, "east");


            coreSSEPanel.add(new JLabel("SSE URL"), "growx,pushx,wrap");
            coreSSEPanel.add(new JTextField(), "growx,pushx");
            coreSSEPanel.add(new JButton("Connect"), "wrap");
            coreSSEPanel.add(new JLabel("Message"), "pushx,growx,wrap");
            coreSSEPanel.add(new JScrollPane(new JTextArea()), "push,grow,span");

            coreApiPanel.setLayout(new BorderLayout());

            coreApiPanel.add(sidePanel, BorderLayout.LINE_END);
            coreApiPanel.add(new ApiPanel(), BorderLayout.CENTER);

            corePanel.add("Rest API", coreApiPanel);
            corePanel.add("Websocket", new WebsocketPanel());
            corePanel.add("Socket IO", coreSocketIOPanel);
            corePanel.add("SSE", coreSSEPanel);

            frame.add(corePanel, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(dimension);
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
            frame.setVisible(true);
        });
    }
}