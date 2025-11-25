package fr.askig2i.ascig2i.testes;

import javax.swing.*;
import java.net.URL;

public class iconTest {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Website Icon Viewer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(200, 200);
                frame.setLayout(new java.awt.BorderLayout());

                try {
                    String domain = "factorio.com";

                    // Google Favicon service to get most part of domain icon file.
                    String faviconUrl = "https://www.google.com/s2/favicons?sz=128&domain=" + domain;

                    ImageIcon icon = new ImageIcon(new URL(faviconUrl));

                    JLabel label = new JLabel(icon);
                    label.setHorizontalAlignment(SwingConstants.CENTER);

                    frame.add(label);

                } catch (Exception e) {
                    e.printStackTrace();
                    frame.add(new JLabel("Failed to load icon!", SwingConstants.CENTER));
                }

                frame.setVisible(true);
            });
        }
    }


