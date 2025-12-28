package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;

public class CardPanel extends UiPanel {

    private Password psw;

    public CardPanel(Password password) {
        this.psw = password;
        setPreferredSize(new Dimension(250, 80));
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        //add(textLogin, gbc);


        // === Icon ===
        ImageIcon icon;

        try {
            icon = new ImageIcon(new URL(psw.getUrl()));
        }
        catch (Exception e) {
            icon = null;
        }
        JLabel logoLabel = new JLabel(icon);
        add(logoLabel,gbc);

        // === Text ===
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false); // transparent pour voir le fond du JPanel parent
        textPanel.add(new JLabel(psw.getServiceName()));
        textPanel.add(new JLabel(psw.getLogin()));
        textPanel.add(new JLabel(psw.getEncryptedPassword()));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(textPanel, gbc);

        // === Bouton Copier ===
        ImageIcon iconCp = new ImageIcon(getClass().getResource("/img/copy.png"));
        Image scaledImage = iconCp.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton copyButton = new JButton(new ImageIcon(scaledImage));
        copyButton.setOpaque(false);
        copyButton.setBorderPainted(false);
        copyButton.setContentAreaFilled(false);
        copyButton.setFocusPainted(false);
        copyButton.setPreferredSize(new Dimension(20, 20));
        copyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copyButton.addActionListener(e -> copyToClipboard());

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(copyButton, gbc);



    }

    private void copyToClipboard() {
        String text = psw.getLogin() + " : " + psw.getEncryptedPassword();
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        //JOptionPane.showMessageDialog(this, "Identifiants copiés !");
    }

    static void main() {
        JFrame frame = new JFrame("Exemple JPanel centré");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Créer le JPanel root
        JPanel root = new JPanel();
        root.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        root.setBackground(Color.GRAY);


        CardPanel card = new CardPanel(new Password("Insta","Test","1234","https://www.google.com/s2/favicons?sz=128&domain=factorio.com"));
        root.add(card);
        frame.add(root);

        frame.setVisible(true);
    }
}
