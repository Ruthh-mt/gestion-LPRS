package appli.gestionnaire.fournitureFournisseur;

import appli.StartApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.FournitureFournisseur;
import repository.gestionnaire.FournitureFournisseurRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowFournitureDUnFournisseurController implements Initializable {

    @FXML
    private TableView<FournitureFournisseur> fournitureDeFournisseurTableView;

    @FXML
    private Label TitleLabel;

    @FXML
    private Button supprimerFournitureDUnFournisseur;

    private Fournisseur fourniseurSel;

    public void initData(Fournisseur fourniseur){
        fourniseurSel = fourniseur;
        FournitureFournisseurRepository fournitureFournisseurRepo= new FournitureFournisseurRepository();
        ObservableList<FournitureFournisseur> allFournitures= FXCollections.observableList(fournitureFournisseurRepo.getAllFournitureByFournisseursId(fourniseurSel));
        fournitureDeFournisseurTableView.getItems().setAll(allFournitures);
        TitleLabel.setText("Liste des fournisseurs pour : "+fourniseurSel.getNomfournisseur());

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
            fournitureDeFournisseurTableView.getColumns().add(maCol);
            fournitureDeFournisseurTableView.getItems().clear();
        }
    }

    @FXML
    void onAjouterUneFournitureAFournisseur(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/addFournisseurPourFourniture","Ajouter un fournisseur");
        AddFourniturePourFournisseurController controller =(AddFourniturePourFournisseurController)
                StartApplication.getControllerFromStage();
        controller.initData(fourniseurSel);

    }

    @FXML
    void onFournitureDeFournisseurTableViewClicked (MouseEvent event) throws IOException, SQLException {
        FournitureFournisseur selection = fournitureDeFournisseurTableView.getSelectionModel().getSelectedItem();
        System.out.println( selection);
        if (event.getClickCount() == 2) {
            if (selection != null) {
                StartApplication.changeScene("gestionnaire/fournitureFournisseur/updateFournitureFournisseur", "Modification");
                UpdateFournitureFournisseurController controller = (UpdateFournitureFournisseurController)
                        StartApplication. getControllerFromStage();
                controller.initialize(selection);
            }
        }else if(selection!=null) {
            supprimerFournitureDUnFournisseur.setDisable(false);
        }
    }

    @FXML
    void onRetourControlCenter(ActionEvent event) {

    }

    @FXML
    void onSupprimerFournitureDUnFournisseur(ActionEvent event) {
        FournitureFournisseurRepository fournitureFournisseurRepo = new FournitureFournisseurRepository();
        FournitureFournisseur selection = fournitureDeFournisseurTableView.getSelectionModel().getSelectedItem();
        boolean success=fournitureFournisseurRepo.supprimerFournitureFournisseur(selection);
        if (success) {
            System.out.println("Suppression fourniture fournisseur reussi ");
            showAlert(Alert.AlertType.INFORMATION,"Suppression de la fourniture : "+selection.getRefFourniture().getLibelle()+"au fournisseur "+fourniseurSel.getNomfournisseur() ,"La suppression est reussi");
        }else{
            showAlert(Alert.AlertType.ERROR,"Suppression de la fourniture : "+selection.getRefFourniture().getLibelle()+"au fournisseur "+fourniseurSel.getNomfournisseur() ,"Erreur lors de la suppression");
        }
    }
    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}