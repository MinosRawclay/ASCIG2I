package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiSelectPanel extends UiPanel {
    private Set<String> selectedElements;
    private List<JToggleButton> buttons;
    private List<ActionListener> selectionListeners;
    private JPanel buttonsPanel;
    private JScrollPane scrollPane;

    public MultiSelectPanel(List<String> elements) {
        this.selectedElements = new HashSet<>();
        this.buttons = new ArrayList<>();
        this.selectionListeners = new ArrayList<>();

        setLayout(new BorderLayout());
        setOpaque(true);

        // Panel contenant les boutons avec FlowLayout
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonsPanel.setBackground(new Color(200, 200, 200));

        // Créer un bouton pour chaque élément
        for (String element : elements) {
            JToggleButton button = createRoundedToggleButton(element);
            buttons.add(button);
            buttonsPanel.add(button);

            // Ajouter un listener pour gérer la sélection
            button.addActionListener(e -> {
                if (button.isSelected()) {
                    selectedElements.add(element);
                } else {
                    selectedElements.remove(element);
                }
                notifySelectionListeners();
            });
        }

        // Ajouter le scrollPane
        scrollPane = new JScrollPane(buttonsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setBackground(new Color(200, 200, 200));

        add(scrollPane, BorderLayout.CENTER);

        // Calculer la hauteur préférée en fonction du nombre de lignes
        updatePreferredSize();
    }

    private void updatePreferredSize() {
        int buttonWidth = 120;
        int buttonHeight = 40;
        int gap = 10;
        int padding = 20; // padding du FlowLayout

        // Calculer combien de boutons par ligne (basé sur une largeur de 400px par défaut)
        int availableWidth = 400 - padding * 2;
        int buttonsPerRow = Math.max(1, availableWidth / (buttonWidth + gap));

        // Calculer le nombre de lignes nécessaires
        int totalButtons = buttons.size();
        int rows = (int) Math.ceil((double) totalButtons / buttonsPerRow);

        // Hauteur nécessaire (avec padding vertical)
        int neededHeight = rows * (buttonHeight + gap) + padding * 2;

        // Limiter la hauteur maximale à 150px (environ 3 lignes)
        int maxHeight = 150;
        int finalHeight = Math.min(neededHeight, maxHeight);

        setPreferredSize(new Dimension(400, finalHeight));
        setMinimumSize(new Dimension(400, finalHeight));
        setMaximumSize(new Dimension(400, maxHeight));

        // Forcer le recalcul du layout du buttonsPanel
        buttonsPanel.setPreferredSize(new Dimension(380, neededHeight));
    }

    private JToggleButton createRoundedToggleButton(String text) {
        JToggleButton button = new JToggleButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Dégradé selon l'état (sélectionné ou non)
                GradientPaint gradient;
                if (isSelected()) {
                    // Dégradé pour l'état sélectionné (bleu)
                    gradient = new GradientPaint(
                            0, 0, new Color(70, 130, 220),
                            0, getHeight(), new Color(50, 90, 180)
                    );
                } else {
                    // Dégradé pour l'état non sélectionné (gris foncé)
                    gradient = new GradientPaint(
                            0, 0, new Color(80, 80, 85),
                            0, getHeight(), new Color(60, 60, 65)
                    );
                }

                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Texte avec couleur selon l'état
                if (isSelected()) {
                    g2.setColor(Color.WHITE);
                } else {
                    g2.setColor(new Color(200, 200, 200));
                }
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(getText(), x, y);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Ne pas dessiner la bordure par défaut
            }
        };

        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(120, 40));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // Méthode pour ajouter un listener qui sera appelé quand la sélection change
    public void addSelectionListener(ActionListener listener) {
        selectionListeners.add(listener);
    }

    private void notifySelectionListeners() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "selection_changed");
        for (ActionListener listener : selectionListeners) {
            listener.actionPerformed(event);
        }
    }

    // Getters
    public Set<String> getSelectedElements() {
        return new HashSet<>(selectedElements);
    }

    public boolean isSelected(String element) {
        return selectedElements.contains(element);
    }

    // Méthode pour définir les éléments sélectionnés par programmation
    public void setSelectedElements(Set<String> elements) {
        selectedElements.clear();
        selectedElements.addAll(elements);

        // Mettre à jour l'état des boutons
        for (JToggleButton button : buttons) {
            button.setSelected(selectedElements.contains(button.getText()));
        }
        repaint();
    }

    // Méthode pour effacer toutes les sélections
    public void clearSelection() {
        selectedElements.clear();
        for (JToggleButton button : buttons) {
            button.setSelected(false);
        }
        repaint();
    }

    // Méthode main pour tester
    public static void main(String[] args) {
        JFrame frame = new JFrame("Multi Select Panel Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 200);
        frame.setLocationRelativeTo(null);

        // Créer une liste d'éléments
        List<String> elements = new ArrayList<>();
        elements.add("element1");
        elements.add("element2");
        elements.add("element3");
        elements.add("element4");
        elements.add("element5");

        // Créer le panel de sélection multiple
        MultiSelectPanel selectPanel = new MultiSelectPanel(elements);

        // Ajouter un label pour afficher la sélection
        JLabel selectionLabel = new JLabel("Sélection : Aucun");
        selectionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        selectionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Ajouter un listener pour mettre à jour le label
        selectPanel.addSelectionListener(e -> {
            Set<String> selected = selectPanel.getSelectedElements();
            if (selected.isEmpty()) {
                selectionLabel.setText("Sélection : Aucun");
            } else {
                selectionLabel.setText("Sélection : " + selected.toString());
            }
        });

        // Layout
        frame.setLayout(new BorderLayout());
        frame.add(selectPanel, BorderLayout.CENTER);
        frame.add(selectionLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}