package repository;

import database.Database;
import model.FicheEtudiant;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.Types.NULL;

public class FicheEtudiantRepository {
    private  Connection connection;

    public FicheEtudiantRepository() {
        this.connection = Database.getConnexion();
    }

    public boolean AjouterFicheEtudiant(FicheEtudiant fe) throws SQLException {
        String sql = "INSERT INTO fiche_etudiante (nom_etudiant,ref_createur, prenom_etudiant,email_etudiant,telephone,adresse,dernierDiplome) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fe.getNom());
            ps.setInt(2,1);
            ps.setString(3, fe.getPrenom());
            ps.setString(4, fe.getEmail());
            ps.setString(5, fe.getTelephone());
            ps.setString(6, fe.getAdresse());
            ps.setString(7, fe.getDernierDiplome());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public Boolean deleteFicheEtudiant(int id) throws SQLException {
        String sql = "DELETE FROM fiche_etudiante WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.execute();
    }

    public Boolean getFicheEtudiant(int id) throws SQLException {
        String sql = "SELECT * FROM fiche_etudiante WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return true;
        }
        return false;
}

    public ArrayList<FicheEtudiant> getToutesLesFiches() throws SQLException {
        String sql = "SELECT * FROM fiche_etudiante";
        ArrayList<FicheEtudiant> ficheEtudiants = new ArrayList<FicheEtudiant>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        FicheEtudiant fe;
        while(rs.next()) {
            int id = rs.getInt("id_fiche_etudiante");
            int ref_createur = rs.getInt("ref_createur");
            String nom = rs.getString("nom_etudiant");
            String prenom = rs.getString("prenom_etudiant");
            String email = rs.getString("email_etudiant");
            String telephone = rs.getString("telephone");
            String adresse = rs.getString("adresse");
            String dernierDiplome = rs.getString("dernierDiplome");
            fe = new FicheEtudiant(id,ref_createur,nom,prenom,email,telephone,adresse,dernierDiplome);
            ficheEtudiants.add(fe);

        }
        return  ficheEtudiants;
    }
}
