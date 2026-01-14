package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddPasswordPanel extends JPanel {
    private JTextField servNameField;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField imageLinkField;
    private JLabel previewLabel;
    private JButton addButton;

    private JPanel categoryPanel;
    private JPanel sharePanel;
    private MultiSelectPanel categoryMultiSelect;
    private MultiSelectPanel shareMultiSelect;
    private JLabel selectedCategoriesLabel;
    private JLabel selectedUsersLabel;

    public AddPasswordPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(600, 450));
        setMinimumSize(new Dimension(400, 450));
        setBackground(new Color(220, 220, 220));

        // Labels et champs
        int labelX = 30;
        int fieldX = 170;
        int fieldWidth = 400;
        int fieldHeight = 35;
        int yStart = 30;
        int yGap = 60;

        // ServName
        addLabelAndField("ServName", labelX, fieldX, yStart, fieldWidth, fieldHeight, 0);
        servNameField = (JTextField) getComponent(getComponentCount() - 1);

        // Login
        addLabelAndField("Login", labelX, fieldX, yStart + yGap, fieldWidth, fieldHeight, 1);
        loginField = (JTextField) getComponent(getComponentCount() - 1);

        // Password
        JLabel passwordLabel = createLabel("Password");
        passwordLabel.setBounds(labelX, yStart + yGap * 2, 130, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(fieldX, yStart + yGap * 2, fieldWidth, fieldHeight);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(passwordField);

        // ImageLink
        addLabelAndField("ImageLink", labelX, fieldX, yStart + yGap * 3, fieldWidth, fieldHeight, 2);
        imageLinkField = (JTextField) getComponent(getComponentCount() - 1);

        // Preview section
        JLabel previewTitleLabel = createLabel("Preview");
        previewTitleLabel.setBounds(labelX, yStart + yGap * 4, 130, 30);
        add(previewTitleLabel);

        previewLabel = new JLabel();
        previewLabel.setBounds(40, yStart + yGap * 4 + 40, 90, 90);
        previewLabel.setBackground(Color.WHITE);
        previewLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true));
        previewLabel.setOpaque(true);
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setInstagramIcon();
        add(previewLabel);

        // Category section
        JLabel categoryLabel = createLabel("Category");
        categoryLabel.setBounds(170, yStart + yGap * 4, 130, 30);
        add(categoryLabel);

        // Panel pour la sélection de catégories
        categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        categoryPanel.setBounds(170, yStart + yGap * 4 + 40, 400, 40);
        categoryPanel.setOpaque(false);
        add(categoryPanel);

        // Bouton + pour les catégories
        JButton categoryPlusButton = createPlusButton();
        categoryPanel.add(categoryPlusButton);

        // Label pour afficher les catégories sélectionnées
        selectedCategoriesLabel = new JLabel("");
        selectedCategoriesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        selectedCategoriesLabel.setForeground(new Color(60, 60, 60));
        categoryPanel.add(selectedCategoriesLabel);

        // Créer le MultiSelectPanel pour les catégories (initialement caché)
        List<String> categories = new ArrayList<>();
        categories.add("Réseaux");
        categories.add("Travail");
        categories.add("Personnel");
        categories.add("Finance");
        categories.add("Gaming");

        categoryMultiSelect = new MultiSelectPanel(categories);
        categoryMultiSelect.setBounds(170, yStart + yGap * 4 + 85, 400, 100);
        categoryMultiSelect.setVisible(false);
        add(categoryMultiSelect);

        // Listener pour le bouton +
        categoryPlusButton.addActionListener(e -> {
            categoryMultiSelect.setVisible(!categoryMultiSelect.isVisible());
            shareMultiSelect.setVisible(false);
        });

        // Listener pour mettre à jour l'affichage des catégories sélectionnées
        categoryMultiSelect.addSelectionListener(e -> {
            updateSelectedLabel(selectedCategoriesLabel, categoryMultiSelect.getSelectedElements());
        });

        // Share with user section
        JLabel shareLabel = createLabel("Share with user");
        shareLabel.setBounds(170, yStart + yGap * 4 + 90, 150, 30);
        add(shareLabel);

        // Panel pour la sélection d'utilisateurs
        sharePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        sharePanel.setBounds(170, yStart + yGap * 4 + 125, 400, 40);
        sharePanel.setOpaque(false);
        add(sharePanel);

        // Bouton + pour les utilisateurs
        JButton sharePlusButton = createPlusButton();
        sharePanel.add(sharePlusButton);

        // Label pour afficher les utilisateurs sélectionnés
        selectedUsersLabel = new JLabel("");
        selectedUsersLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        selectedUsersLabel.setForeground(new Color(60, 60, 60));
        sharePanel.add(selectedUsersLabel);

        // Créer le MultiSelectPanel pour les utilisateurs (initialement caché)
        List<String> users = new ArrayList<>();
        users.add("Raph");
        users.add("Alice");
        users.add("Bob");
        users.add("Charlie");
        users.add("David");

        shareMultiSelect = new MultiSelectPanel(users);
        shareMultiSelect.setBounds(170, yStart + yGap * 4 + 170, 400, 100);
        shareMultiSelect.setVisible(false);
        add(shareMultiSelect);

        // Listener pour le bouton +
        sharePlusButton.addActionListener(e -> {
            shareMultiSelect.setVisible(!shareMultiSelect.isVisible());
            categoryMultiSelect.setVisible(false);
        });

        // Listener pour mettre à jour l'affichage des utilisateurs sélectionnés
        shareMultiSelect.addSelectionListener(e -> {
            updateSelectedLabel(selectedUsersLabel, shareMultiSelect.getSelectedElements());
        });

        // ADD button
        addButton = createRoundedButton("ADD");
        addButton.setBounds(40, yStart + yGap * 4 + 125, 100, 40);
        addButton.setBackground(new Color(90, 90, 90));
        add(addButton);

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

    private JButton createPlusButton() {
        JButton button = new JButton("+");
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 100, 100));
        button.setPreferredSize(new Dimension(35, 35));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
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

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                g2.setColor(getForeground());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(getText(), x, y);

                g2.dispose();
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(100, 100, 100));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void setInstagramIcon() {
        BufferedImage img = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, 80, 80, 15, 15);

        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(251, 173, 80),
                80, 80, new Color(193, 53, 132)
        );
        g2.setPaint(gradient);
        g2.fillRoundRect(5, 5, 70, 70, 15, 15);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawOval(25, 25, 30, 30);

        g2.setStroke(new BasicStroke(2));
        g2.drawOval(30, 30, 20, 20);

        g2.fillOval(58, 17, 8, 8);

        g2.dispose();
        previewLabel.setIcon(new ImageIcon(img));
    }

    // Getters
    public JTextField getServNameField() {
        return servNameField;
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getImageLinkField() {
        return imageLinkField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public Set<String> getSelectedCategories() {
        return categoryMultiSelect.getSelectedElements();
    }

    public Set<String> getSelectedUsers() {
        return shareMultiSelect.getSelectedElements();
    }

    public void updatePreview(String imageUrl) {
        try {
            java.net.URL url = new java.net.URL(imageUrl);
            BufferedImage img = javax.imageio.ImageIO.read(url);
            Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            previewLabel.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            setInstagramIcon();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Password Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 600);
        frame.setLocationRelativeTo(null);

        AddPasswordPanel panel = new AddPasswordPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}