package appli.accueil;
import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import session.Session;

public class HomePageController {
    private Session sessionUser = Session.getInstance();
    // button gestionnaire
    @FXML
    private Button dashboardBtn;
    // btn secretaire
    @FXML
    private Button dossierInscriptionBtn;

    @FXML
    private Button ficheEtudianteBtn;
    // btn professeur
    @FXML
    private Button fournitureBtn;

    @FXML
    private Button planningBtn;

    @FXML
    public void initialize(){
        String userRole =sessionUser.getUtilisateur().getRole();
        if("Professeur".equals(userRole)){
            dashboardBtn.setDisable(true);
            dossierInscriptionBtn.setDisable(true);
            planningBtn.setDisable(true);
        }
        else if("Secrétaire".equals(userRole)){
            dashboardBtn.setDisable(true);
            fournitureBtn.setDisable(true);
            planningBtn.setDisable(true);
        }
        else if("Gestionnaire".equals(userRole)){
            planningBtn.setDisable(true);
            dossierInscriptionBtn.setDisable(true);
            ficheEtudianteBtn.setDisable(true);
            fournitureBtn.setDisable(true);
        }
    }

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

    @FXML
    void onStatistiquesClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("statistiques/statistiques", "Statistiques");
    }

}
