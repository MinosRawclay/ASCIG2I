package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Password;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CardPanel extends UiPanel {

    private Password psw;

    public CardPanel(Password password) {
        this.psw = password;
        setPreferredSize(new Dimension(250, 80));
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;

        add(textLogin, gbc);



        ImageIcon icon;
        //String faviconUrl = "https://www.google.com/s2/favicons?sz=128&domain=factorio.com";
        icon = new ImageIcon(psw.getUrl());









    }
}
