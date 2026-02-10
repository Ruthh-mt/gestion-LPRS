package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FicheEtudiant;
import repository.FicheEtudiantRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FicheListController implements Initializable {
    public javafx.scene.control.Button modiferFicheBtn;
    public javafx.scene.control.Button supprimerFicheBtn;
    @FXML
    private TableView<FicheEtudiant> tableView;

    @FXML
    private Button redirectionAccueilBtn ;

    @FXML
    private javafx.scene.control.Button redirectionDossierBtn ;
    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[][] colonnes = {
                {"Nom", "nomEtudiant"},
                {"Prénom", "prenomEtudiant"},
                {"Email", "emailEtudiant"},
                {"Téléphone", "telephoneEtudiant"},
                {"Adresse", "adresseEtudiant"},
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
    public void redirectionCreateFiche() throws IOException {
        StartApplication.changeScene("secretaire/ficheCreate","Créer une fiche");
    }
    public void redirectionAccueil() throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");
    }




}


