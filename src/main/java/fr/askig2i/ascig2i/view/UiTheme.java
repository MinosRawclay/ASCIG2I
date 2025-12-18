package fr.askig2i.ascig2i.view;

import java.awt.Color;
public final class UiTheme {
    private UiTheme() {}

    // === Couleurs principales === Button ===
    public static final Color PRIMARY_START = new Color(168, 168, 168);
    public static final Color PRIMARY_END   = new Color(255, 255, 255);

    public static final Color PRIMARY_START_PRESSED = new Color(55, 52, 62);
    public static final Color PRIMARY_END_PRESSED   = new Color(132, 132, 150);

    // === Panel ===
    public static final int ARC_PANEL = 20;

    // === Texte ===
    public static final Color TEXT_NORMAL  = new Color(84,84,84);
    public static final Color TEXT_PRESSED = new Color(220, 220, 200);

    // === Text fields ===
    public static final Color TF_BG = Color.WHITE;
    public static final Color TF_PLACEHOLDER = Color.GRAY;
    public static final Color TF_BORDER = new Color(180, 180, 180);

    // === Backgrounds ===
    public static final Color BACKGROUND_START = new Color(255, 102, 196);
    public static final Color BACKGROUND_END = new Color(82, 112, 255);

    // === Champs texte ===
    public static final Color FIELD_BACKGROUND = new Color(45, 45, 90);
    public static final Color FIELD_BORDER     = new Color(120, 120, 220);
}

