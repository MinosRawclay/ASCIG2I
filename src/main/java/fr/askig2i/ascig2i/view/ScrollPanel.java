package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Password;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Set;

public class ScrollPanel extends JPanel {
    ScrollPanel(ArrayList<Password> passwords) {
        // Rendre le ScrollPanel transparent
        setOpaque(false);

        // Panel principal pour empiler les cartes verticalement
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // Ajout de plusieurs cartes
        passwords.forEach(p -> {
            mainPanel.add(new CardPanel(p));
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
        verticalBar.setUI(new RoundedScrollBarUI());
        verticalBar.setPreferredSize(new Dimension(10, 0));
        verticalBar.setOpaque(false);

        add(scrollPane);
    }

    private static class RoundedScrollBarUI extends BasicScrollBarUI {
        private static final int ARC = UiTheme.ARC_PANEL;
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(240, 240, 240));
            g2.fillRoundRect(
                    trackBounds.x,
                    trackBounds.y,
                    trackBounds.width,
                    trackBounds.height,
                    ARC,
                    ARC
            );
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Dégradé vertical
            GradientPaint gradient = new GradientPaint(
                    thumbBounds.x,
                    thumbBounds.y,
                    UiTheme.PRIMARY_START_PRESSED,
                    thumbBounds.x,
                    thumbBounds.y + thumbBounds.height,
                    UiTheme.PRIMARY_END_PRESSED
            );

            Shape thumb = new RoundRectangle2D.Float(
                    thumbBounds.x,
                    thumbBounds.y,
                    thumbBounds.width,
                    thumbBounds.height,
                    ARC,
                    ARC
            );

            g2.setPaint(gradient);
            g2.fill(thumb);
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }
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
