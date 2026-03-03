package repository;

import database.Database;
import model.Filiere;

import java.sql.*;
import java.util.ArrayList;

public class FiliereRepository {
    private Connection connection;

    public FiliereRepository() {
        this.connection = Database.getConnexion();
    }

    public ArrayList<Filiere> getAllFilieres() throws SQLException {
        String sql = "SELECT * from filiere";
        ArrayList<Filiere> filieres = new ArrayList<>();
        Filiere filiere = null;
        int id = 0;
        String nom = "";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultatRequete = stmt.executeQuery();
            while (resultatRequete.next()) {
                id = resultatRequete.getInt("id_filiere");
                nom = resultatRequete.getString("nom");
                filiere = new Filiere(id, nom);
                System.out.println("Nom : "+filiere.getNomFiliere());
                System.out.println("Id filière : "+filiere.getIdFiliere());
                filiere = new Filiere(id, nom);
                filieres.add(filiere);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filieres;

    }
}
