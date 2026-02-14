package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FicheEtudiant;
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
    private FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();


@FXML
    public void redirectionCreateFiche() throws IOException {
        StartApplication.changeScene("secretaire/ficheCreate","Créer une fiche");
    }
    @FXML
    public void redirectionUpdateFiche() throws IOException, SQLException {
        StartApplication.changeScene("secretaire/ficheUpdate","Modifier la fiche");
        FicheEtudiant fe = tableView.getSelectionModel().getSelectedItem();
        FicheUpdateController ficheUpdateController = new FicheUpdateController();
        ficheUpdateController.initData(fe);
    }
    @FXML
    public void redirectionAccueil() throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");
    }

    @FXML
    public void gestionListe() throws IOException {

        tableView.setOnMouseClicked(event -> {

            if (event.getClickCount() == 2) {

                FicheEtudiant fe = tableView.getSelectionModel().getSelectedItem();

                if (fe != null) {
                    System.out.println("Double clic sur : " + fe.getNomEtudiant());

                    SessionFiche.getInstance().sauvegardeSession(fe);
                    modiferFicheBtn.setVisible(true);
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.sessionLabel.setText("Session de "+Session.getInstance().getUtilisateur().getPrenom()+" "+Session.getInstance().getUtilisateur().getNom());
        System.out.println("Id session :"+ Session.getInstance().getUtilisateur().getId());
        modiferFicheBtn.setVisible(false);
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

        try {
            tableView.getItems().setAll(
                    ficheEtudiantRepository.getToutesLesFiches()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}


