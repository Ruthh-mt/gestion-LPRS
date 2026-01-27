package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import java.io.IOException;

public class InscriptionController {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField mdpConfirmation;
    @FXML
    private ChoiceBox<String> role;

    private final UtilisateurRepository repo = new UtilisateurRepository();

    @FXML
    public void initialize() {
        role.getItems().addAll("Professeur", "Secrétaire", "Gestionnaire");
        role.getSelectionModel().selectFirst();
    }

    @FXML
    private void onInscriptionClick() {

        String nomTxt = nom.getText().trim();
        String prenomTxt = prenom.getText().trim();
        String emailTxt = email.getText().trim();
        String mdpTxt = mdp.getText();
        String mdpConfirmTxt = mdpConfirmation.getText();
        String roleTxt = role.getValue();

        if (nomTxt.isEmpty() || prenomTxt.isEmpty() || emailTxt.isEmpty()
                || mdpTxt.isEmpty() || mdpConfirmTxt.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Tous les champs doivent être remplis.");
            return;
        }

        if (!mdpTxt.equals(mdpConfirmTxt)) {
            showAlert(Alert.AlertType.ERROR, "Les mots de passe ne correspondent pas.");
            return;
        }

        if (repo.emailExiste(emailTxt)) {
            showAlert(Alert.AlertType.ERROR, "Cet email est déjà utilisé.");
            return;
        }

        Utilisateur user = new Utilisateur(
                nomTxt,
                prenomTxt,
                emailTxt,
                mdpTxt,
                roleTxt
        );

        if (repo.inscrire(user)) {
            showAlert(Alert.AlertType.INFORMATION, "Inscription réussie");
            clearForm();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur lors de l'inscription");
        }
    }

    @FXML
    void onConnexionClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/login");
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Inscription");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        nom.clear();
        prenom.clear();
        email.clear();
        mdp.clear();
        mdpConfirmation.clear();
        role.getSelectionModel().selectFirst();
    }
}