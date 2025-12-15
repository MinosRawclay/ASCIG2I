package fr.askig2i.ascig2i.model;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.*;

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

    @ManyToMany
    @JoinTable(
            name = "LIAISON_USER",
            joinColumns = @JoinColumn(name = "ID_USER"),
            inverseJoinColumns = @JoinColumn(name = "ID_PASSWORD")
    )
    private Set<Password> passwordSet;



    public User(String login, String password) {
        this.login="";
        this.password="";
        if ((login != null) && login.length() < 50) {this.login=login;}
        if ((password != null && login != null) && password.length() < 50) {this.password=EncryptionManager.encrypt(password, login.hashCode());
        this.passwordSet = new HashSet<>();
        }
    }

    public Set<Password> getPasswordSet() {
        Set<Password> temp = new HashSet<>();
        temp.addAll(this.passwordSet);
        return temp;
    }
    public Set<Password> getPwdByCategory(Category cat) {
        Set<Password> temp = new HashSet<>();
        for(Password pwd : this.passwordSet){
            if(pwd.hasCategory(cat)){
                temp.add(pwd);
            }
        }
        return temp;
    }

    public User() {
        this.login="";
        this.password="";
    }

    public void setPassword(Password password){
        if(password!=null){
            this.passwordSet.add(password);
        }
    }
    public void addPassword(Password password){
        if(password != null && !this.passwordSet.contains(password)){
            this.passwordSet.add(password);
            password.setUser(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }


}
