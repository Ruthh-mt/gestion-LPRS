package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private Label erreurLabel;

    @FXML
    private PasswordField mdpField;

    @FXML
    void onConnexionClick(ActionEvent event) {

    }

    @FXML
    void onInscriptionClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/inscription");

    }

    @FXML
    void onMdpOublieClick(ActionEvent event) {


    }

}
