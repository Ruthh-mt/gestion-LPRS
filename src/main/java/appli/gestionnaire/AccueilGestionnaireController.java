package appli.gestionnaire;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.gestionnaire.Commande;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.Fourniture;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccueilGestionnaireController  implements Initializable {

    @FXML
    private Label commandeEnCours;

    @FXML
    private TableView<Commande> commandeTableView;

    @FXML
    private Label demandeEnCours;

    @FXML
    private TableView<Fournisseur> fournisseurTableView;

    @FXML
    private TableView<Fourniture> fournitureTableView;

    @FXML
    private Label nbFournitureVide;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String [][] colonnes = {
                {"id Commande","idCommande"},
                {"Nom Commande","nomCommande"},
                {"Fait par","refFournisseur"},
                {"Commandé chez",""},
                {"Commande faite le ","dateCommande"}
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Commande,String> maCol = new TableColumn<>(colonnes[i][0]);
//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Commande,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            commandeTableView.getColumns().add(maCol);
        }
    }

    @FXML
    void onCommandeChosed(ActionEvent event) {

    }


    @FXML
    void onFournisseurChosed(ActionEvent event,URL location, ResourceBundle resources) {

        String [][] colonnes = {
                { "Id Forunisseur","idFournisseur" },
                { "Nom","nomfournisseur" },
                { "Mail","mailFournisseur" },
                { "Telephone","telephoneFournisseur" },
                { "Frais de livraison ","fraisLivraison" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Fournisseur,String> maCol = new TableColumn<>(colonnes[i][0]);
//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Fournisseur,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            fournisseurTableView.getColumns().add(maCol);
        }
    }


    @FXML
    void onFournitureChosed(ActionEvent event) {

    }

    @FXML
    void onNewCommand(ActionEvent event) {

    }

    @FXML
    void onNewFournisseur(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/fournisseur/createFournisseur","Ajouter un fournisseur");

    }

    @FXML
    void onNewFourniture(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/fourniture/createFourniture","Ajouter une fourniture");

    }

    @FXML
    void onRetourHomePage(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");

    }

    @FXML
    void onShowAllFournisseur(ActionEvent event) {

    }

    @FXML
    void onShowFinishedFourniture(ActionEvent event) {

    }

    @FXML
    void onShowPastCommand(ActionEvent event) {

    }
    @FXML
    void onShowMyCommandes(ActionEvent event) {

    }

}
