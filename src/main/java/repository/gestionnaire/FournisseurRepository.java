package repository.gestionnaire;

import database.Database;

import java.sql.Connection;

public class FournisseurRepository {
    private Connection cnx;
    public FournisseurRepository() {
        this.cnx = Database.getConnexion();;
    }
}
