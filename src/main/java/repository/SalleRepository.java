package repository;

import database.Database;
import model.Salle;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class SalleRepository {

    private final Connection cnx;

    public SalleRepository() {
        this.cnx = Database.getConnexion();
    }

    public List<Salle> findAll() {
        String sql = "SELECT * FROM salle";
        List<Salle> salles = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salles.add(new Salle(
                        rs.getInt("id_salle"),
                        rs.getInt("capacite"),
                        rs.getBoolean("est_occupe")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur récupération salles : " + e.getMessage());
        }
        return salles;
    }

    public List<Salle> findSallesDisponibles(LocalDate date, LocalTime heure) {
        String sql = "SELECT * FROM salle s " +
                "WHERE s.id_salle NOT IN (" +
                "  SELECT ref_salle FROM rendez_vous " +
                "  WHERE date_rendez_vous = ? " +
                "  AND status != 'Annulé' " +
                "  AND heure < ADDTIME(?, '01:00:00') " +
                "  AND ADDTIME(heure, '01:00:00') > ?" +
                ")";
        List<Salle> salles = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(date));
            ps.setTime(2, Time.valueOf(heure));
            ps.setTime(3, Time.valueOf(heure));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salles.add(new Salle(rs.getInt("id_salle"), rs.getInt("capacite"), rs.getBoolean("est_occupe")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur salles disponibles : " + e.getMessage());
        }
        return salles;
    }
    public List<Salle> findSallesDisponibles(LocalDate date, LocalTime heure, int excludeRdvId) {
        String sql = "SELECT * FROM salle s " +
                "WHERE s.id_salle NOT IN (" +
                "  SELECT ref_salle FROM rendez_vous " +
                "  WHERE date_rendez_vous = ? " +
                "  AND status != 'Annulé' " +
                "  AND id_rendez_vous != ? " +
                "  AND heure < ADDTIME(?, '01:00:00') " +
                "  AND ADDTIME(heure, '01:00:00') > ?" +
                ")";
        List<Salle> salles = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(date));
            ps.setInt(2, excludeRdvId);
            ps.setTime(3, Time.valueOf(heure));
            ps.setTime(4, Time.valueOf(heure));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salles.add(new Salle(rs.getInt("id_salle"), rs.getInt("capacite"), rs.getBoolean("est_occupe")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur salles disponibles : " + e.getMessage());
        }
        return salles;
    }

    public boolean marquerOccupee(int idSalle) {
        String sql = "UPDATE salle SET est_occupe = 1 WHERE id_salle = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idSalle);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur marquage salle occupée : " + e.getMessage());
            return false;
        }
    }

    public boolean marquerLibre(int idSalle) {
        String sql = "UPDATE salle SET est_occupe = 0 WHERE id_salle = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idSalle);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Erreur marquage salle libre : " + e.getMessage());
            return false;
        }
    }
}