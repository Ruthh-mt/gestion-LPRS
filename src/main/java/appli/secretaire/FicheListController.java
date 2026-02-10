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
import session.Session;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FicheListController implements Initializable {


    public Button creerFicheBtn;
    public Button modiferFicheBtn;
    @FXML
    private TableView<FicheEtudiant> tableView;

    @FXML
    private Button redirectionAccueilBtn ;

    @FXML
    private javafx.scene.control.Button redirectionDossierBtn ;
    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();


    public void redirectionCreateFiche() throws IOException {
        StartApplication.changeScene("secretaire/ficheCreate","Créer une fiche");
    }
    public void redirectionUpdateFiche() throws IOException {
        StartApplication.changeScene("secretaire/ficheUpdate","Modifier la fiche");
    }
    public void redirectionAccueil() throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");
    }

    @FXML
    public void gestionListe() throws IOException {
        tableView.setOnMouseClicked(event -> {
            creerFicheBtn.setVisible(true);
            modiferFicheBtn.setVisible(true);
            if (event.getClickCount() == 2) {
                System.out.println("Bouton cliqué");
                FicheEtudiant fe = tableView.getSelectionModel().getSelectedItem();
                System.out.println("\nNom étudiant : " + fe.getNomEtudiant());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Id session :"+ Session.getInstance().getUtilisateur().getId());
        creerFicheBtn.setVisible(false);
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


