package fr.askig2i.ascig2i.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORY")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    @Column(name = "DESCRIPTION", length = 255)
    private String description;


    @ManyToMany
    @JoinTable(
            name = "LIAISON_CATEGORY",
            joinColumns = @JoinColumn(name = "ID_CATEGORY"),
            inverseJoinColumns = @JoinColumn(name = "ID_PASSWORD")
    )
    private Set<Password> passwordSet;

    public Category() {
        name = "";
        description = "";
        passwordSet = new HashSet<>();
    }

    public Category(String name,  String description) {
        if(name==null || name.length()>=50){return;}
        if(description==null || description.length()>=255){return;}
        this.name = name;
        this.description = description;
        passwordSet = new HashSet<>();
    }

    public boolean containS (String s) {
        return false;
    }

    public void addPassword(Password password){
        if(password != null && !this.passwordSet.contains(password)){
            this.passwordSet.add(password);
            password.setCategory(this);
        }
    }

    public void setPassword(Password password){
        if(password!=null){
            this.passwordSet.add(password);
        }
    }

    public Long getId() {
        return id;
    }

    public Set<Password> getPasswordSet() {
        return passwordSet;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
