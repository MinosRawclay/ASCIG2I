package fr.askig2i.ascig2i.testes;

import javax.swing.*;
import java.awt.*;
import fr.askig2i.ascig2i.view.*;

public class TestPanel {
    static void main() {
        JFrame frame = new JFrame("Gradient Panel Test");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fond de la fenêtre (exemple)
        frame.setContentPane(new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(
                        0, 0, UiTheme.BACKGROUND_START,
                        0, getHeight(), UiTheme.BACKGROUND_END
                ));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        UiPanel container = new UiPanel();

        container.setOpacity(200);
        container.setArc(25);
        container.setPreferredSize(new Dimension(300, 200));
        container.setLayout(new GridBagLayout());

        container.add(new JLabel("Login Panel"));

        frame.add(container, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
