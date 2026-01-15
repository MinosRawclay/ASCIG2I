package fr.askig2i.ascig2i;

import fr.askig2i.ascig2i.model.Category;
import fr.askig2i.ascig2i.model.EncryptionManager;
import fr.askig2i.ascig2i.model.Password;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.*;

import java.util.List;

public class SQLHandler {

    public static User checkUser(String lg, String pwd, EntityManager em){
        pwd = EncryptionManager.encrypt(pwd, lg.hashCode());
        System.out.printf(pwd);
        String strQuery = "SELECT u FROM User u "
         + "WHERE u.login = :login "
         + "AND u.password = :password";
         Query query = em.createQuery(strQuery);
         query.setParameter("login", lg);
         query.setParameter("password", pwd);
         List<User> users = query.getResultList();
         if(users.isEmpty()){return null;}
        return users.getFirst();
    }

    public static List<User> getUsersPwd(Password pwd, EntityManager em){
        String strQuery = "SELECT u FROM User u "
                + "WHERE Password = :pwd ";
        Query query = em.createQuery(strQuery);
        query.setParameter("pwd", pwd);
        List<User> users = query.getResultList();
        if(users.isEmpty()){return null;}
        return users;
    }

    public static List getCategories(EntityManager em){
        String strQuery = "SELECT c FROM Category c";
        Query query = em.createQuery(strQuery);
        List categories = query.getResultList();
        if(categories.isEmpty()){return null;}
        return categories;
    }
    public static List getUsers(EntityManager em){
        String strQuery = "SELECT u FROM User u";
        Query query = em.createQuery(strQuery);
        List users = query.getResultList();
        if(users.isEmpty()){return null;}
        return users;
    }

    public static void addPasswordUser(Password p, User u, EntityManager em){
        EntityTransaction et = em.getTransaction();
        et.begin();
        u.addPassword(p);
        em.persist(u);
        em.persist(p);
        et.commit();
    }

    public static void addUserPassword(Password p, User u, EntityManager em){
        EntityTransaction et = em.getTransaction();
        et.begin();
        p.addUser(u);
        em.persist(u);
        em.persist(p);
        et.commit();
    }

    public static void addCategoryPassword(Category c, Password p, EntityManager em){
        EntityTransaction et = em.getTransaction();
        et.begin();
        p.addCategoty(c);
        em.persist(c);
        em.persist(p);
        et.commit();
    }
    public static void addPasswordCategory(Category c, Password p, EntityManager em){
        EntityTransaction et = em.getTransaction();
        et.begin();
        c.addPassword(p);
        em.persist(c);
        em.persist(p);
        et.commit();
    }

    public static void saveNewPassword(Password p, EntityManager em){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(p);
        et.commit();
    }

    public static void saveNewUser(User u, EntityManager em){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(u);
        et.commit();
    }

    public static void saveNewCategory(Category c, EntityManager em){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(c);
        et.commit();
    }

    public static void deleteCategory(String c, EntityManager em) {
        String strQuery = "SELECT c FROM Category c "
                + "WHERE name = :name ";
        Query query = em.createQuery(strQuery);
        query.setParameter("name", c);
        List<Category> cs = query.getResultList();
        Category cat = cs.getFirst();
        EntityTransaction et = em.getTransaction();
        et.begin();

        Category managed = em.find(Category.class, cat.getId());

        if (managed != null) {
            // Nettoyage de la table de jointure
            for (Password p : managed.getPasswordSet()) {
                p.getCategories().remove(managed);
            }
            managed.getPasswordSet().clear();

            // Suppression finale
            em.remove(managed);
        }

        et.commit();
    }


}
