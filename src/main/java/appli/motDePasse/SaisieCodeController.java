package appli.motDePasse;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.io.IOException;

public class SaisieCodeController {
    private String mailUser;

    @FXML
    private TextField codeField;

    public void initData(String mail) {
        this.mailUser = mail;
    }

    @FXML
    void onCodeConfirmer(ActionEvent event) throws IOException {
        if(codeField.getText().isEmpty()) {
            showAlert();
        }else {
            UtilisateurRepository userRepo = new UtilisateurRepository();
            String codeBDD=userRepo.getCode(mailUser);
            if (codeField.getText().equals(codeBDD)) {
                StartApplication.changeScene("motDePasse/changePassword","Mot de passe Oublie");
                ChangePasswordController controller = (ChangePasswordController)
                        StartApplication.getControllerFromStage();
                controller.initData(mailUser);
            }
        }

    }

    @FXML
    void onRetourLogin(ActionEvent event) throws IOException {
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
