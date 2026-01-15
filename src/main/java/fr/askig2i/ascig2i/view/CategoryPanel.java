package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.User;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryPanel extends JPanel {
    private User user;


    private final MultiSelectPanel categoryMultiSelect;
    private JLabel labelCategory;
    private JTextField fieldCategory;


    public CategoryPanel() {
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

        // ========== CONTENU GAUCHE ==========
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(130, 110, 255));
        gbcC.gridx = 0;
        gbcC.gridy = 0;
        gbcC.weightx = 0.50;
        gbcC.weighty = 1.0;
        contentPanel.add(leftPanel, gbcC);

        // Titre pour la section gauche
        JLabel titleLeft = new JLabel("Catégories existantes");
        titleLeft.setFont(new Font("Arial", Font.BOLD, 20));
        titleLeft.setForeground(Color.WHITE);
        titleLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLeft.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        leftPanel.add(titleLeft);

        // Panel contenant le MultiSelectPanel
        UiPanel selectPanel = new UiPanel();
        selectPanel.setLayout(new BorderLayout());
        selectPanel.setPreferredSize(new Dimension(450, 350));
        selectPanel.setMinimumSize(new Dimension(450, 350));
        selectPanel.setMaximumSize(new Dimension(450, 350));
        selectPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        categoryMultiSelect = new MultiSelectPanel(new ArrayList<>());
        updateCategory();

        selectPanel.add(categoryMultiSelect, BorderLayout.CENTER);
        leftPanel.add(selectPanel);

        // Espacement
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Bouton supprimer
        Button deleteButton = new Button("Supprimer sélection", e -> deleteCategories());
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setPreferredSize(new Dimension(200, 40));
        deleteButton.setMaximumSize(new Dimension(200, 40));
        leftPanel.add(deleteButton);

        // Glue pour pousser vers le haut
        leftPanel.add(Box.createVerticalGlue());

        // ========== CONTENU DROITE ==========
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(130, 110, 255));
        gbcC.gridx = 1;
        gbcC.weightx = 0.50;
        contentPanel.add(rightPanel, gbcC);

        // Titre pour la section droite
        JLabel titleRight = new JLabel("Ajouter une catégorie");
        titleRight.setFont(new Font("Arial", Font.BOLD, 20));
        titleRight.setForeground(Color.WHITE);
        titleRight.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleRight.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        rightPanel.add(titleRight);

        // Panel pour le formulaire
        UiPanel formPanel = new UiPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(450, 200));
        formPanel.setMinimumSize(new Dimension(450, 200));
        formPanel.setMaximumSize(new Dimension(450, 200));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label
        labelCategory = new JLabel("Nom de la catégorie :");
        labelCategory.setFont(new Font("Arial", Font.BOLD, 16));
        labelCategory.setForeground(new Color(60, 60, 60));
        labelCategory.setBounds(30, 40, 200, 30);
        formPanel.add(labelCategory);

        // Champ texte
        fieldCategory = new JTextField();
        fieldCategory.setBounds(30, 80, 390, 40);
        fieldCategory.setFont(new Font("Arial", Font.PLAIN, 16));
        fieldCategory.setBackground(Color.WHITE);
        fieldCategory.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(fieldCategory);

        // Bouton ajouter
        Button addButton = new Button("Ajouter", e -> addCategory());
        addButton.setBounds(30, 140, 150, 40);
        formPanel.add(addButton);

        rightPanel.add(formPanel);

        // Glue pour pousser vers le haut
        rightPanel.add(Box.createVerticalGlue());
    }
    //TODO remplacer la fonction par un appel BDD
    private void addCategory() {
        System.out.println("addCategory");
        System.out.println(fieldCategory.getText());
    }

    //TODO remplacer la liste par un appel BDD
    private void deleteCategories() {
        System.out.println("Deleting categories");
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


    static void main() {
        JFrame frame = new JFrame("Exemple Header");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 860);

        CategoryPanel panel = new CategoryPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
