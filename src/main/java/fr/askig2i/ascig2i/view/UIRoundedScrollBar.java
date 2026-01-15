package fr.askig2i.ascig2i.view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class UIRoundedScrollBar extends BasicScrollBarUI {
    private static final int ARC = UiTheme.ARC_PANEL;
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(240, 240, 240));
        g2.fillRoundRect(
                trackBounds.x,
                trackBounds.y,
                trackBounds.width,
                trackBounds.height,
                ARC,
                ARC
        );
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dégradé vertical
        GradientPaint gradient = new GradientPaint(
                thumbBounds.x,
                thumbBounds.y,
                UiTheme.PRIMARY_START_PRESSED,
                thumbBounds.x,
                thumbBounds.y + thumbBounds.height,
                UiTheme.PRIMARY_END_PRESSED
        );

        Shape thumb = new RoundRectangle2D.Float(
                thumbBounds.x,
                thumbBounds.y,
                thumbBounds.width,
                thumbBounds.height,
                ARC,
                ARC
        );

        g2.setPaint(gradient);
        g2.fill(thumb);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }
}

