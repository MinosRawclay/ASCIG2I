package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    public HeaderPanel() {
        // ===== pas de fond =====
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.white);

        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240)); // couleur du header

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // marges internes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridy = 0;

        // === Bouton Home ===
        ImageIcon iconCp = new ImageIcon(getClass().getResource("/img/home.png"));
        Image scaledImage = iconCp.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton homeButton = new JButton(new ImageIcon(scaledImage));
        homeButton.setOpaque(false);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusPainted(false);
        homeButton.setPreferredSize(new Dimension(20, 20));
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.addActionListener(e -> goHome());

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(homeButton, gbc);


    }

    private void goHome(){}

    static void main() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 800);
        frame.setLocationRelativeTo(null);

        // Créer le JPanel root
        JPanel root = new JPanel();
        root.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        root.setBackground(Color.GRAY);

        HeaderPanel panel = new HeaderPanel();
        root.add(panel, gbc);
        frame.add(root, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}
