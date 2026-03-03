package repository;

import database.Database;
import model.Salle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Salle> findSallesDisponibles() {
        String sql = "SELECT * FROM salle WHERE est_occupe = 0";
        List<Salle> salles = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salles.add(new Salle(
                        rs.getInt("id_salle"),
                        rs.getInt("capacite"),
                        false
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur récupération salles disponibles : " + e.getMessage());
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