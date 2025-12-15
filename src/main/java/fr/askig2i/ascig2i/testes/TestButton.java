package fr.askig2i.ascig2i.testes;

import fr.askig2i.ascig2i.view.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestButton {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Gradient Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        Button loginButton = new Button(
                "Login",
                e -> JOptionPane.showMessageDialog(null, "Login cliqué")
        );

        loginButton.setPreferredSize(new Dimension(140, 40));
        loginButton.setOpacity(200);

        frame.add(loginButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

