package fr.askig2i.ascig2i.view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
    private EntityManager em = emf.createEntityManager();

    public HomePanel() {

        // ===== pas de fond =====
        setLayout(new GridBagLayout());
        setOpaque(false);




    }
}
