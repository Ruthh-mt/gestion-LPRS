package appli.motDePasse;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SaisieEmailController {

    @FXML
    private TextField emailField;

    @FXML
    void onMailConfirmer() throws IOException {
        if(emailField.getText().isEmpty()){
            showAlert();
        }else{
            StartApplication.changeScene("motDePasse/messagePop", "Pour Information");
            MessagePopController controller = (MessagePopController)
                    StartApplication.getControllerFromStage();
            controller.initData(emailField.getText());
        }
    }

    @FXML
    void onRetourLogin() throws IOException {
        StartApplication.changeScene("accueil/login", "Connexion");

    }
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Mot de passe Oublie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tout les champs");
        alert.showAndWait();
    }

}