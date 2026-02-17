package appli.motDePasse;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;

import java.io.IOException;

public class ChangePasswordController {
    private String mailUser;

    @FXML
    private PasswordField confirmationMdpField;

    @FXML
    private PasswordField mdpField;


    public void initData(String mail) {
        this.mailUser = mail;
    }

    @FXML
    void onChangePassword(ActionEvent event) throws IOException {
        if(mdpField.getText().isEmpty() || confirmationMdpField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Veuillez remplir tout les champs");
        } else if (!confirmationMdpField.getText().equals(mdpField.getText())) {
            showAlert(Alert.AlertType.ERROR, "Mot de passe et confirmation differents");
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            UtilisateurRepository userRepo = new UtilisateurRepository();
            Utilisateur user=new Utilisateur(mailUser,encoder.encode(mdpField.getText()));
            if(userRepo.mettreAJourMdp(user)){
                System.out.println("Modification mdp terminé success");
                userRepo.deleteCode(mailUser);
                StartApplication.changeScene("accueil/login","Connexion");
            }else{
                showAlert(Alert.AlertType.ERROR, "Erreur lors de la modification");
            }

        }

    }

    @FXML
    void onRetourLogin(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/login", "Connexion");

    }
    private void showAlert(Alert.AlertType alertType , String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Mot de passe Oublie");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
