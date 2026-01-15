package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Password;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HomePanel extends JPanel {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
    private EntityManager em = emf.createEntityManager();
    private WindowManager manager;
    private User user;

    private final AddPasswordPanel addPasswordPanel;
    private final ScrollPanel scrollPanel;
    private final MultiSelectPanel categoryMultiSelect;


    private ArrayList<Password> passwords = new ArrayList<>();

    public HomePanel(WindowManager manager, User user_) {
        this.manager = manager;
        user = user_;

        setOpaque(false);
        setLayout(new BorderLayout());

        // HEADER
        add(new HeaderPanel(), BorderLayout.NORTH);

        // CONTENU CENTRAL
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        add(contentPanel, BorderLayout.CENTER);
        GridBagConstraints gbcC = new GridBagConstraints();
        gbcC.insets = new Insets(15, 15, 15, 15);
        gbcC.fill = GridBagConstraints.BOTH;

        // CONTENU GAUCHE - Augmentation de la largeur
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        gbcC.gridx = 0;
        gbcC.gridy = 0;
        gbcC.weightx = 0.65; // Augmenté de 0.6 à 0.65
        gbcC.weighty = 1.0;
        contentPanel.add(leftPanel, gbcC);

        updatePasswords();
        scrollPanel = new ScrollPanel(passwords,this);
        scrollPanel.setPreferredSize(new Dimension(450, 500)); // Largeur minimale garantie
        //scrollPanel.addSelectionListener(e -> selectionerPsw());
        leftPanel.add(scrollPanel, BorderLayout.CENTER);

        // CONTENU DROITE
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);
        gbcC.gridx = 1;
        gbcC.weightx = 0.35; // Ajusté de 0.4 à 0.35
        contentPanel.add(rightPanel, gbcC);
        GridBagConstraints gbcR = new GridBagConstraints();
        gbcR.insets = new Insets(15, 15, 15, 15);

        UiPanel uiPanel = new UiPanel();
        uiPanel.setPreferredSize(new Dimension(450, 200));
        uiPanel.setMinimumSize(new Dimension(450, 200));
        categoryMultiSelect = new MultiSelectPanel(new ArrayList<>());
        categoryMultiSelect.setVisible(true);
        categoryMultiSelect.setOpaque(false);
        categoryMultiSelect.setBounds(0, 0, 450, 200);
        categoryMultiSelect.addSelectionListener(e -> updateFilterCategory());
        updateCategory();
        uiPanel.add(categoryMultiSelect);

        gbcR.gridx = 0;
        gbcR.gridy = 0;
        gbcR.weightx = 1.0;
        gbcR.weighty = 0;
        rightPanel.add(uiPanel, gbcR);

        addPasswordPanel = new AddPasswordPanel(user);
        gbcR.gridx = 0;
        gbcR.gridy = 1;
        gbcR.weightx = 1.0;
        gbcR.weighty = 0;
        rightPanel.add(addPasswordPanel, gbcR);


    }


    public void selectCard(Password password) {
        addPasswordPanel.setAll(password);
    }

    public void unSelectCard() {
        addPasswordPanel.unselectAll();
    }

    //TODO
    private void updateFilterCategory(){
        System.out.println("updateFilterCategory");
        System.out.println(categoryMultiSelect.getSelectedElements());
    }

    //TODO remplacer la liste par un appel BDD
    private void updateCategory(){
        List<String> categories = new ArrayList<>();
        categories.add("Réseaux");
        categories.add("Travail");
        categories.add("Personnel");
        categories.add("Finance");
        categories.add("Gaming");
        categories.add("Admin");
        categories.add("Ig2I");
        categoryMultiSelect.setElement(categories);
    }

    //TODO changer update password par un lien BDD
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

    static void main() {
        JFrame frame = new JFrame("Exemple Header");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //HomePanel panel = new HomePanel();
        //frame.add(panel);

        frame.setVisible(true);
    }
}