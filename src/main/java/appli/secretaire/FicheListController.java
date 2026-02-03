package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FicheEtudiant;
import repository.FicheEtudiantRepository;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FicheListController implements Initializable {
    public javafx.scene.control.Button modiferFicheBtn;
    public javafx.scene.control.Button supprimerFicheBtn;
    @FXML
    private TableView<FicheEtudiant> tableView = new TableView<>();

    @FXML
    private Button redirectionAccueilBtn ;

    @FXML
    private javafx.scene.control.Button redirectionDossierBtn ;
    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String[][] colonnes = {
                {"Nom de l'étudiant", "nomEtudiant"},
                {"Prénom", "prenomEtudiant"},
                {"Email", "emailEtudiant"},
                {"Téléphone", "telephoneEtudiant"},
                {"Adresse", "adresseEtudiant"},
                {"Dernier diplome", "dernierDiplome"}
        };

        for (int i = 0; i < colonnes.length; i++) {
                TableColumn<FicheEtudiant, String> maCol = new TableColumn<>(colonnes[i][1]);
                maCol.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
                tableView.getColumns().add(maCol);
            }

        ArrayList<FicheEtudiant> fiches = null;
        try {
            fiches = ficheEtudiantRepository.getToutesLesFiches();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (FicheEtudiant f : fiches) {
            tableView.getItems().add(f);
        }
        modiferFicheBtn.setDisable(true);
        supprimerFicheBtn.setDisable(true);
    }

    @FXML
    public void gestionListe() throws IOException {
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                FicheEtudiant ficheEtudiant = tableView.getSelectionModel().getSelectedItem();
                modiferFicheBtn.setDisable(false);
                supprimerFicheBtn.setDisable(false);


            }
        });
    }




}


