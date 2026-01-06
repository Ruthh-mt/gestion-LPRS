package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InscriptionController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpConfirmationField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    void onInscriptionClick(ActionEvent event) {

    }

    @FXML
    void onConnexionClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/login");
    }

}