package repository.gestionnaire;

import database.Database;

import java.sql.Connection;

public class FournitureFournisseurRepository {
    private Connection cnx;
    public FournitureFournisseurRepository() {
        this.cnx = Database.getConnexion();;
    }
}
