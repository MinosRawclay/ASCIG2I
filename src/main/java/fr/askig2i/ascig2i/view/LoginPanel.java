package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.SQLHandler;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
    private EntityManager em = emf.createEntityManager();
    private User user;

    private JTextField loginField;
    private JPasswordField passwordField;
    private Button loginButton;
    private Button newUserButton;

    public LoginPanel() {

        // ===== Fond dégradé du panel =====
        setLayout(new GridBagLayout());
        setOpaque(false);

        // ===== Panel central =====
        UiPanel centerPanel = new UiPanel(
                new Color(90, 90, 180),
                new Color(140, 140, 220)
        );
        centerPanel.setOpacity(200);
        centerPanel.setArc(100);
        centerPanel.setPreferredSize(new Dimension(320, 260));
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        // ===== Text Login =====
        JLabel textLogin = new JLabel("Login", SwingConstants.CENTER);
        textLogin.setFont(new Font("Arial", Font.BOLD, 22));
        textLogin.setForeground(UiTheme.TEXT_PRESSED);
        gbc.gridy = 0;
        centerPanel.add(textLogin, gbc);
        // ===== Champ Login =====
        JTextField loginField = new JTextField();
        loginField.setPreferredSize(new Dimension(200, 30));
        loginField.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridy = 1;
        centerPanel.add(loginField, gbc);

        // ===== Text Password =====
        JLabel textPassword = new JLabel("Password", SwingConstants.CENTER);
        textPassword.setFont(new Font("Arial", Font.BOLD, 22));
        textPassword.setForeground(UiTheme.TEXT_PRESSED);
        gbc.gridy = 2;
        centerPanel.add(textPassword, gbc);
        // ===== Champ Password =====
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 3;
        centerPanel.add(passwordField, gbc);

        // ==== Message ====
        JLabel messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 22));
        messageLabel.setForeground(UiTheme.TEXT_PRESSED);
        gbc.gridy = 6;
        centerPanel.add(messageLabel, gbc);

        // ===== Bouton Login =====
        Button loginButton = new Button("Login", e -> {
            String username = loginField.getText();
            String password = new String(passwordField.getPassword());

            System.out.println(username + " : " + password);

            // Vérification des informations de connexion
            if ((this.user = SQLHandler.checkUser(username, password, em))!=null) {
                System.out.println("TEST1");
                messageLabel.setText("Successfully logged!");
                messageLabel.setForeground(Color.GREEN);
            } else {
                System.out.println("TEST2");
                messageLabel.setText("Username or Password Incorrect.");
                messageLabel.setForeground(Color.RED);
            }
        });
        loginButton.setPreferredSize(new Dimension(180, 40));
        loginButton.setOpacity(220);
        gbc.gridy = 4;
        centerPanel.add(loginButton, gbc);

        // ===== Bouton New User =====
        Button newUserButton = new Button("New User", e -> {
            System.out.println("New User");
        });
        newUserButton.setPreferredSize(new Dimension(180, 40));
        newUserButton.setOpacity(200);
        gbc.gridy = 5;
        centerPanel.add(newUserButton, gbc);

        add(centerPanel,0);
    }

    // ===== Actions =====

    protected void onLogin() {
        String login = loginField.getText();
        char[] password = passwordField.getPassword();

        System.out.println("Login: " + login);
    }

    protected void onNewUser() {
        System.out.println("New User clicked");
    }

}
