package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Password;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HomePanel extends JPanel {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
    private EntityManager em = emf.createEntityManager();
    private WindowManager manager;

    private ArrayList<Password> passwords = new ArrayList<>();

    public HomePanel(WindowManager manager) {
        this.manager = manager;

        setOpaque(true);
        setBackground(Color.blue);
        setLayout(new BorderLayout());

        // HEADER
        add(new HeaderPanel(), BorderLayout.NORTH);

        // CONTENU CENTRAL
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(130, 110, 255));
        add(contentPanel, BorderLayout.CENTER);
        GridBagConstraints gbcC = new GridBagConstraints();
        gbcC.insets = new Insets(15, 15, 15, 15);
        gbcC.fill = GridBagConstraints.BOTH;

        // CONTENU GAUCHE - Augmentation de la largeur
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(130, 110, 255));
        gbcC.gridx = 0;
        gbcC.gridy = 0;
        gbcC.weightx = 0.65; // Augmenté de 0.6 à 0.65
        gbcC.weighty = 1.0;
        contentPanel.add(leftPanel, gbcC);

        updatePasswords();
        ScrollPanel scrollPanel = new ScrollPanel(passwords);
        scrollPanel.setPreferredSize(new Dimension(450, 500)); // Largeur minimale garantie
        leftPanel.add(scrollPanel, BorderLayout.CENTER);

        // CONTENU DROITE
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(130, 110, 255));
        gbcC.gridx = 1;
        gbcC.weightx = 0.35; // Ajusté de 0.4 à 0.35
        contentPanel.add(rightPanel, gbcC);
        GridBagConstraints gbcR = new GridBagConstraints();
        gbcR.insets = new Insets(15, 15, 15, 15);

        //Button buttonAddPassword = new Button("addPassword", e -> newPassword());
        AddPasswordPanel addPasswordPanel = new AddPasswordPanel();
        gbcR.gridx = 0;
        gbcR.gridy = 0;
        gbcR.weightx = 1.0;
        gbcR.weighty = 0;
        gbcR.anchor = GridBagConstraints.NORTH;
        //rightPanel.add(buttonAddPassword, gbcR);
        rightPanel.add(addPasswordPanel, gbcR);


    }

    private void updatePasswords() {
        passwords.add(new Password(
                "Netflix", "raphel.0@gmail.com", "1234", "https://www.netflix.com"));
        passwords.add(new Password(
                "Google gmail", "raphael.0@gmail.com", "12345", ""));
        passwords.add(new Password(
                "Spotify", "raphael.0@gmail.com", "5678", "https://www.spotify.com"));
        passwords.add(new Password(
                "Amazon", "r.0@email.com", "abcd", "https://www.amazon.fr"));
        passwords.add(new Password(
                "GitHub", "raphael.0@dev.com", "efgh90", "https://github.com"));
        passwords.add(new Password(
                "Twitter", "0_raphael@social.com", "pass123", "https://twitter.com"));
        passwords.add(new Password(
                "LinkedIn", "raphael.0@pro.com", "linked456", "https://www.linkedin.com"));
        passwords.add(new Password(
                "Facebook", "raphael.0@fb.com", "fbpass123", "https://www.facebook.com"));
    }

    //TODO
    private void newPassword() {
    }

    static void main() {
        JFrame frame = new JFrame("Exemple Header");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        HomePanel panel = new HomePanel(null);
        frame.add(panel);

        frame.setVisible(true);
    }
}