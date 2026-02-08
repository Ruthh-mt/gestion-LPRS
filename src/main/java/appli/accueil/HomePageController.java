package appli.accueil;
import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import org.mindrot.jbcrypt.BCrypt;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import java.io.IOException;
import javafx.event.ActionEvent;

public class HomePageController {

    @FXML
    void onDashboardClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire", "Control Center LOL");
    }

    @FXML
    void onDeconnexionClick(ActionEvent event) {

    }

    @FXML
    void onDossierClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("secretaire/dossierList", "Dossier étudiant");
    }

    @FXML
    void onFicheClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("secretaire/ficheList", "Fiches personnelles");
    }

    @FXML
    void onFournitureClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("professeur/demandeCreate", "Nouvelle demande");
    }

    @FXML
    void onPlanningClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("professeur/planning", "Planning");
    }

    @FXML
    void onProfilClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("profil/profilRead", "Profil");
    }

}
