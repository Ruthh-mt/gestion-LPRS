module appli.gestionlprsfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens appli.gestionlprsfx to javafx.fxml;
    exports appli.gestionlprsfx;
}