package fr.askig2i.ascig2i.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity

public class Password {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="serviceName")
    private String serviceName;
    @Column(name="login")
    private String login;
    @Column(name="encryptedPassword")
    private String encryptedPassword;
    @Column(name="url")
    private String url;
    @Column(name="Categorie")
    private List<Category> categorie;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Category> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Category> categorie) {
        this.categorie = categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Password password)) return false;
        return id == password.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", login='" + login + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", url='" + url + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
