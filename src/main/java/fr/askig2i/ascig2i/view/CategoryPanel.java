package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.SQLHandler;
import fr.askig2i.ascig2i.model.Category;
import fr.askig2i.ascig2i.model.Password;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryPanel extends JPanel {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
    private EntityManager em = emf.createEntityManager();
    private User user;
    private WindowManager manager;


    private final MultiSelectPanel categoryMultiSelect;
    private JLabel labelCategory;
    private JTextField fieldCategory;
    private JTextField fieldDesc;


    public CategoryPanel(WindowManager mg) {
        this.manager = mg;
        setOpaque(true);
        setBackground(Color.blue);
        setLayout(new BorderLayout());

        // HEADER
        add(new HeaderPanel(manager), BorderLayout.NORTH);

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

        // Champ desc
        fieldDesc = new JTextField();
        fieldDesc.setBounds(30, 80, 390, 40);
        fieldDesc.setFont(new Font("Arial", Font.PLAIN, 16));
        fieldDesc.setBackground(Color.WHITE);
        fieldDesc.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(fieldDesc);

        // Bouton ajouter
        Button addButton = new Button("Ajouter", e -> addCategory());
        addButton.setBounds(30, 140, 150, 40);
        formPanel.add(addButton);

        rightPanel.add(formPanel);

        // Glue pour pousser vers le haut
        rightPanel.add(Box.createVerticalGlue());
    }

    // RAPHAEL TODO => PB AFFICHAGE
    private void addCategory() {
        System.out.println("addCategory");
        Category c1 = new Category(
                fieldCategory.getText(), fieldDesc.getText());
        SQLHandler.saveNewCategory(c1, em);
        manager.goCategory();
    }

    private void deleteCategories() {
        System.out.println("Deleting categories");
        //RAPHAEL TODO prend la category select (le nom mais jpeux modif pour la cat)
        //SQLHandler.deleteCategory(?, em);
    }

    private void updateCategory(){
        List<String> categories = new ArrayList<>();
        for(Category c : (List<Category>) Objects.requireNonNull(SQLHandler.getCategories(em))){
            categories.add(c.getName());
        }
        categoryMultiSelect.setElement(categories);
    }


    static void main() {
        JFrame frame = new JFrame("Exemple Header");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 860);

        CategoryPanel panel = new CategoryPanel(new WindowManager());
        frame.add(panel);

        frame.setVisible(true);
    }
}
