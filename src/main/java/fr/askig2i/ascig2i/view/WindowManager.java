package fr.askig2i.ascig2i.view;

import com.mysql.cj.log.Log;
import fr.askig2i.ascig2i.model.User;

import javax.swing.*;
import java.awt.*;

public class WindowManager extends JFrame {
    private User user;
    LoginPanel loginPanel;
    HomePanel homePanel;

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


        // Mettre en param le manager dans toutes les windows
        LoginPanel loginPanel = new LoginPanel(this);
        HomePanel homePannel = new HomePanel(this);

        this.setLayout(new BorderLayout());
        this.add(loginPanel, BorderLayout.CENTER);

    }
    public void setConnected(User u){
        this.user = u;
    }

    public User getConnected(){
        return this.user;
    }

    public void login(){;
        this.loginPanel.setVisible(true);
        this.setVisible(true);
        while(this.user == null);
        this.loginPanel.setVisible(false);
        this.homePannel.setVisible(true);
        this.setVisible(true);
    }

    static void main() {
        WindowManager frame = new WindowManager();
        frame.login();

    }
}
