package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DossierInscription;
import model.FicheEtudiant;
import model.Utilisateur;
import repository.DossierRepository;
import session.Session;
import session.SessionFiche;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DossierListController implements Initializable {

    @FXML
    TableView<DossierInscription> tableviewDossier;
    @FXML
    private  Button accueilBtn;
    @FXML
    private Button modifierDossierBtn;

    @FXML
    private Button supprimerDossierBtn;

    @FXML
    private TableView<DossierInscription> tableview;

    @FXML
    private Label sessionLabel ;

    Utilisateur userSession = Session.getInstance().getUtilisateur();
    DossierRepository dossierRepository = new DossierRepository();

    @FXML
    public void redirectionAccueil() throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");
    }

    @FXML
    public void redirectionAjouterDossier() throws IOException {
        StartApplication.changeScene("secretaire/dossierCreate","Dossier");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     sessionLabel.setText("Session de "+userSession.getPrenom()+" "+userSession.getNom());
     modifierDossierBtn.setVisible(false);
     supprimerDossierBtn.setVisible(false);
        this.sessionLabel.setText("Session de "+Session.getInstance().getUtilisateur().getPrenom()+" "+Session.getInstance().getUtilisateur().getNom());
        System.out.println("Id session :"+ Session.getInstance().getUtilisateur().getId());
        modifierDossierBtn.setVisible(false);
        supprimerDossierBtn.setVisible(false);
        String[][] colonnes = {
                {"Date", "date"},
                {"Heure", "heure"},
                {"Motivation", "motivation"},
                {"Etudiant", ""},

        };

        for (String[] col : colonnes) {
            TableColumn<DossierInscription, String> column =
                    new TableColumn<>(col[0]);
            column.setCellValueFactory(
                    new PropertyValueFactory<>(col[1])
            );
            tableviewDossier.getColumns().add(column);
        }
        try {
            tableviewDossier.getItems().setAll(
                   dossierRepository.getAllDossiers()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void gestionListe() throws IOException {

        tableview.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {

                DossierInscription di = tableview.getSelectionModel().getSelectedItem();

                if (di != null) {
                    System.out.println("Double clic sur : " + di.toString());

                    //SessionFiche.getInstance().sauvegardeSession(di);
                    modifierDossierBtn.setVisible(true);
                    supprimerDossierBtn.setVisible(true);
                }
            }
        });
    }
}
