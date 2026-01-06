package fr.askig2i.ascig2i.view;

import fr.askig2i.ascig2i.model.Password;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HomePanel extends JPanel {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
    private EntityManager em = emf.createEntityManager();
    private WindowManager manager;

    private ArrayList<Password> passwords = new ArrayList<>();

    public HomePanel(WindowManager manager) {
        this.manager = manager;

        // ===== pas de fond =====
        setOpaque(true);
        setBackground(Color.blue);

        setLayout(new BorderLayout());

        // HEADER (déjà existant chez toi)
        add(new HeaderPanel(), BorderLayout.NORTH);

        // CONTENU CENTRAL
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(130, 110, 255)); // dégradé géré ailleurs si besoin
        add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        updatePasswords();
        ScrollPanel scrollPanel = new ScrollPanel(passwords);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        contentPanel.add(scrollPanel, gbc);

        Button buttonAddPassword = new Button("addPassword",e->newPassword());
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        //add(buttonAddPassword, gbc);


        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bottomButtons.setOpaque(false);

        bottomButtons.add(new Button("Category",e->newPassword()));
        bottomButtons.add(new Button("userShare",e->newPassword()));

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.6;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTH;

        contentPanel.add(bottomButtons, gbc);











    }

    private void updatePasswords() {
        passwords.add(new Password(
                "Netflix","raphel.0@gmail.com","1234","https://www.netflix.com"));
        passwords.add(new Password(
                "Google gmail","raphael.0@gmail.com","12345",""));
        passwords.add(new Password(
                "Spotify", "raphael.0@gmail.com", "5678", "https://www.spotify.com"));
        passwords.add(new Password(
                "Amazon", "r.0@email.com", "abcd", "https://www.amazon.fr"));
        passwords.add(new Password(
                "GitHub", "raphael.0@dev.com", "efgh90", "https://github.com"));
        passwords.add(new Password(
                "Twitter", "0_raphael@social.com", "pass123", "https://twitter.com"));
        passwords.add(new Password(
                "LinkedIn", "raphael.0@pro.com", "linked456", "https://www.linkedin.com"));
        passwords.add(new Password(
                "Facebook", "raphael.0@fb.com", "fbpass123", "https://www.facebook.com"));
    }

    private void newPassword(){}

    static void main() {
        JFrame frame = new JFrame("Exemple Header");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //WindowManager manager = new WindowManager();
        HomePanel panel = new HomePanel(null);
        frame.add(panel);

        frame.setVisible(true);
    }
}
