package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {

    private int opacity = 255;

    public Button(String text, ActionListener action) {
        super(text);

        if (action != null) {
            addActionListener(action);
        }

        setForeground(UiTheme.TEXT_NORMAL);
        setFont(new Font("Arial", Font.BOLD, 14));

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER,  opacity / 255f));

        boolean pressed = getModel().isPressed();

        Color start = pressed
                ? UiTheme.PRIMARY_START_PRESSED
                : UiTheme.PRIMARY_START;

        Color end = pressed
                ? UiTheme.PRIMARY_END_PRESSED
                : UiTheme.PRIMARY_END;

        setForeground(pressed
                ? UiTheme.TEXT_PRESSED
                : UiTheme.TEXT_NORMAL);

        GradientPaint gradient = new GradientPaint(
                0, 0, start,
                0, getHeight(), end
        );

        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.dispose();

        super.paintComponent(g);
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
        repaint();
    }


}
