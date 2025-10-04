package fr.ensicaen.tennis.util;

public class PasswordGenerator {
    public static void main(String[] args) {
        String plainPassword = "onadorelejava";
        String hashed = PasswordUtil.hashPassword(plainPassword);
        System.out.println("Mot de passe hashé : " + hashed);
    }
}
