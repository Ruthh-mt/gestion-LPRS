package repository;

import database.Database;
import model.DossierInscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DossierRepository {
    private Connection connection;


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


