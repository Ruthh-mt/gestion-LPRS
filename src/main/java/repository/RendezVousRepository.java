package repository;

import database.Database;
import model.RendezVous;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RendezVousRepository {

    private final Connection cnx;
    private final SalleRepository salleRepo;

    public RendezVousRepository() {
        this.cnx = Database.getConnexion();
        this.salleRepo = new SalleRepository();
    }

    public void marquerRendezVousPassés() {
        String sql = "UPDATE rendez_vous SET status = 'Passé' " +
                "WHERE status = 'Prévus' " +
                "AND (date_rendez_vous < ? OR (date_rendez_vous = ? AND heure < ?))";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();
            ps.setDate(1, Date.valueOf(today));
            ps.setDate(2, Date.valueOf(today));
            ps.setTime(3, Time.valueOf(now));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur marquage passés : " + e.getMessage());
        }
    }

    // Remplacer creerRendezVous() — supprimer l'appel marquerOccupee
    public boolean creerRendezVous(RendezVous rdv) {
        String sql = "INSERT INTO rendez_vous (date_rendez_vous, heure, status, ref_professeur, " +
                "ref_dossier_inscription, ref_salle) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(rdv.getDateRendezVous()));
            ps.setTime(2, Time.valueOf(rdv.getHeure()));
            ps.setString(3, rdv.getStatus());
            ps.setInt(4, rdv.getRefProfesseur());
            ps.setInt(5, rdv.getRefDossierInscription());
            ps.setInt(6, rdv.getRefSalle());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur création rendez-vous : " + e.getMessage());
            return false;
        }
    }

    public List<RendezVous> findByProfesseur(int refProfesseur) {
        String sql = "SELECT * FROM rendez_vous WHERE ref_professeur = ? ORDER BY date_rendez_vous, heure";
        List<RendezVous> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, refProfesseur);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erreur récupération rendez-vous : " + e.getMessage());
        }
        return liste;
    }

    public List<RendezVous> findAll() {
        String sql = "SELECT * FROM rendez_vous ORDER BY date_rendez_vous, heure";
        List<RendezVous> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erreur récupération rendez-vous : " + e.getMessage());
        }
        return liste;
    }

    // Remplacer updateStatut() — supprimer l'appel marquerLibre
    public boolean updateStatut(int idRendezVous, String nouveauStatut) {
        String sql = "UPDATE rendez_vous SET status = ? WHERE id_rendez_vous = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nouveauStatut);
            ps.setInt(2, idRendezVous);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur mise à jour statut : " + e.getMessage());
            return false;
        }
    }


    // Remplacer supprimerRendezVous() — supprimer l'appel marquerLibre
    public boolean supprimerRendezVous(int idRendezVous) {
        String sql = "DELETE FROM rendez_vous WHERE id_rendez_vous = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idRendezVous);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur suppression rendez-vous : " + e.getMessage());
            return false;
        }
    }
    private RendezVous mapRow(ResultSet rs) throws SQLException {
        return new RendezVous(
                rs.getInt("id_rendez_vous"),
                rs.getDate("date_rendez_vous").toLocalDate(),
                rs.getTime("heure").toLocalTime(),
                rs.getString("status"),
                rs.getInt("ref_professeur"),
                rs.getInt("ref_dossier_inscription"),
                rs.getInt("ref_salle")
        );
    }

    private int getRefSalle(int idRendezVous) {
        String sql = "SELECT ref_salle FROM rendez_vous WHERE id_rendez_vous = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idRendezVous);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("ref_salle");
        } catch (SQLException e) {
            System.out.println("Erreur récupération ref_salle : " + e.getMessage());
        }
        return -1;
    }

    public boolean mettreAJourRendezVous(RendezVous rdv) {
        String sql = "UPDATE rendez_vous SET date_rendez_vous = ?, heure = ?, status = ?, " +
                "ref_dossier_inscription = ?, ref_salle = ? WHERE id_rendez_vous = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(rdv.getDateRendezVous()));
            ps.setTime(2, Time.valueOf(rdv.getHeure()));
            ps.setString(3, rdv.getStatus());
            ps.setInt(4, rdv.getRefDossierInscription());
            ps.setInt(5, rdv.getRefSalle());
            ps.setInt(6, rdv.getIdRendezVous());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur mise à jour rendez-vous : " + e.getMessage());
            return false;
        }
    }

        public int countRendezVous() {
        String sql = "SELECT COUNT(*) FROM rendez_vous" ;
        int nb_rdv = 0;
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                nb_rdv++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nb_rdv;
    }
}