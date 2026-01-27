package repository;

import database.Database;
import model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRepository {

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

    public Utilisateur authentifier(String email, String motDePasse) {
        String sql = "SELECT * FROM utilisateur WHERE email = ? AND mdp = ?";

        try (Connection cnx = Database.getConnexion();
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, motDePasse);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utilisateur u = new Utilisateur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("role")
                );
                u.setId(rs.getInt("id_utilisateur"));
                return u;
            }

        } catch (SQLException e) {
            System.out.println("Erreur authentification : " + e.getMessage());
        }

        return null;
    }
}