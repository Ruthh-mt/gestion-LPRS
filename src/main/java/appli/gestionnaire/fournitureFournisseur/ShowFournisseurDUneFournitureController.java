package appli.gestionnaire.fournitureFournisseur;

import appli.StartApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.Fourniture;
import model.gestionnaire.FournitureFournisseur;
import repository.gestionnaire.FournisseurRepository;
import repository.gestionnaire.FournitureFournisseurRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowFournisseurDUneFournitureController implements Initializable {

    private Fourniture fournitureSel;
    @FXML
    private TableView<FournitureFournisseur> fournisseurDeFournitureTableView;

    @FXML
    private Label TitleLabel;

    @FXML
    private Button supprimerFournisseurdeFourniture;

    public void initData(Fourniture fourniture){
        fournitureSel = fourniture;
        FournitureFournisseurRepository fournitureFournisseurRepo= new FournitureFournisseurRepository();
        ObservableList<FournitureFournisseur> allFournisseurs= FXCollections.observableList(fournitureFournisseurRepo.getAllFournisseursByFournitureId(fournitureSel.getIdFourniture()));
        fournisseurDeFournitureTableView.getItems().setAll(allFournisseurs);
        TitleLabel.setText("Liste des fournisseurs pour : "+fournitureSel.getLibelle());

    }

    public void initialize(URL location, ResourceBundle resources){
        String [][] colonnes = {
                {"Fourniture","refFourniture"},
                {"Fournisseur","refFournisseur"},
                {"Prix","prix"}
        };
        for (String[] colonne : colonnes) {
            //Création de la colonne avec le titre
            TableColumn<FournitureFournisseur, String> maCol = new TableColumn<>(colonne[0]);
//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<>(colonne[1]));
            //Ajout de la colonne dans notre tableau
            fournisseurDeFournitureTableView.getColumns().add(maCol);
            fournisseurDeFournitureTableView.getItems().clear();
        }
    }


    @FXML
    void onAjouterUnFournisseurAFourniture() throws IOException {
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/addFournisseurPourFourniture","Ajouter un fournisseur");
        AddFournisseurPourFournitureController controller =(AddFournisseurPourFournitureController)
                StartApplication.getControllerFromStage();
                   controller.initData(fournitureSel);
    }

    @FXML
    void onFournisseurDeFournitureTableViewClicked(MouseEvent event) throws IOException {
        FournitureFournisseur selection = fournisseurDeFournitureTableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            if (selection != null) {
                StartApplication.changeScene("gestionnaire/fourniture/updateFournitureFournisseur", "Modification");
                UpdateFournitureFournisseurController controller = (UpdateFournitureFournisseurController)
                        StartApplication. getControllerFromStage();
                controller.initData(selection);
            }
        }else if(selection!=null) {
            supprimerFournisseurdeFourniture.setDisable(false);
        }

    }

    @FXML
    void onSupprimerFournisseurdeFourniture() {

    }

    @FXML
    void onRetourControlCenter() throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Ajout d'un Fournisseur pour "+ fournitureSel.getLibelle());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
