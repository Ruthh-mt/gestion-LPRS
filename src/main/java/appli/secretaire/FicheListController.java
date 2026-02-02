package appli.secretaire;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FicheEtudiant;
import repository.FicheEtudiantRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FicheListController implements Initializable {
    @FXML
    private TableView<FicheEtudiant> tableView;

    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[][] colonnes = {
                {"Numero de fiche", "id_fiche_etudiante"},
                {"Identifiant du créateur", "ref_createur"},
                {"Nom de l'étudiant", "nom_etudiant"},
                {"Prénom", "prenom_etudiant"},
                {"Email", "email_etudiant"},
                {"Téléphone", "telephone"},
                {"Adresse", "adresse"},
                {"Dernier diplome", "dernierDiplome"}
        };

        for (int i = 0; i < colonnes.length; i++) {
            if (colonnes[i][0].equals("Numero de fiche")) {
                TableColumn<FicheEtudiant, Integer> maCol = new TableColumn<>(colonnes[i][0]);
                maCol.setCellValueFactory(new PropertyValueFactory<>("id_fiche_etudiante"));
                tableView.getColumns().add(maCol);

            }
            else if (colonnes[i][0].equals("ref_createur")) {
                TableColumn<FicheEtudiant, Integer> maCol = new TableColumn<>(colonnes[i][0]);
                maCol.setCellValueFactory(new PropertyValueFactory<>("ref_createur"));
                tableView.getColumns().add(maCol);

            }
            else {
                TableColumn<FicheEtudiant, String> maCol = new TableColumn<>(colonnes[i][1]);
                maCol.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
                tableView.getColumns().add(maCol);
            }
        }
        ArrayList<FicheEtudiant> lesFiches = null;
        try {
            lesFiches = ficheEtudiantRepository.getToutesLesFiches();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (FicheEtudiant l : lesFiches) {
            tableView.getItems().add(l);
        }
    }
}
