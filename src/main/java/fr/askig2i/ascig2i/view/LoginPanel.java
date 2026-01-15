package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.SQLHandler;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPanel extends JPanel {

    private WindowManager manager;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
    private EntityManager em = emf.createEntityManager();
    private User user;

    private JTextField loginField;
    private JPasswordField passwordField;
    private Button loginButton;
    private Button newUserButton;
    private JLabel messageLabel;

    private void addPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    public LoginPanel(WindowManager w) {
        this.manager = w;
        // ===== pas de fond =====
        setLayout(new GridBagLayout());
        setOpaque(false);

        // ===== Panel central =====
        UiPanel centerPanel = new UiPanel();
        centerPanel.setOpacity(200);
        centerPanel.setArc(100);
        centerPanel.setPreferredSize(new Dimension(320, 260));
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0,20,0,20);  //top padding
        //gbc.gridx = 0;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.insets = new Insets(10, 20, 10, 20);

        // ===== Text Login =====
        JLabel textLogin = new JLabel("Login", SwingConstants.CENTER);
        textLogin.setFont(new Font("Arial", Font.BOLD, 22));
        textLogin.setForeground(UiTheme.TEXT_PRESSED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;

        centerPanel.add(textLogin, gbc);
        // ===== Champ Login =====
        UiTexteField loginField = new UiTexteField(20);
        //loginField.setPreferredSize(new Dimension(200, 30));
        loginField.setColumns(30);
        loginField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        addPlaceholder(loginField, "Username");
        centerPanel.add(loginField, gbc);

        // ===== Text Password =====
        JLabel textPassword = new JLabel("Password", SwingConstants.CENTER);
        textPassword.setFont(new Font("Arial", Font.BOLD, 22));
        textPassword.setForeground(UiTheme.TEXT_PRESSED);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10,20,0,20);  //top padding
        centerPanel.add(textPassword, gbc);
        gbc.insets = new Insets(0,20,0,20);  //top padding

        // ===== Champ Password =====
        UiPasswordField passwordField = new UiPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        //passwordField.setColumns(30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        addPlaceholder(passwordField, "password");
        centerPanel.add(passwordField, gbc);

        // ==== Message ====
        messageLabel = new JLabel("-_-");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setForeground(UiTheme.TEXT_PRESSED);


        // ===== Bouton Login =====
        Button loginButton = new Button("Login", e -> login());
        loginButton.setPreferredSize(new Dimension(180, 40));
        loginButton.setOpacity(220);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20,20,0,0);  //top padding
        centerPanel.add(loginButton, gbc);

        // ===== Bouton New User =====
        Button newUserButton = new Button("New User", e -> {
            System.out.println("New User");
        });
        newUserButton.setPreferredSize(new Dimension(180, 40));
        newUserButton.setOpacity(200);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20,10,0,20);  //top padding
        centerPanel.add(newUserButton, gbc);

        // ==== Message 2 ====
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.PAGE_END; ;
        gbc.insets = new Insets(10,20,0,20);  //top padding

        centerPanel.add(messageLabel, gbc);

        add(centerPanel);
    }

    private void login(){
        String username = loginField.getText();
        String password = new String(passwordField.getPassword());

        System.out.println(username + " : " + password);
        // Vérification des informations de connexion
        if ((this.user = SQLHandler.checkUser(username, password, em))!=null) {
            System.out.println("TEST1");
            messageLabel.setText("Successfully logged!");
            messageLabel.setForeground(Color.GREEN);
            this.manager.setConnected(this.user);
            manager.goHome();
        } else {
            System.out.println("TEST2");
            messageLabel.setText("Username or Password Incorrect.");
            messageLabel.setForeground(Color.RED);
        }
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
