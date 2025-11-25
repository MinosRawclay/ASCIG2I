package fr.askig2i.ascig2i.testes;

import fr.askig2i.ascig2i.model.Password;

public class encription {
    static void main() {
        Password password1 = new Password(
                "Netflix","raphel.0@gmail.com","azertLuiopazert**iopaz$rtyuiop12ertBuiopaz5rtyuiop","https://www.netflix.com");
        System.out.println("Password 1: " + password1.toString());
        System.out.println("Password 1 hash: " + password1.hashCode());

        System.out.println(password1.getEncryptedPassword());
        String decrypt = password1.decrypt(password1.getEncryptedPassword());
        System.out.println(decrypt);


    }
}
