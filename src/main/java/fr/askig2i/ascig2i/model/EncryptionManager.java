package fr.askig2i.ascig2i.model;

public class EncryptionManager {
    public static String encrypt(String pwd, int mod) {
        int key = (int) (mod^2 - 4) & 0xFFFF; // key
        StringBuilder builder = new StringBuilder();

        for (char c : pwd.toCharArray()) {
            long encrypted = ((long)c) ^ key;
            // convertit en hex sur 16 caractères (padding avec des zéros)
            builder.append(String.format("%04X", encrypted));
        }

        return builder.toString();
    }

    public static String decrypt(String encrypted, int mod) {
        int key = (int)(mod^2  - 4) & 0xFFFF; // même key
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i += 4) {
            String hex = encrypted.substring(i, i + 4);
            int encryptedChar = Integer.parseUnsignedInt(hex, 16);
            char original = (char)(encryptedChar ^ key);
            builder.append(original);
        }

        return builder.toString();
    }
}
