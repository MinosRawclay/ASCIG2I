package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Password;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class ScrollPanel extends JPanel {
    ScrollPanel(ArrayList<Password> passwords) {
        // Panel principal pour empiler les cartes verticalement
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // Ajout de plusieurs cartes
        passwords.forEach(p -> {
            mainPanel.add(new CardPanel(p));
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // espace entre cartes
        });
        // Ajout du scroll
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(200, 600));
        add(scrollPane);
    }

    static void main() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 800);
        frame.setLocationRelativeTo(null);
        ArrayList<Password> passwords = new ArrayList<>();

        passwords.add(new Password(
                "Netflix","raphel.0@gmail.com","1234","https://www.netflix.com"));
        passwords.add(new Password(
                "Google gmail","raphael.0@gmail.com","12345",""));
        passwords.add(new Password(
                "Spotify", "raphael.0@gmail.com", "5678", "https://www.spotify.com"));
        passwords.add(new Password(
                "Amazon", "r.0@email.com", "abcd", "https://www.amazon.fr"));

        ScrollPanel scrollPanel = new ScrollPanel(passwords);
        frame.add(scrollPanel);
        frame.setVisible(true);
    }
}
