package repository;

import database.Database;
import model.FicheEtudiant;
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
    public Filiere getFiliere(int id) throws SQLException {

        int id_filiere = 0 ;
        String nom = "";
        Filiere filiere = null ;

        String sql = "SELECT * FROM filiere WHERE id_filiere = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            id_filiere = rs.getInt("id_filiere");
            nom = rs.getString("nom");
            filiere = new Filiere(id_filiere, nom);
        }
        return filiere;

    }
}
