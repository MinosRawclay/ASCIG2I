package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.EncryptionManager;
import fr.askig2i.ascig2i.model.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class CardPanel extends UiPanel {

    private Password psw;
    private boolean selected;
    private CardSelectionListener listener;

    public CardPanel(Password password) {
        this.psw = password;
        selected = false;
        setPreferredSize(new Dimension(400, 90));
        setMinimumSize(new Dimension(400, 90));
        setMaximumSize(new Dimension(600, 90));
        setLayout(null); // Layout absolu pour contrôle précis
        setOpaque(false);

        // === Icon ===
        ImageIcon icon;
        try {
            icon = new ImageIcon(new URL("https://www.google.com/s2/favicons?sz=128&domain=" + psw.getUrl()));
        } catch (Exception e) {
            icon = createDefaultIcon();
        }

        JLabel logoLabel = new JLabel(icon);
        logoLabel.setBounds(15, 10, 64, 64);
        add(logoLabel);

        // === Panel pour le texte ===
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setBounds(95, 10, 250, 70);

        // Nom du service
        JLabel servName = new JLabel(psw.getServiceName());
        servName.setFont(new Font("Arial", Font.BOLD, 16));
        servName.setForeground(new Color(50, 50, 50));
        servName.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(servName);

        // Espacement
        textPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Login
        JLabel login = new JLabel("Login: " + truncateText(psw.getLogin(), 25));
        login.setFont(new Font("Arial", Font.PLAIN, 13));
        login.setForeground(new Color(80, 80, 80));
        login.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(login);

        // Espacement
        textPanel.add(Box.createRigidArea(new Dimension(0, 3)));

        // Password
        JLabel jpassword = new JLabel("Password: " + EncryptionManager.decrypt(password.getEncryptedPassword(),password.getLogin().hashCode()) );
        jpassword.setFont(new Font("Arial", Font.PLAIN, 13));
        jpassword.setForeground(new Color(80, 80, 80));
        jpassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.add(jpassword);

        add(textPanel);

        // === Bouton Copier ===
        ImageIcon iconCp = null;
        try {
            iconCp = new ImageIcon(getClass().getResource("/img/copy.png"));
            Image scaledImage = iconCp.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            iconCp = new ImageIcon(scaledImage);
        } catch (Exception _) {}

        JButton copyButton = new JButton(iconCp);
        copyButton.setOpaque(false);
        copyButton.setBorderPainted(false);
        copyButton.setContentAreaFilled(false);
        copyButton.setFocusPainted(false);
        copyButton.setPreferredSize(new Dimension(32, 32));
        copyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copyButton.setToolTipText("Copier les identifiants");
        copyButton.addActionListener(e -> copyToClipboard());
        copyButton.setBounds(355, 30, 32, 32);

        add(copyButton);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (listener != null) {
                    listener.onCardClicked(CardPanel.this);
                }
            }
        });
    }

    public void setSelectionListener(CardSelectionListener listener) {
        this.listener = listener;
    }


    private String truncateText(String text, int maxLength) {
        if (text.length() > maxLength) {
            return text.substring(0, maxLength - 3) + "...";
        }
        return text;
    }

    private ImageIcon createDefaultIcon() {
        // Créer une icône par défaut si l'image ne charge pas
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(64, 64, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fond gris
        g2.setColor(new Color(220, 220, 220));
        g2.fillRoundRect(0, 0, 64, 64, 10, 10);

        // Icône par défaut (cercle)
        g2.setColor(new Color(150, 150, 150));
        g2.fillOval(20, 20, 24, 24);

        g2.dispose();
        return new ImageIcon(img);
    }

    private void copyToClipboard() {
        String text = psw.getLogin() + " : " + psw.getEncryptedPassword();
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        System.out.println("selected: " + selected);
        updateStyle();
    }

    public boolean isSelected() {
        return selected;
    }

    public Password getPsw() {
        return psw;
    }

    public void addListener(ActionListener listener) {
        addListener(listener);
    }

    private void updateStyle() {
        if (selected) {
            setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        } else {
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exemple CardPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        // Créer le JPanel root
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(new Color(240, 240, 240));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Ajouter plusieurs cartes pour tester
        CardPanel card1 = new CardPanel(new Password(
                "Instagram",
                "user@example.com",
                "password123",
                "https://www.instagram.com"
        ));
        root.add(card1);
        root.add(Box.createRigidArea(new Dimension(0, 10)));

        CardPanel card2 = new CardPanel(new Password(
                "GitHub",
                "developer@github.com",
                "securepass456",
                "https://github.com"
        ));
        root.add(card2);
        root.add(Box.createRigidArea(new Dimension(0, 10)));

        CardPanel card3 = new CardPanel(new Password(
                "Netflix",
                "viewer@netflix.com",
                "netflix789",
                "https://www.netflix.com"
        ));
        root.add(card3);

        JScrollPane scrollPane = new JScrollPane(root);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}