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
        this.selectionListeners = new ArrayList<>();

        setLayout(new BorderLayout());
        setOpaque(true);

        // Panel contenant les boutons avec FlowLayout
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonsPanel.setBackground(new Color(200, 200, 200));



        // Ajouter le scrollPane
        scrollPane = new JScrollPane(buttonsPanel);
        scrollPane.setOpaque(false); // Rendre le JScrollPane transparent
        scrollPane.getViewport().setOpaque(false); // Rendre le viewport transparent
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setUnitIncrement(20);
        verticalBar.setBlockIncrement(80);
        verticalBar.setUI(new UIRoundedScrollBar());
        verticalBar.setPreferredSize(new Dimension(10, 0));
        verticalBar.setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        setElement(elements);
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

        // IMPORTANT : La hauteur du buttonsPanel doit être la hauteur TOTALE
        int totalHeight = rows * (buttonHeight + gap) + padding * 2;

        // Le buttonsPanel doit avoir sa hauteur complète
        buttonsPanel.setPreferredSize(new Dimension(380, totalHeight));

        // Le MultiSelectPanel (avec scrollPane) a une hauteur limitée
        int maxHeight = 150;
        int finalHeight = Math.min(totalHeight, maxHeight);

        setPreferredSize(new Dimension(400, finalHeight));
        setMinimumSize(new Dimension(400, finalHeight));

        buttonsPanel.revalidate();
        scrollPane.revalidate();
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
                    // Dégradé pour l'état sélectionné
                    gradient = new GradientPaint(
                            0, 0, UiTheme.PRIMARY_START_PRESSED,
                            0, getHeight(), UiTheme.PRIMARY_END_PRESSED
                    );
                } else {
                    // Dégradé pour l'état non sélectionné
                    gradient = new GradientPaint(
                            0, 0, UiTheme.PRIMARY_START,
                            0, getHeight(), UiTheme.PRIMARY_END
                    );
                }

                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

                // Texte avec couleur selon l'état
                if (isSelected()) {
                    g2.setColor(UiTheme.TEXT_PRESSED);
                } else {
                    g2.setColor(UiTheme.TEXT_NORMAL);
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

    public void setElement(List<String> elements) {
        buttonsPanel.removeAll();
        selectedElements = new HashSet<>();
        buttons =  new ArrayList<>();

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
        // Recalculer la taille
        updatePreferredSize();

        // Forcer la mise à jour visuelle
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    public void setSelectedElements(List<String> selectedElements) {
        for (String element : selectedElements) {
            buttons.get(selectedElements.indexOf(element)).setSelected(true);
        }
    }

    public void unselectAll() {
        for (JToggleButton button : buttons){
            button.setSelected(false);
        }
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
        elements.add("element6");
        elements.add("element7");
        elements.add("element8");
        elements.add("element9");
        elements.add("element10");

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