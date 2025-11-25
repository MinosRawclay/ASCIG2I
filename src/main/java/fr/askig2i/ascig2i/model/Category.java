package fr.askig2i.ascig2i.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORY")
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "LIAISON_CATEGORY")
    private Set<Password> passwordSet;
}
