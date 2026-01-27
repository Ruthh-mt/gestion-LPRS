package repository;

import database.Database;
import model.DossierInscription;

import java.sql.*;
import java.util.ArrayList;

public class DossierRepository {
    private Connection connection;

    public DossierRepository() {
        this.connection = Database.getConnexion();
    }
    public void ajouterDossier(DossierInscription  dossier){
        String sql = "INSERT INTO Dossier (date,heure, motivation , ref_filiere , ref_fiche_etudiante) VALUES (?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (Date) dossier.getDate());
            ps.setTime(2,dossier.getHeure());
            ps.setString(3,dossier.getFiliere());
            ps.setString(4,dossier.getMotivation());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerDossier(DossierInscription  dossier){
        String sql = "DELETE FROM Dossier WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,dossier.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDossier(DossierInscription  dossier){
        String sql = "UPDATE dossier set date = ?, heure = ?, filiere = ?, motivation = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (Date) dossier.getDate());
            ps.setTime(2,dossier.getHeure());
            ps.setString(3,dossier.getFiliere());
            ps.setString(4,dossier.getMotivation());
            ps.setInt(5,dossier.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<DossierInscription> getTousLesDossier() throws SQLException {
        String sql = "SELECT * FROM Dossier";
        ArrayList<DossierInscription> dossiers = new ArrayList<>();
        int id = 0 ;
        Date date = null ;
        Time heure = null ;
        String filiere = null ;
        String motivation = null ;
        DossierInscription dossier  = null ;

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            id = rs.getInt("id");
            date = rs.getDate("date");
            heure = rs.getTime("heure");
            filiere = rs.getString("filiere");
            motivation = rs.getString("motivation");
            dossiers.add(dossier);
        }
        return dossiers;
    }
}
