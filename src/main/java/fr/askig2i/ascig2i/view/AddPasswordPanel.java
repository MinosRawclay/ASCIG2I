package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Category;
import fr.askig2i.ascig2i.model.EncryptionManager;
import fr.askig2i.ascig2i.model.Password;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddPasswordPanel extends  UiPanel {
    private final User user;

    private final JTextField servNameField;
    private final JTextField loginField;
    private final JTextField passwordField;
    private final JTextField imageLinkField;
    private final JButton addButton;
    private final JButton updateButton;
    private ImageIcon icon;
    private final JLabel logoLabel;

    private final JPanel categoryPanel;
    private final JPanel sharePanel;
    private MultiSelectPanel categoryMultiSelect;
    private MultiSelectPanel shareMultiSelect;
    private final JLabel selectedCategoriesLabel;
    private final JLabel selectedUsersLabel;
    private EntityTransaction et;
    private EntityManager em;

    public AddPasswordPanel(User user_) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
        em = emf.createEntityManager();
        et = em.getTransaction();

        user = user_;
        setLayout(null);
        setPreferredSize(new Dimension(450, 420));
        setMinimumSize(new Dimension(450, 420));

        // Labels et champs
        int labelX = 30;
        int fieldX = 120;
        int fieldWidth = 280;
        int fieldHeight = 25;
        int yStart = 30;
        int yGap = 40;

        // ServName
        addLabelAndField("ServName", labelX, fieldX, yStart, fieldWidth, fieldHeight, 0);
        servNameField = (JTextField) getComponent(getComponentCount() - 1);

        // Login
        addLabelAndField("Login", labelX, fieldX, yStart + yGap, fieldWidth, fieldHeight, 1);
        loginField = (JTextField) getComponent(getComponentCount() - 1);

        // Password
        addLabelAndField("Password", labelX, fieldX, yStart + 2*yGap, fieldWidth, fieldHeight, 2);
        passwordField = (JTextField) getComponent(getComponentCount() - 1);

        // ImageLink
        addLabelAndField("ImageLink", labelX, fieldX, yStart + yGap * 3, fieldWidth, fieldHeight, 2);
        imageLinkField = (JTextField) getComponent(getComponentCount() - 1);
        imageLinkField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // Cette fonction s'exécute quand on quitte le champ
                updateIcon();
            }
        });

        // Preview section
        JLabel previewTitleLabel = createLabel("Preview");
        previewTitleLabel.setBounds(labelX, yStart + yGap * 4, 130, 30);
        add(previewTitleLabel);

        JLabel previewLabel = new JLabel();
        previewLabel.setBounds(30, yStart + yGap * 4 + 30, 70, 70);
        previewLabel.setBackground(Color.WHITE);
        previewLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true));
        previewLabel.setOpaque(true);
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        logoLabel = new JLabel(icon);
        logoLabel.setBounds(30, yStart + yGap * 4 + 30, 70, 70);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateIcon();
        add(logoLabel);
        add(previewLabel);

        // === Category section ===========================================
        JLabel categoryLabel = createLabel("Category");
        categoryLabel.setBounds(170, yStart + yGap * 4, 130, 30);
        add(categoryLabel);

        // Panel pour la sélection de catégories
        categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        categoryPanel.setBounds(170, yStart + yGap * 4 + 40, 400, 40);
        categoryPanel.setOpaque(false);
        add(categoryPanel);

        // Bouton + pour les catégories
        Button categoryPlusButton = new Button("+",e -> {
            boolean newVisibility = !categoryMultiSelect.isVisible();
            categoryMultiSelect.setVisible(newVisibility);
            if (newVisibility) {
                setComponentZOrder(categoryMultiSelect, 0); // Met au premier plan
            }
            shareMultiSelect.setVisible(false);
        });
        categoryPanel.add(categoryPlusButton);

        // Label pour afficher les catégories sélectionnées
        selectedCategoriesLabel = new JLabel("");
        //selectedCategoriesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        selectedCategoriesLabel.setForeground(new Color(60, 60, 60));
        categoryPanel.add(selectedCategoriesLabel);

        // Créer le MultiSelectPanel pour les catégories (initialement caché)
        categoryMultiSelect = new MultiSelectPanel(new ArrayList<>());
        categoryMultiSelect.setBounds(20, yStart + yGap * 4 - 70, 380, 100);
        categoryMultiSelect.setVisible(false);
        add(categoryMultiSelect);

        // Listener pour mettre à jour l'affichage des catégories sélectionnées
        categoryMultiSelect.addSelectionListener(e -> {
            updateSelectedLabel(selectedCategoriesLabel, categoryMultiSelect.getSelectedElements());
        });

        // === Share with user section ===========================================
        JLabel shareLabel = createLabel("Share with user");
        shareLabel.setBounds(170, yStart + yGap * 4 + 90, 150, 30);
        add(shareLabel);

        // Panel pour la sélection d'utilisateurs
        sharePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        sharePanel.setBounds(170, yStart + yGap * 4 + 125, 400, 40);
        sharePanel.setOpaque(false);
        add(sharePanel);

        // Bouton + pour les utilisateurs
        Button sharePlusButton = new Button("+",e -> {
            boolean newVisibility = !shareMultiSelect.isVisible();
            shareMultiSelect.setVisible(newVisibility);
            if (newVisibility) {
                setComponentZOrder(shareMultiSelect, 0); // Met au premier plan
            }
            categoryMultiSelect.setVisible(false);
        });
        sharePanel.add(sharePlusButton);

        // Label pour afficher les utilisateurs sélectionnés
        selectedUsersLabel = new JLabel("");
        //selectedUsersLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        selectedUsersLabel.setForeground(new Color(60, 60, 60));
        sharePanel.add(selectedUsersLabel);

        shareMultiSelect = new MultiSelectPanel(new ArrayList<>());
        shareMultiSelect.setBounds(20, yStart + yGap * 4 + 20, 380, 100);
        shareMultiSelect.setOpaque(true);
        shareMultiSelect.setVisible(false);
        add(shareMultiSelect);

        // Listener pour mettre à jour l'affichage des utilisateurs sélectionnés
        shareMultiSelect.addSelectionListener(e -> {
            updateSelectedLabel(selectedUsersLabel, shareMultiSelect.getSelectedElements());
        });

        updateAll();

        // ADD button
        addButton = new Button("ADD", e->addPassword());
        addButton.setBounds(40, yStart + yGap * 4 + 115, 100, 40);
        addButton.setBackground(new Color(90, 90, 90));
        add(addButton);

        // Update button
        updateButton = new Button("Update", e->updatePassword());
        updateButton.setBounds(40, yStart + yGap * 4 + 115, 100, 40);
        updateButton.setBackground(new Color(90, 90, 90));
        updateButton.setVisible(false);
        add(updateButton);

        // Listener global pour fermer les MultiSelectPanel en cliquant ailleurs
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                closeMultiSelectPanels(e);
            }
        });
    }

    private void closeMultiSelectPanels(MouseEvent e) {
        // Vérifier si le clic est en dehors des MultiSelectPanel
        Point point = e.getPoint();

        if (categoryMultiSelect.isVisible() &&
                !categoryMultiSelect.getBounds().contains(point) &&
                !categoryPanel.getBounds().contains(point)) {
            categoryMultiSelect.setVisible(false);
        }

        if (shareMultiSelect.isVisible() &&
                !shareMultiSelect.getBounds().contains(point) &&
                !sharePanel.getBounds().contains(point)) {
            shareMultiSelect.setVisible(false);
        }
    }

    private void updateSelectedLabel(JLabel label, Set<String> selected) {
        if (selected.isEmpty()) {
            label.setText("");
        } else {
            String text = String.join(", ", selected);
            if (text.length() > 40) {
                text = text.substring(0, 37) + "...";
            }
            label.setText(text);
        }
    }

    private void addLabelAndField(String labelText, int labelX, int fieldX, int y, int fieldWidth, int fieldHeight, int index) {
        JLabel label = createLabel(labelText);
        label.setBounds(labelX, y, 130, 30);
        add(label);

        JTextField field = new JTextField();
        field.setBounds(fieldX, y, fieldWidth, fieldHeight);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(field);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(60, 60, 60));
        return label;
    }

    private void updateIcon() {
        icon = null;
        if (!imageLinkField.getText().isEmpty()) {
            try {
                icon = new ImageIcon(new URL("https://www.google.com/s2/favicons?sz=128&domain="+imageLinkField.getText()));
            }
            catch (Exception _){}
        }
        logoLabel.setIcon(icon);
    }

    private void updateAll(){
        updateCategory();
        updateShared();
        updateIcon();
    }

    //TODO remplacer la liste par un appel BDD
    private void updateShared(){
        List<String> users = new ArrayList<>();
        users.add("Raph");
        users.add("Alice");
        users.add("Bob");
        users.add("Charlie");
        users.add("David");
        shareMultiSelect.setElement(users);
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

    //TODO remplacer la fonction par un appel BDD
    private void addPassword(){
        System.out.println("Adding password:");
        System.out.println(servNameField.getText());
        System.out.println(loginField.getText());
        System.out.println(passwordField.getText());
        System.out.println(imageLinkField.getText());
        System.out.println(selectedCategoriesLabel.getText());
        System.out.println(selectedUsersLabel.getText());

        et.begin();
        Password password1 = new Password(
                servNameField.getText(),
                loginField.getText(),
                passwordField.getText(),
                imageLinkField.getText());



    }

    //TODO
    private void updatePassword(){
        System.out.println("Updating password");
        System.out.println(servNameField.getText());
        System.out.println(loginField.getText());
        System.out.println(passwordField.getText());
        System.out.println(imageLinkField.getText());
        System.out.println(selectedCategoriesLabel.getText());
        System.out.println(selectedUsersLabel.getText());


    }

    public void setAll(Password password){
        servNameField.setText(password.getServiceName());
        loginField.setText(password.getLogin());
        passwordField.setText(EncryptionManager.decrypt(password.getEncryptedPassword(),password.getLogin().hashCode()));
        imageLinkField.setText(password.getUrl());
        updateIcon();
        setCategories(password);
        setShare(password);
        addButton.setVisible(false);
        updateButton.setVisible(true);
    }

    public void unselectAll(){
        servNameField.setText("");
        loginField.setText("");
        passwordField.setText("");
        imageLinkField.setText("");
        updateIcon();
        setCategories(null);
        setShare(null);

        addButton.setVisible(true);
        updateButton.setVisible(false);
    }

    public void setCategories(Password password){
        if (password == null) {
            categoryMultiSelect.unselectAll();
            return;
        }
        List<Category> categories = new ArrayList<>();
        List<String> categoriesS = new ArrayList<>();
        //TODO récuperer les catégories d'un password avec la BDD
        //TODO puis convertir en le type categoriesS

        categoryMultiSelect.setSelectedElements(categoriesS);
    }

    public void setShare(Password password){
        if (password == null) {
            categoryMultiSelect.unselectAll();
            return;
        }
        List<User> users = new ArrayList<>();
        List<String> usersS = new ArrayList<>();
        //TODO récuperer les user d'un password avec la BDD
        //TODO puis convertir en le type categoriesS

        shareMultiSelect.setSelectedElements(usersS);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Password Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 420);
        frame.setLocationRelativeTo(null);

        AddPasswordPanel panel = new AddPasswordPanel(null);
        frame.add(panel);

        frame.setVisible(true);
    }
}