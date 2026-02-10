package appli.gestionnaire;

import appli.StartApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import repository.gestionnaire.CommandeRepository;
import repository.gestionnaire.FournisseurRepository;
import repository.gestionnaire.FournitureRepository;

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
                {"Nom","nomCommande"},
                {"Chez","refFournisseur"},
                {"Faite le ","dateCommande"}
        };

        for (String[] colonne : colonnes) {
            //Création de la colonne avec le titre
            TableColumn<Commande, String> maCol = new TableColumn<>(colonne[0]);
//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<>(colonne[1]));
            //Ajout de la colonne dans notre tableau
            commandeTableView.getColumns().add(maCol);
        }

        colonnes = new String[][]{
                {"Id Fournisseur", "idFournisseur"},
                {"Nom", "nomfournisseur"},
                {"Mail", "mailFournisseur"},
                {"Telephone", "telephoneFournisseur"},
                {"Frais de livraison ", "fraisLivraison"},
        };


        for (String[] colonne : colonnes) {
            //Création de la colonne avec le titre
            TableColumn<Fournisseur, String> maCol = new TableColumn<>(colonne[0]);
//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<>(colonne[1]));
            //Ajout de la colonne dans notre tableau
            fournisseurTableView.getColumns().add(maCol);
        }
        colonnes = new String[][]{
                {"Id Fourniture", "idFourniture"},
                {"Libelle", "libelle"},
                {"Stock actuelle", "stockActuelle"},
                {"Stock minimum", "stockMinimum"},
        };

        for (String[] colonne : colonnes) {
            //Création de la colonne avec le titre
            TableColumn<Fourniture, String> maCol = new TableColumn<>(colonne[0]);
//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<>(colonne[1]));
            //Ajout de la colonne dans notre tableau
            fournitureTableView.getColumns().add(maCol);
        }
        commandeTableView.getItems().clear();
        CommandeRepository commandeRepo = new CommandeRepository();
        ObservableList <Commande>allCommandes= FXCollections.observableList(commandeRepo.getAllCommandes());
        commandeTableView.getItems().setAll(allCommandes);
    }

    @FXML
    void onCommandeChosed() {
        System.out.println("Ben alors t'a enfin trouvé la solution");
    }


    @FXML
    void onFournisseurChosed() {

        fournisseurTableView.getItems().clear();
        FournisseurRepository fournisseurRepo = new FournisseurRepository();
        ObservableList <Fournisseur>allFournisseur= FXCollections.observableList(fournisseurRepo.getAllFournisseur());
        fournisseurTableView.getItems().addAll(allFournisseur);

    }

    @FXML
    void onFournitureChosed() {

        fournitureTableView.getItems().clear();
        FournitureRepository fournitureRepo = new FournitureRepository();
        ObservableList <Fourniture>allFournitures= FXCollections.observableList(fournitureRepo.getAllFournitures());
        fournitureTableView.getItems().setAll(allFournitures);
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
