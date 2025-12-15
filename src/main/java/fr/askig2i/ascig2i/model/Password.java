package fr.askig2i.ascig2i.model;

import jakarta.persistence.*;

import java.util.*;

@Entity

public class Password {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_PASSWORD")
    private int id;
    @Column(name="SERVICENAME", length = 50)
    private String serviceName;
    @Column(name="LOGIN", length = 50)
    private String login;
    @Column(name="ENCRYPTPASSWORD", length = 204)
    private String encryptedPassword;
    @Column(name="URL", length = 255)
    private String url;
    private static int key = 0x44;
    @ManyToMany
    /*@JoinTable(
            name = "LIAISON_CATEGORY",
            joinColumns = @JoinColumn(name = "ID_PASSWORD"),
            inverseJoinColumns = @JoinColumn(name = "ID_CATEGORY")
    )*/
    private Set<Category> categories;
    @ManyToMany
    /*@JoinTable(
            name = "LIAISON_USER",
            joinColumns = @JoinColumn(name = "ID_PASSWORD"),
            inverseJoinColumns = @JoinColumn(name = "ID_USER")
    )*/
    private Set<User> users;

    public Password() {
        super();
        serviceName = "";
        login = "";
        encryptedPassword = "";
        url = "";
        categories = new HashSet<>();
    }

    public Password(String serviceName, String login, String password, String url) {
        if(serviceName==null || serviceName.length()>=50){return;}
        if(login==null || login.length()>=50){return;}
        if(password==null || password.length()>=50){return;}
        if(url==null || url.length()>=255){return;}
        this.serviceName = serviceName;
        this.login = login;
        this.encryptedPassword = EncryptionManager.encrypt(password, login.hashCode());
        this.url = url;
        this.categories = new HashSet<>();
        this.users = new HashSet<>();

    }

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

    public boolean hasCategory(Category cat){
        return this.categories.contains(cat);
    }

    public void setCategory(Category category){
        if(category!=null){
            this.categories.add(category);
        }
    }
    public void addCategoty(Category category){
        if(category != null && !this.categories.contains(category)){
            this.categories.add(category);
            category.setPassword(this);
        }
    }

    public void setUser(User user){
        if(user!=null){
            this.users.add(user);
        }
    }
    public void addUser(User user){
        if(user != null && !this.categories.contains(user)){
            this.users.add(user);
            user.setPassword(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Password password)) return false;
        return id == password.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(login + serviceName);
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", login='" + login + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", url='" + url + '\'' +
                ", categorie=" + categories +
                '}';
    }





}
