package fr.askig2i.ascig2i.model;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="USER",
        uniqueConstraints={
        @UniqueConstraint(columnNames ={"FIRSTNAME", "LASTNAME"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long id;
    @Column(name = "LOGIN", nullable = false, length = 50)
    private String login;
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @ManyToMany(mappedBy = "LIAISON_USER")
    private Set<Password> passwordSet;




    public User(String login, String password) {
        this.login="";
        this.password="";
        if ((login != null) && login.length() < 50) {this.login=login;}
        if ((password != null) && password.length() < 50) {this.password=password;}
    }

    public User() {
        this.login="";
        this.password="";
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }
}
