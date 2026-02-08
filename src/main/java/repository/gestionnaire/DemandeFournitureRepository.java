package repository.gestionnaire;

import database.Database;

import java.sql.Connection;

public class DemandeFournitureRepository {
    private Connection cnx;
    public DemandeFournitureRepository() {
        this.cnx = Database.getConnexion();;
    }
}
