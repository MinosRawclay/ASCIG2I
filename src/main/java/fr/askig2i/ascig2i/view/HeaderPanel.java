package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    public HeaderPanel() {
        // ===== pas de fond =====
        setLayout(new GridBagLayout());
        setOpaque(false);
        //setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // marges internes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //GridBagConstraints gbc = new GridBagConstraints();


        // === Bouton Home ===
        ImageIcon iconCp = new ImageIcon(getClass().getResource("/img/home.png"));
        Image scaledImage = iconCp.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JButton homeButton = new JButton(new ImageIcon(scaledImage));
        homeButton.setOpaque(false);
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusPainted(false);
        homeButton.setPreferredSize(new Dimension(20, 20));
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.addActionListener(e -> goHome());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; // ne s'étire pas
        gbc.anchor = GridBagConstraints.WEST;

        add(homeButton, gbc);

        // === Titre ===
        JLabel titleLabel = new JLabel("Askig2i");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1; // prend tout l’espace disponible
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // === Button ===




        JPanel Buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        Buttons.setOpaque(false);
        Buttons.add(new Button("Category", e -> unlog()));
        Buttons.add(new Button("userShare", e -> unlog()));
        Buttons.add(new Button("UnLog",e->unlog()));

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;

        add(Buttons, gbc);



    }

    //TODO
    private void goHome(){}

    //TODO
    private void unlog(){}

    static void main() {
        JFrame frame = new JFrame("Exemple Header");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setLayout(new BorderLayout());
        frame.add(new HeaderPanel(), BorderLayout.NORTH);

        frame.setVisible(true);

    }

}
