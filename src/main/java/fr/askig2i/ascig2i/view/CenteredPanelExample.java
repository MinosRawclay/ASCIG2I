package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;

public class CenteredPanelExample {
    public static void main(String[] args) {
        // Créer la fenêtre
        JFrame frame = new JFrame("Exemple JPanel centré");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Créer le JPanel root
        JPanel root = new JPanel();
        root.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        root.setBackground(Color.GRAY);

        // Créer le JPanel à centrer
        JPanel centeredPanel = new JPanel();
        centeredPanel.setBackground(Color.CYAN);
        centeredPanel.setPreferredSize(new Dimension(10, 10)); // Taille du panel centré

        // Ajouter le panel centré au centre de root
        root.add(centeredPanel);

        frame.add(root);

        // Afficher la fenêtre
        frame.setVisible(true);
    }
}
