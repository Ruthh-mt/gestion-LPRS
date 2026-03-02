package repository;

import model.FicheEtudiant;
import model.Filiere;

import java.sql.*;
import java.util.ArrayList;

public class FiliereRepository {
    private Connection connection;

    public ArrayList<Filiere> getAllFiliere() throws SQLException {
        Statement st = connection.createStatement();
        ArrayList<Filiere> filieres = new ArrayList<>();
        String sql = "SELECT * from filiere";
        int id = 0;
        int ref_createur = 0;
        String nom = "";
        Filiere filiere = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultatRequete = stmt.executeQuery();
            while (resultatRequete.next()) {
                id = resultatRequete.getInt("id_filiere");
                nom = resultatRequete.getString("nom");
                filiere = new Filiere(id, nom);
                filieres.add(filiere);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filieres;
    }
}
