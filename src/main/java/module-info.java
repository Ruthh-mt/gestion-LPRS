module appli{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens appli to javafx.fxml;
    exports appli;
    exports appli.accueil;
    opens appli.accueil to javafx.fxml;
}