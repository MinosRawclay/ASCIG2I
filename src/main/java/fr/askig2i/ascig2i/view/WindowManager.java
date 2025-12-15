package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;

public class WindowManager extends JFrame {
    
    public WindowManager() {
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== Fond de la fenêtre (dégradé global) =====
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(
                        0, 0, UiTheme.BACKGROUND_START,
                        0, getHeight(), UiTheme.BACKGROUND_END
                );

                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        });
    }

    static void main() {
        JFrame frame = new WindowManager();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginPanel loginFrame = new LoginPanel();
        frame.add(loginFrame);
        loginFrame.setVisible(true);
        frame.setVisible(true);


        //JFrame lg = new LoginFrame();
        //lg.setVisible(true);
    }
}
