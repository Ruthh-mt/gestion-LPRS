package repository;

import database.Database;
import model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRepository {
    private Connection cnx;

    public UtilisateurRepository() {
        this.cnx = Database.getConnexion();
    }


    public boolean inscrire(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mdp, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection cnx = Database.getConnexion();
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getPrenom());
            ps.setString(3, utilisateur.getEmail());
            ps.setString(4, utilisateur.getMotDePasse());
            ps.setString(5, utilisateur.getRole());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("Erreur inscription : " + e.getMessage());
            return false;
        }
    }

    public boolean emailExiste(String email) {
        String sql = "SELECT id_utilisateur FROM utilisateur WHERE email = ?";

        try (Connection cnx = Database.getConnexion();
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Erreur vérification email : " + e.getMessage());
            return true;
        }
    }

    public String getPasswordbyEmail(String email) {

        String sql = "Select mdp from utilisateur where email = ?";

        try {
            PreparedStatement ps = this.cnx.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("mdp");
            }
        } catch (SQLException e) {
            return "Erreur lors de la recuperation du mdp" + e.getMessage();
        }
        return null;
    }

    public Utilisateur getUser(String email, String mdp) {
        String sql = "Select * from utilisateur where email = ?";
        int id = 0;
        String nom = null;
        String prenom = null;
        String role = null;
        Utilisateur user = null;

        try {
            PreparedStatement ps = this.cnx.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_utilisateur");
                nom = rs.getString("nom");
                prenom = rs.getString("prenom");
                email = email;
                user = new Utilisateur(id, nom, prenom);


                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}