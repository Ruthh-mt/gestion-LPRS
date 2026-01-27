package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private void onConnexionClick() {
        String email = emailField.getText().trim();
        String mdp = mdpField.getText();

        if (email.isEmpty() || mdp.isEmpty()) {
            showAlert(AlertType.WARNING, "Veuillez saisir votre email et votre mot de passe.");
            return;
        }
        // Exemple simple : email et mot de passe hardcodés
        if (email.equalsIgnoreCase("admin@example.com") && mdp.equals("password")) {
            showAlert(AlertType.INFORMATION, "Connexion réussie !");
        } else {
            showAlert(AlertType.ERROR, "Email ou mot de passe incorrect.");
        }
    }

    @FXML
    private void onMdpOublieClick() {
        System.out.println("Mot de passe oublié cliqué");
    }

    @FXML
    void onInscriptionClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/inscription");
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Connexion");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
