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
import model.FicheEtudiant;
import model.Utilisateur;
import repository.FicheEtudiantRepository;
import session.Session;
import session.SessionFiche;
import appli.secretaire.FicheUpdateController ;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FicheListController implements Initializable {


    @FXML
    private Button creerFicheBtn;
    @FXML
    private Button modiferFicheBtn;
    @FXML
    private TableView<FicheEtudiant> tableView;

    @FXML
    private Label sessionLabel;
    @FXML
    private Button redirectionAccueilBtn ;

    @FXML
    private javafx.scene.control.Button redirectionDossierBtn ;

    @FXML
    private Button ficheReadBtn;



    private FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    @FXML
    private Button supprimerFicheBtn;

    @FXML
    private boolean suppression = false;


    Utilisateur userActuel = Session.getInstance().getUtilisateur();
    FicheEtudiant ficheActuel = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.sessionLabel.setText("Session de "+userActuel.getPrenom()+" "+Session.getInstance().getUtilisateur().getNom());
        modiferFicheBtn.setVisible(false);
        supprimerFicheBtn.setVisible(false);
        creerFicheBtn.setVisible(false);
        redirectionDossierBtn.setVisible(false);
        ficheReadBtn.setVisible(false);
        if(userActuel.getRole().equals("Secrétaire")){
            creerFicheBtn.setVisible(true);
            redirectionDossierBtn.setVisible(true);
        }


        String[][] colonnes = {
                {"Nom", "nomEtudiant"},
                {"Prénom", "prenomEtudiant"},
                {"Email", "emailEtudiant"},
                {"Dernier diplôme", "dernierDiplome"}
        };

        for (String[] col : colonnes) {
            TableColumn<FicheEtudiant, String> column =
                    new TableColumn<>(col[0]);
            column.setCellValueFactory(
                    new PropertyValueFactory<>(col[1])
            );
            tableView.getColumns().add(column);
        }
        int refUser = Session.getInstance().getUtilisateur().getIdUtilisateur();
        try {
            tableView.getItems().setAll(
                    ficheEtudiantRepository.getToutesLesFiches(refUser)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(suppression){
            tableView.refresh();
        }
    }
@FXML
    public void redirectionCreateFiche() throws IOException {
        StartApplication.changeScene("secretaire/ficheCreate","Créer une fiche");
    }

    @FXML
    public void redirectionUpdateFiche() throws IOException, SQLException {
        FicheEtudiant fe = tableView.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource("secretaire/ficheUpdate" + "View.fxml"));
        Parent root = fxmlLoader.load();
        FicheUpdateController ficheUpdateController = fxmlLoader.getController();
        ficheUpdateController.initData(fe);
        Stage mainStage = (Stage) tableView.getScene().getWindow();

        mainStage.setScene(new Scene(root));
        mainStage.show();

    }
    @FXML
    public void redirectionAccueil() throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");
    }

    @FXML
    public void redirectionDossierBtn() throws IOException {
    StartApplication.changeScene("secretaire/dossierList","Dossier");
    }

    @FXML
    public void redirectionFicheRead() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource("secretaire/ficheRead" + "View.fxml"));
        Parent root = fxmlLoader.load();
        FicheReadController ficheReadController = fxmlLoader.getController();
        ficheReadController.initData(ficheActuel);
        Stage mainStage = (Stage) ficheReadBtn.getScene().getWindow();

        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

    @FXML
    public void gestionListe() throws IOException {

        tableView.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {

                FicheEtudiant fe = tableView.getSelectionModel().getSelectedItem();

                if (fe != null && userActuel.getRole().equals("Secrétaire")) {
                    System.out.println("Double clic sur : " + fe.getNomEtudiant());
                    System.out.println("Secrétaire : " + userActuel.getPrenom()+" "+userActuel.getNom());
                    SessionFiche.getInstance().sauvegardeSession(fe);
                    ficheActuel = fe;
                    modiferFicheBtn.setVisible(true);
                    supprimerFicheBtn.setVisible(true);
                    ficheReadBtn.setVisible(true);
                }
            }
        });
    }



    @FXML
    public void supprimerFiche() throws SQLException, IOException {
        ficheEtudiantRepository.deleteFicheEtudiant(ficheActuel.getIdFicheEtudiante());
     }
}


