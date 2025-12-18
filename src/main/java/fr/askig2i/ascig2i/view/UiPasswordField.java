package fr.askig2i.ascig2i.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UiPasswordField extends JPasswordField {

    private Color backgroundColor = UiTheme.TF_BG;
    private Color borderColor = UiTheme.TF_BORDER;
    private Color placeholderColor = UiTheme.TF_PLACEHOLDER;
    private int arc = 20;

    private String placeholder = "";
    private boolean showingPlaceholder = true;

    public UiPasswordField(int columns) {
        super(columns);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setFont(new Font("Arial", Font.PLAIN, 14));
        setEchoChar((char) 0); // visible tant que placeholder

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setForeground(Color.BLACK);
                    setEchoChar('•');
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getPassword().length == 0) {
                    showPlaceholder();
                }
            }
        });
    }

    // ===== Placeholder =====
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        showPlaceholder();
    }

    private void showPlaceholder() {
        setText(placeholder);
        setForeground(placeholderColor);
        setEchoChar((char) 0);
        showingPlaceholder = true;
    }

    public boolean isEmpty() {
        return showingPlaceholder || getPassword().length == 0;
    }

    // ===== Apparence =====
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setArc(int arc) {
        this.arc = arc;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        g2.dispose();
    }
}
