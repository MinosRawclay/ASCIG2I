package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;

public class UiPanel extends JPanel {

    // Opacité globale (0 = transparent, 255 = opaque)
    private int opacity = 255; // 80 %

    // Rayon d'arrondi (0 = carré parfait)
    private int arc = UiTheme.ARC_PANEL;

    public UiPanel() {
        setOpaque(false); // IMPORTANT pour la transparence
    }

    public UiPanel(Color start, Color end) {
        this();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Application de l'opacité (int → float)
        float alpha = opacity / 255f;
        g2.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha)
        );

        GradientPaint gradient = new GradientPaint(
                0, 0, UiTheme.PRIMARY_START,
                0, getHeight(), UiTheme.PRIMARY_END
        );

        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        g2.dispose();

        // Peinture normale des enfants
        super.paintComponent(g);
    }

    // ===== Configuration =====

    public void setOpacity(int opacity) {
        this.opacity = Math.max(0, Math.min(255, opacity));
        repaint();
    }

    public void setArc(int arc) {
        this.arc = Math.max(0, arc);
        repaint();
    }
}
