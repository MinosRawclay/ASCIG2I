package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Password;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScrollPanel extends JPanel {

    private CardPanel selectedCard;

    ScrollPanel(ArrayList<Password> passwords) {
        // Rendre le ScrollPanel transparent
        setOpaque(false);

        // Panel principal pour empiler les cartes verticalement
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // Ajout de plusieurs cartes
        passwords.forEach(p -> {
            CardPanel cardPanel = new CardPanel(p);
            cardPanel.setSelectionListener(card -> {
                // Si on reclique sur la card déjà sélectionnée → toggle OFF
                if (selectedCard == card) {
                    card.setSelected(false);
                    selectedCard = null;
                    return;
                }
                // Sinon, on change la sélection
                if (selectedCard != null) {
                    selectedCard.setSelected(false);
                }
                card.setSelected(true);
                selectedCard = card;
            });
            mainPanel.add(cardPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // espace entre cartes

        });
        // Ajout du scroll
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setOpaque(false); // Rendre le JScrollPane transparent
        scrollPane.getViewport().setOpaque(false); // Rendre le viewport transparent
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 600));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setUnitIncrement(20);
        verticalBar.setBlockIncrement(80);
        verticalBar.setUI(new UIRoundedScrollBar());
        verticalBar.setPreferredSize(new Dimension(10, 0));
        verticalBar.setOpaque(false);

        add(scrollPane);
    }

    public CardPanel getSelectedCard() {
        return selectedCard;
    }

    private void setSelectedCard(CardPanel newCard) {
        CardPanel oldCard = this.selectedCard;
        this.selectedCard = newCard;
        firePropertyChange("selectedCard", oldCard, newCard);
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
        passwords.add(new Password(
                "GitHub", "raphael.0@dev.com", "efgh90", "https://github.com"));
        passwords.add(new Password(
                "Twitter", "0_raphael@social.com", "pass123", "https://twitter.com"));
        passwords.add(new Password(
                "LinkedIn", "raphael.0@pro.com", "linked456", "https://www.linkedin.com"));
        passwords.add(new Password(
                "Facebook", "raphael.0@fb.com", "fbpass123", "https://www.facebook.com"));



        ScrollPanel scrollPanel = new ScrollPanel(passwords);
        frame.add(scrollPanel);
        frame.setVisible(true);
    }
}
