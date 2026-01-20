package repository;

import database.Database;
import model.FicheEtudiant;

import java.sql.*;
import java.util.ArrayList;

public class FicheEtudiantRepository {
    private Connection connection;

    public FicheEtudiantRepository() {
        this.connection = Database.getConnexion();
    }

    public void AjouterFicheEtudiant(FicheEtudiant fe) throws SQLException {
        String sql = "INSERT INTO ficheEtudiant (nom,prenom,dernierDiplome,email,telephone,adresse) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fe.getNom());
            ps.setString(2, fe.getPrenom());
            ps.setString(3, fe.getDernierDiplome());
            ps.setString(4, fe.getEmail());
            ps.setString(5, fe.getTelephone());
            ps.setString(6, fe.getAdresse());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public Boolean deleteFicheEtudiant(int id) throws SQLException {
        String sql = "DELETE FROM ficheEtudiant WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.execute();
    }

    public Boolean getFicheEtudiant(int id) throws SQLException {
        String sql = "SELECT * FROM ficheEtudiant WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return true;
        }
        return false;
}

    public ArrayList<FicheEtudiant> getToutesLesFiches() throws SQLException {
        String sql = "SELECT * FROM ficheEtudiant";
        ArrayList<FicheEtudiant> ficheEtudiants = new ArrayList<FicheEtudiant>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String dernierDiplome = rs.getString("dernierDiplome");
            String email = rs.getString("email");
            String telephone = rs.getString("telephone");
            String adresse = rs.getString("adresse");
        }
        return  ficheEtudiants;
    }
}
