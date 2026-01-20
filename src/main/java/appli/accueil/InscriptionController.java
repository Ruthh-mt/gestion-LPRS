package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class InscriptionController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private PasswordField mdpConfirmationField;

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @FXML
    public void initialize() {
        // Remplir le menu déroulant des rôles
        roleChoiceBox.getItems().addAll("Professeur", "Secrétaire", "Gestionnaire");
        roleChoiceBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onInscriptionClick() {
        // Validation simple des champs
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();
        String mdp = mdpField.getText();
        String mdpConfirm = mdpConfirmationField.getText();
        String role = roleChoiceBox.getValue();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || mdpConfirm.isEmpty()) {
            showAlert(AlertType.WARNING, "Tous les champs doivent être remplis.");
            return;
        }

        if (!mdp.equals(mdpConfirm)) {
            showAlert(AlertType.ERROR, "Les mots de passe ne correspondent pas.");
            return;
        }

        // TODO: Ajouter logique d'inscription (ex: appel service, base de données)

        showAlert(AlertType.INFORMATION, "Inscription réussie pour le rôle : " + role);
        clearForm();
    }

    @FXML
    void onConnexionClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/login");
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Inscription");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        mdpField.clear();
        mdpConfirmationField.clear();
        roleChoiceBox.getSelectionModel().selectFirst();
    }
}
