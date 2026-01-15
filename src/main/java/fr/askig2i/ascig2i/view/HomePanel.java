package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Category;
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
        add(new HeaderPanel(manager), BorderLayout.NORTH);

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

        addPasswordPanel = new AddPasswordPanel(user, manager);
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

    //RAPHAEL TODO
    private void updateFilterCategory(){
        System.out.println("updateFilterCategory");
        System.out.println(categoryMultiSelect.getSelectedElements());
    }

    //Fonctionne
    private void updateCategory(){
        List<String> categories = new ArrayList<>();
        for( Category cat : user.getCategories() ){
            categories.add(cat.getName());
        }
        categoryMultiSelect.setElement(categories);
    }

    private void updatePasswords() {
        passwords.clear();
        passwords.addAll(user.getPasswordSet());
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