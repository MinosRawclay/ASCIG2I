package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.SQLHandler;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
    private EntityManager em = emf.createEntityManager();
    private User user;


    public LoginFrame(){
        this.setTitle("Signing");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 200);
        this.setLayout(new GridLayout(4, 2, 10, 10));
        LoginPanel();

        this.setVisible(true);
    }




    public void LoginPanel(){
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JLabel messageLabel = new JLabel("");

        this.add(userLabel);
        this.add(userText);
        this.add(passwordLabel);
        this.add(passwordText);
        this.add(new JLabel(""));
        this.add(loginButton);
        this.add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                // Vérification des informations de connexion
                if (SQLHandler.checkUser(username, password, em)==null) {
                    messageLabel.setText("Successfully logged!");
                    messageLabel.setForeground(Color.GREEN);
                } else {
                    messageLabel.setText("Username or Password Incorrect.");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });

    }

}
