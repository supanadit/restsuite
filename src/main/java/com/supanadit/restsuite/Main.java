package com.supanadit.restsuite;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.supanadit.restsuite.component.MenuBar;
import com.supanadit.restsuite.component.core.Frame;
import com.supanadit.restsuite.entity.ProjectEntity;
import com.supanadit.restsuite.panel.MainPanel;
import com.supanadit.restsuite.system.Restsuite;
import com.supanadit.restsuite.system.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        Restsuite.createWorkspaceDirectory();

        // Test Hibernate
        ProjectEntity projectEntity = new ProjectEntity("IFGF");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the project objects
            session.save(projectEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<ProjectEntity> projectEntities = session.createQuery("from ProjectEntity", ProjectEntity.class).list();
            projectEntities.forEach(s -> System.out.println(s.getName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            FlatIntelliJLaf.install();

            Frame frame = new Frame("Rest Suite");
            frame.setName("Rest API Testing for Professional");

            MenuBar menuBar = new MenuBar();
            menuBar.getExitMenuItem().addActionListener(e -> {
                frame.dispose();
            });

            // frame.setJMenuBar(menuBar);

            frame.add(new MainPanel(menuBar), BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}