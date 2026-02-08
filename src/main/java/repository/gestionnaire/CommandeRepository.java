package repository.gestionnaire;

import database.Database;

import java.sql.Connection;

public class CommandeRepository {
    private Connection cnx;
    public CommandeRepository() {
        this.cnx = Database.getConnexion();;
    }


}

