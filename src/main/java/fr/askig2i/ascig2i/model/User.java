package fr.askig2i.ascig2i.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long id;
    @Column(name = "SERV_NAME", nullable = false, length = 50)
    private String servName;
    @Column(name = "LOGIN", nullable = false, length = 50)
    private String login;
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
    @Column(name = "URL", nullable = false, length = 255)
    private String url;

    public User(String servName, String login, String password, String url) {
        this.servName = "";
        this.login="";
        this.password="";
        this.url="";
        if ((servName != null) && servName.length() < 100) {this.servName=servName;}
        if ((login != null) && login.length() < 50) {this.login=login;}
        if ((password != null) && password.length() < 50) {this.password=password;}
        if ((url != null) && url.length() < 100) {this.url=url;}
    }

    public User() {
        this.servName = "";
        this.login="";
        this.password="";
        this.url="";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(servName, user.servName) && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servName, login);
    }

    @Override
    public String toString() {
        return "User{" +
                "servName='" + servName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
