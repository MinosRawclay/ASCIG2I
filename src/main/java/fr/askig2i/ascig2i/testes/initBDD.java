package fr.askig2i.ascig2i.testes;

import fr.askig2i.ascig2i.model.Category;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.service.Service;

public class initBDD {
    static void main() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        User user1 = new User("Raphael","askig2i");
        User user2 = new User("Alexandre","askig2i");
        User user3 = new User("Quentin","salengro");
        User user4 = new User("Maxime","maximillien");

        //Category category1 = new Category("","");
        //push


        //em.persist(serv3);
        et.commit();
    }
}
