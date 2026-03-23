package repository;

import database.Database;
import model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilisateurRepository {
    private Connection cnx;

    public UtilisateurRepository() {
        this.cnx = Database.getConnexion();
    }


    public boolean inscrire(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mdp, role) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
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

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Erreur vérification email : " + e.getMessage());
            return true;
        }
    }

    public Utilisateur getUserByMail(Utilisateur user) {
        String get = "SELECT * FROM utilisateur WHERE email=? ";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(get);
            stmt.setString(1, user.getEmail());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("role"),
                        rs.getInt("ref_filiere")
                );
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + '\n' + " >>" + e.getMessage());
            return null;
        }
    }


    public boolean supprimerUtilisateurParEmail(String email) {
        String delete = "DELETE FROM utilisateur WHERE email=? ";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(delete);
            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("User deleted with sucess : " + '\n' + ">> mail was : " + email);
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppresion : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public boolean mettreAJourUtilisateur(Utilisateur utilisateur) {
        String update = "UPDATE utilisateur SET nom=?,prenom=?,email=?,role=? WHERE id_utilisateur=?";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(update);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getRole());
            stmt.setInt(5, utilisateur.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise a jour de l'utilisateur : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public ArrayList<Utilisateur> getTousLesUtilisateurs() {
        Utilisateur user = null;
        String getAll = "SELECT* FROM utilisateur";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(getAll);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
            while (rs.next()) {
                user = new Utilisateur(
                        rs.getInt("id_utilidateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("role"),
                        rs.getInt("ref_filiere")
                );
                utilisateurs.add(user);
            }
            return utilisateurs;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation des utilisateurs : " + '\n' + " >>" + e.getMessage());
            return null;
        }
    }

    public boolean createCode(String code, String email) {
        String addCode = "UPDATE utilisateur SET code=? WHERE email=?";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(addCode);
            stmt.setString(1, code);
            stmt.setString(2, email);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du code  : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public String getCode(String email) {
        String getCode = "SELECT code FROM utilisateur WHERE email=?";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(getCode);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("code");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation du code  : " + '\n' + " >>" + e.getMessage());
            return null;
        }

    }

    public boolean deleteCode(String email) {
        String deleteCode = "UPDATE utilisateur SET code=? WHERE email=?";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(deleteCode);
            stmt.setString(1, null);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Code deleted with sucess : " + '\n' + ">> mail was : " + email);
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppresion : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }
    public boolean mettreAJourMdp(Utilisateur utilisateur) {
        String update = "UPDATE utilisateur SET mdp=? WHERE email=?";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(update);
            stmt.setString(1, utilisateur.getMotDePasse());
            stmt.setString(2, utilisateur.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise a jour du mdp: " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public Utilisateur getUtilisateurById(int id) {
        Utilisateur user = null;
        String query = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new Utilisateur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                );
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + '\n' + " >>" + e.getMessage());
            return null;
        }
    }

}