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
    private void onConnexionClick() throws IOException {
        String email = emailField.getText().trim();
        String mdp = mdpField.getText();

        if (email.isEmpty() || mdp.isEmpty()) {
            showAlert(AlertType.WARNING, "Veuillez saisir votre email et votre mot de passe.");
            return;
        }
        else{ // si les champs email et mdp ne sont pas vide
            UtilisateurRepository userRepository = new UtilisateurRepository();
            boolean userExiste=userRepository.emailExiste(email);
            if(userExiste){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String mdpBdd= userRepository.getPasswordbyEmail(email);
                if(encoder.matches(mdp,mdpBdd)){
                    StartApplication.changeScene("accueil/homePage", "Accueil");
                    Utilisateur userTrouve = userRepository.getUser(email,mdp);
                    Session.getInstance().sauvegardeSession(userTrouve);
                } else{
                    showAlert(AlertType.ERROR, "Email ou mot de passe incorrect.");
                    return;
                }

            }else{
                System.out.println("Vous n'existez pas chez nous, veuillez vous inscrire");
                showAlert(AlertType.WARNING, "Vous n'existez pas chez nous, veuillez vous inscrire");
                return;
            }

        }

    }

    @FXML
    private void onMdpOublieClick() {
        System.out.println("Mot de passe oublié cliqué");
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
