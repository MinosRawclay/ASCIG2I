package fr.askig2i.ascig2i;

import fr.askig2i.ascig2i.model.EncryptionManager;
import fr.askig2i.ascig2i.model.User;
import jakarta.persistence.*;

import java.util.List;

public class SQLHandler {

    public static boolean checkUser(String lg, String pwd, EntityManager em){
        pwd = EncryptionManager.encrypt(pwd, lg.hashCode());

        String strQuery = "SELECT u FROM User u "
         + "WHERE u.login = :login "
         + "AND u.password = :password";
         Query query = em.createQuery(strQuery);
         query.setParameter("login", lg);
         query.setParameter("password", pwd);
         List<User> users = query.getResultList();
        return !users.isEmpty();
    }

}
