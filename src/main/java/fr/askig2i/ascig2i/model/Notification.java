package fr.askig2i.ascig2i.model;

import jakarta.persistence.*;
import fr.askig2i.ascig2i.model.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NOTIFICATION")
    private int id;
    @Column(name = "MSG")
    private String message;
    @ManyToOne
    @JoinColumn(name = "ID_USER") // Nom de la colonne de clé étrangère
    private User user;
}
