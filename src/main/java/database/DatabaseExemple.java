package database;


import java.sql.*;

public class DatabaseExemple {
    private static final String SERVEUR = "YOUR_SERVEUR";
    private static final String NOM_BDD = "YOUR_DATABASE_NAME";
    private static final String UTILISATEUR = "YOUR_USERNAME";
    private static final String MOT_DE_PASSE = "YOUR_PASSWORD";

    private static String getUrl() {
        return "jdbc:mysql://" + SERVEUR + "/" + NOM_BDD + "?serverTimezone=UTC";
    }

    public static Connection getConnexion() {
        Connection cnx = null;
        try {
            cnx = DriverManager.getConnection(getUrl(), UTILISATEUR, MOT_DE_PASSE);
            System.out.println("Connexion réussie à la base de données !");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }

        return cnx;
    }

    public static void main(String[] args) {
        Connection cnx = getConnexion();
        if (cnx != null) {
            System.out.println("Connexion établie avec succès !");
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }
    }
}