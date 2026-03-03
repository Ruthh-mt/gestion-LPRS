package appli.gestionnaire.fournitureFournisseur;

import appli.StartApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.gestionnaire.Fourniture;
import model.gestionnaire.FournitureFournisseur;
import repository.gestionnaire.FournitureFournisseurRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowFournisseurDeFournitureController implements Initializable {

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
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/addFournisseurView.fxml","Ajouter un fournisseur");
        AddFournisseurController controller =(AddFournisseurController)
                StartApplication.getControllerFromStage();
//                    controller.initData(fournitureSel);
    }

    @FXML
    void onFournisseurDeFournitureTableViewClicked() {

    }

    @FXML
    void onSupprimerFournisseurdeFourniture() {

    }

    @FXML
    void onRetourControlCenter() throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");
    }


    public Fourniture getFournitureSel() {
        return fournitureSel;
    }

    public void setFournitureSel(Fourniture fournitureSel) {
        this.fournitureSel = fournitureSel;
    }


}
