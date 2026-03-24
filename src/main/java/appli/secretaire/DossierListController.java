package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DossierInscription;
import model.FicheEtudiant;
import model.Utilisateur;
import repository.DossierRepository;
import session.Session;
import session.SessionDossier;
import session.SessionFiche;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DossierListController implements Initializable {

    public Button redirectionDossierReadBtn;
    @FXML
    TableView<DossierInscription> tableviewDossier;
    @FXML
    private  Button accueilBtn;
    @FXML
    private Button modifierDossierBtn;

    @FXML
    private Button supprimerDossierBtn;

    @FXML
    private Label sessionLabel ;

    @FXML
    private Button ajouterDossierBtn;

    @FXML
    private Button deleteDossierBtn;
    @FXML


    Utilisateur userSession = Session.getInstance().getUtilisateur();
    DossierRepository dossierRepository = new DossierRepository();
    DossierInscription dossierActuel = null;

    @FXML
    public void redirectionAccueil() throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");
    }

    @FXML
    public void redirectionDossierRead() throws IOException, SQLException {
        DossierInscription di = tableviewDossier.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource("secretaire/dossierRead" + "View.fxml"));
        Parent root = fxmlLoader.load();
        DossierReadController dossierReadController = fxmlLoader.getController();
        dossierReadController.initData(di);
        Stage mainStage = (Stage) tableviewDossier.getScene().getWindow();

        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    @FXML
    public void redirectionAjouterDossier() throws IOException {
        StartApplication.changeScene("secretaire/dossierCreate","Dossier");
    }
    @FXML
    public void redirectionUpdateDossier() throws IOException, SQLException {
        DossierInscription di = tableviewDossier.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource("secretaire/dossierUpdate" + "View.fxml"));
        Parent root = fxmlLoader.load();
        DossierUpdateController dossierUpdateController = fxmlLoader.getController();
        dossierUpdateController.initData(di);
        Stage mainStage = (Stage) tableviewDossier.getScene().getWindow();

        mainStage.setScene(new Scene(root));
        mainStage.show();

    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionLabel.setText("Session de " + userSession.getPrenom() + " " + userSession.getNom());
        modifierDossierBtn.setVisible(false);
        supprimerDossierBtn.setVisible(false);
        ajouterDossierBtn.setVisible(false);
        redirectionDossierReadBtn.setVisible(false);
        if(userSession.getRole().equals("Secrétaire")){
            ajouterDossierBtn.setVisible(true);
        }
        else{
            ajouterDossierBtn.setVisible(false);
        }
        this.sessionLabel.setText("Session de " + userSession.getPrenom() + " " + userSession.getNom());
        String[][] colonnes = {
                {"Date", "date"},
                {"Heure", "heure"},
                {"Motivation", "motivation"},
                {"Nom", "nomEtudiant"},
                {"Prenom", "prenomEtudiant"} ,
                {"Filière","nomFiliere"}
        };

        for (int i = 0; i < colonnes.length; i++) {
            if (colonnes[i][0].equals("Ref filiere")) {
                TableColumn<DossierInscription, Integer> maCol = new TableColumn<>(colonnes[i][0]);
                maCol.setCellValueFactory(new PropertyValueFactory<>("refFiliere"));
                tableviewDossier.getColumns().add(maCol);
            }

            else {
                TableColumn<DossierInscription, String> maCol = new TableColumn<>(colonnes[i][1]);
                maCol.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
                tableviewDossier.getColumns().add(maCol);
            }
        }

        ArrayList<DossierInscription> lesDossiers = null;
        int refUser = Session.getInstance().getUtilisateur().getIdUtilisateur();
        try {
            lesDossiers = dossierRepository.getAllDossiersSecretaire(refUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (DossierInscription d : lesDossiers) {
            tableviewDossier.getItems().add(d);
        }
    }
    @FXML
    public void gestionListe() throws IOException {

        tableviewDossier.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {
                DossierInscription di = tableviewDossier.getSelectionModel().getSelectedItem();
                if ((di != null) && userSession.getRole().equals( "Secrétaire") ) {
                    modifierDossierBtn.setVisible(true);
                    supprimerDossierBtn.setVisible(true);
                    redirectionDossierReadBtn.setVisible(true);
                    dossierActuel = di;
                }
            }
        });
    }
    @FXML
    public void supprimerDossier() throws SQLException, IOException {
         boolean ok = dossierRepository.supprimerDossier(dossierActuel);
         if(ok){
             System.out.println("Dossier supprimé");
             StartApplication.changeScene("secretaire/dossierList","listeDossier");
         }
    }
}
