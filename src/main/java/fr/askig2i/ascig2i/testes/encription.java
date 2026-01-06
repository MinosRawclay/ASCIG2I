package fr.askig2i.ascig2i.testes;

import fr.askig2i.ascig2i.model.EncryptionManager;
import fr.askig2i.ascig2i.model.Password;

public class encription {
    static void main() {
        Password password1 = new Password(
                "Netflix","raphael.0@gmail.com","askig2i","https://www.netflix.com");

        System.out.println(password1.getEncryptedPassword());
        String decrypt = EncryptionManager.decrypt(password1.getEncryptedPassword(), ("raphael.0@gmail.com").hashCode()    );
        System.out.println(decrypt);


    }
}
