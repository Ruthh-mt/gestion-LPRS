package repository.gestionnaire;

import database.Database;

import java.sql.Connection;

public class FournitureRepository {
    private Connection cnx;
    public FournitureRepository() {
        this.cnx = Database.getConnexion();;
    }

}
