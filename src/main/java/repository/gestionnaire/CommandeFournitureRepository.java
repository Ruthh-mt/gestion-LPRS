package repository.gestionnaire;

import database.Database;

import java.sql.Connection;

public class CommandeFournitureRepository {

    private Connection cnx;
    public CommandeFournitureRepository() {
        this.cnx = Database.getConnexion();;
    }


}
