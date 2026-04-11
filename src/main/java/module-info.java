module appli{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires javafx.base;
    requires java.desktop;
    requires jbcrypt;
    requires spring.security.crypto;
    requires javafx.graphics;
    requires jakarta.mail;
    requires jdk.jdi;
    //requires appli;
    //requires appli;


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

    opens appli.gestionnaire.commande to javafx.fxml;
    exports appli.gestionnaire.commande;

    opens appli.gestionnaire.fournisseur to javafx.fxml;
    exports appli.gestionnaire.fournisseur;

    opens appli.gestionnaire.fourniture to javafx.fxml;
    exports appli.gestionnaire.fourniture;

    opens appli.gestionnaire.fournitureFournisseur to javafx.fxml;
    exports appli.gestionnaire.fournitureFournisseur;

    opens appli.statistiques to javafx.fxml;
    exports appli.statistiques;

    opens session to javafx.fxml;
    exports session;

    opens appli.motDePasse to javafx.fxml;
    exports appli.motDePasse;

    opens model to javafx.fxml;
    exports model;

    opens model.gestionnaire to javafx.fxml;
    exports model.gestionnaire;

    opens appli.gestionnaire.commandeFourniture  to javafx.fxml;
    exports appli.gestionnaire.commandeFourniture;


}