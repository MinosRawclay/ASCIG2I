package fr.askig2i.ascig2i.testes;

import fr.askig2i.ascig2i.model.Category;
import fr.askig2i.ascig2i.model.Password;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.service.Service;

public class initBDD {
    static void main() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASCIG2I");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        User user1 = new User("test","test");
        User user2 = new User("Alexandre","askig2i");
        User user3 = new User("Quentin","salengro");
        User user4 = new User("Maxime","maximillien");

        Category category1 = new Category("Administration","école et sites du gouvernement");
        Category category2 = new Category("Réseaux sociaux","Twitter tmtc");
        Category category3 = new Category("Jeux","FORTNITEUH");
        Category category4 = new Category("Autres","Les trucs y existent mais tsais pas pourquoi");

        Password password1 = new Password(
                "Netflix","raphel.0@gmail.com","1234","https://www.netflix.com");
        Password password2 = new Password(
                "Google gmail","raphael.0@gmail.com","12345","");
        Password password3 = new Password(
                "Spotify", "raphael.0@gmail.com", "5678", "https://www.spotify.com");
        Password password4 = new Password(
                "Amazon", "r.0@email.com", "abcd", "https://www.amazon.fr");
        Password password5 = new Password(
                "GitHub", "raphael.0@dev.com", "efgh90", "https://github.com");
        Password password6 = new Password(
                "Twitter", "0_raphael@social.com", "pass123", "https://twitter.com");
        Password password7 = new Password(
                "LinkedIn", "raphael.0@pro.com", "linked456", "https://www.linkedin.com");
        Password password8 = new Password(
                "Facebook", "raphael.0@fb.com", "fbpass123", "https://www.facebook.com");
        Password password9 = new Password(
                "Reddit", "raphael_0@reddit.com", "redditpass", "https://www.reddit.com");
        Password password10 = new Password(
                "YouTube", "alexandre.dupont@email.com", "yt1234", "https://www.youtube.com");
        Password password11 = new Password(
                "Instagram", "alexandre.d@insta.com", "insta5678", "https://www.instagram.com");
        Password password12 = new Password(
                "Microsoft 365", "alexandre.d@outlook.com", "msOffice90", "https://www.office.com");
        Password password13 = new Password(
                "Dropbox", "alexandre.dup@dropbox.com", "dbPass123", "https://www.dropbox.com");
        Password password14 = new Password(
                "Slack", "alexandre.d@slack.com", "slack456", "https://slack.com");

        password1.addCategoty(category1);
        category1.addPassword(password2);
        password2.addCategoty(category2);

        user1.addPassword(password1);
        user1.addPassword(password2);

        user3.addPassword(password2);
        password2.addUser(user1);
        password3.addUser(user2);
        password4.addUser(user3);

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
        em.persist(category1);
        em.persist(category2);
        em.persist(category3);
        em.persist(category4);
        em.persist(password1);
        em.persist(password2);
        em.persist(password3);
        em.persist(password4);
        em.persist(password5);
        em.persist(password6);
        em.persist(password7);
        em.persist(password8);
        em.persist(password9);
        em.persist(password10);
        em.persist(password11);
        em.persist(password12);
        em.persist(password13);
        em.persist(password14);


        et.commit();

        user1.getCategories();

    }
}
