package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;
import session.Session;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;


    @FXML
    void onConnexionClick(ActionEvent event) throws IOException {
        if(emailField.getText().isEmpty() || mdpField.getText().isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Veuillez saisir votre email et votre mot de passe.");

        }else{
            UtilisateurRepository userRepo = new UtilisateurRepository();
            Utilisateur possibleUser=userRepo.getUserByMail(new Utilisateur(emailField.getText(),mdpField.getText()));

            if(possibleUser!=null){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if(encoder.matches(mdpField.getText(), possibleUser.getMotDePasse())){
                    System.out.println("Connexion réussie pour : " + possibleUser.getNom());
                    Session.getInstance().sauvegardeSession(possibleUser);
                    StartApplication.changeScene("accueil/homePage","Accueil");
                }else{
                    showAlert(Alert.AlertType.WARNING, "Mot de passe ou Email  incorrect");
                }
            }else{
                showAlert(Alert.AlertType.ERROR, "Erreur lors de la connexion");
            }

        }
    }

    @FXML
    private void onMdpOublieClick() throws IOException {
        System.out.println("Mot de passe oublié cliqué");
        StartApplication.changeScene("motDePasse/saisieEmail","Mot de passe Oublié");
    }

    @FXML
    void onInscriptionClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/inscription", "Inscription");
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Connexion");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
