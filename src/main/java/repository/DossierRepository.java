package repository;

import database.Database;
import model.DossierInscription;
import model.Filiere;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DossierRepository {
    private Connection connection;

    public DossierRepository() {
        this.connection = Database.getConnexion();

    }

    public boolean ajouterDossier(DossierInscription doss) throws SQLException {
        String sql = "INSERT INTO dossier_inscription (date,heure,motivation_etudiant,ref_filiere,ref_fiche_etudiante) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(doss.getDate()));
            ps.setTime(2, doss.getHeure());
            ps.setString(3, doss.getMotivation());
            ps.setInt(4, doss.getFiliere());
            ps.setInt(5, doss.getRef_fiche());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public ArrayList<DossierInscription> getAllDossiers() throws SQLException {
        String sql = "SELECT * from dossier_inscription";
        ArrayList<DossierInscription> dossiers = new ArrayList<>();
        DossierInscription dossierInscription = null;
        int id = 0;
        Date date = null;
        Time heure = null;
        String motivation = null;
        int ref_filiere = 0;
        int ref_fiche = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultatRequete = stmt.executeQuery();
            while (resultatRequete.next()) {
                id = resultatRequete.getInt("id_dossier_inscription");
                date = resultatRequete.getDate("date");
                heure = resultatRequete.getTime("heure");
                motivation = resultatRequete.getString("motivation_etudiant");
                ref_filiere = resultatRequete.getInt("ref_filiere");
                ref_fiche = resultatRequete.getInt("ref_fiche_etudiante");

                dossierInscription = new DossierInscription(id, date, heure, motivation, ref_filiere, ref_fiche);
                dossiers.add(dossierInscription);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dossiers;
    }





    public void supprimerDossier(DossierInscription dossier) {
        String sql = "DELETE FROM Dossier WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, dossier.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DossierInscription> findAll() {
    }
}


