package appli.gestionnaire;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import appli.StartApplication;

import java.io.IOException;

public class AccueilGestionnaireController {

    @FXML
    private Label commandeEnCours;

    @FXML
    private TableView<?> commandeTableView;

    @FXML
    private Label demandeEnCours;

    @FXML
    private Label nbFournitureVide;

    @FXML
    void onNewCommand(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/createCommande", "Commande");

    }

    @FXML
    void onShowPastCommand(ActionEvent event) {

    }

}
