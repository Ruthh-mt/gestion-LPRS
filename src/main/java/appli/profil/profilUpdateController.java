package appli.profil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import session.Session;

import java.io.IOException;

public class profilUpdateController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private Label roleLabel;

    @FXML
    private Label erreurLabel;

    private Utilisateur userSession;
    private final UtilisateurRepository repo = new UtilisateurRepository();

    public void initData(Utilisateur utilisateur) {
        this.userSession = utilisateur;
        nomField.setText(utilisateur.getNom());
        prenomField.setText(utilisateur.getPrenom());
        emailField.setText(utilisateur.getEmail());
        roleLabel.setText(utilisateur.getRole());
    }

    @FXML
    void onEnregistrerClick(ActionEvent event) throws IOException {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            erreurLabel.setText("Tous les champs sont obligatoires.");
            erreurLabel.setVisible(true);
            return;
        }

        userSession.setNom(nom);
        userSession.setPrenom(prenom);
        userSession.setEmail(email);

        if (repo.mettreAJourUtilisateur(userSession)) {
            showAlert(Alert.AlertType.INFORMATION, "Profil mis à jour avec succès.");
            StartApplication.changeScene("profil/profilRead", "Profil");
        } else {
            erreurLabel.setText("Erreur lors de la mise à jour.");
            erreurLabel.setVisible(true);
        }
    }

    @FXML
    void onAnnulerClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("profil/profilRead", "Profil");
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Profil");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}