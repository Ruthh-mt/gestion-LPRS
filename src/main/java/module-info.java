module appli{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires javafx.base;
    requires java.desktop;
    requires jbcrypt;
    requires spring.security.crypto;

    opens appli to javafx.fxml;
    exports appli;

    opens appli.accueil to javafx.fxml;
    exports appli.accueil;

    opens appli.gestionnaire to javafx.fxml;
    exports appli.gestionnaire;

    opens appli.professeur to javafx.fxml;
    exports appli.professeur;

    opens appli.profil to javafx.fxml;
    exports appli.profil;

    opens appli.secretaire to javafx.fxml;
    exports appli.secretaire;

    opens model to javafx.base;


}