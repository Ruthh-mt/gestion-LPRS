package appli.gestionnaire;

import appli.StartApplication;
import appli.gestionnaire.commande.UpdateCommandeController;
import appli.gestionnaire.fournisseur.UpdateFournisseurController;
import appli.gestionnaire.fourniture.UpdateFournitureController;
import appli.gestionnaire.fournitureFournisseur.ShowFournisseurDeFournitureController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private Button voirLesFournisseursAssocie;

    @FXML
    private Button voirLesFournituresAssocie;

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
//partie commande
    @FXML
    void onCommandeChosed() {
        commandeTableView.getItems().clear();
        CommandeRepository commandeRepo = new CommandeRepository();
        ObservableList <Commande>allCommandes= FXCollections.observableList(commandeRepo.getAllCommandes());
        commandeTableView.getItems().setAll(allCommandes);
    }

    @FXML
    void onNewCommand(ActionEvent event) {

    }


    @FXML
    void onShowPastCommand(ActionEvent event) {

    }
    @FXML
    void onShowMyCommandes(ActionEvent event) {

    }

    @FXML
    void onCommandeTableClicked(MouseEvent event) throws IOException {
        Commande selection = commandeTableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            if (selection != null) {
                StartApplication.changeScene("gestionnaire/commande/updateCommande", "Modification Commande");
                UpdateCommandeController controller = (UpdateCommandeController)
                        StartApplication. getControllerFromStage();
                controller.initData(selection);
            }else{
                System.out.println("c'est null");
            }
        }
    }

    //partie fournisseur
    @FXML
    void onFournisseurChosed() {

        fournisseurTableView.getItems().clear();
        FournisseurRepository fournisseurRepo = new FournisseurRepository();
        ObservableList <Fournisseur>allFournisseur= FXCollections.observableList(fournisseurRepo.getAllFournisseur());
        fournisseurTableView.getItems().addAll(allFournisseur);


    }

    @FXML
    void onNewFournisseur(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/fournisseur/createFournisseur","Ajouter un fournisseur");

    }


    @FXML
    void onShowAllFournisseur(ActionEvent event) {

    }

    @FXML
    void onFournisseurTableClicked(MouseEvent event) throws IOException {
        Fournisseur selection = fournisseurTableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            if (selection != null) {
                StartApplication.changeScene("gestionnaire/fournisseur/updateFournisseur", "Modification Fournisseur");
                UpdateFournisseurController controller = (UpdateFournisseurController )
                        StartApplication. getControllerFromStage();
                controller.initData(selection);
            }else{
                System.out.println("c'est null");
            }
        }else if(selection!=null){
            voirLesFournituresAssocie.setVisible(true);
        }
    }

    @FXML
    void onShowFournitureDuFournisseur(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournitureDeFournisseur","Listes des fournitures associées a ce fournisseur");

    }

    //partie fourniture
    @FXML
    void onFournitureChosed() {

        fournitureTableView.getItems().clear();
        FournitureRepository fournitureRepo = new FournitureRepository();
        ObservableList <Fourniture>allFournitures= FXCollections.observableList(fournitureRepo.getAllFournitures());
        fournitureTableView.getItems().setAll(allFournitures);
    }

    @FXML
    void onNewFourniture(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/fourniture/createFourniture","Ajouter une fourniture");

    }

    @FXML
    void onShowFinishedFourniture(ActionEvent event) {

    }

    @FXML
    void onFournitureTableClicked(MouseEvent event) throws IOException {
        Fourniture selection = fournitureTableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            if (selection != null) {
                StartApplication.changeScene("gestionnaire/fourniture/updateFourniture", "Modification Fourniture");
                UpdateFournitureController controller = (UpdateFournitureController)
                        StartApplication. getControllerFromStage();
                controller.initData(selection);
            }else{
                System.out.println("c'est null");
            }
        }else if(selection!=null){
        voirLesFournisseursAssocie.setVisible(true);
    }
    }

    @FXML
    void onShowFournisseurDeFourniture(ActionEvent event) throws IOException {
        Fourniture selection = fournitureTableView.getSelectionModel().getSelectedItem();
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournisseurDeFourniture","Listes des fournisseurs associée a cette fourniture");
        ShowFournisseurDeFournitureController controller = (ShowFournisseurDeFournitureController)
                StartApplication. getControllerFromStage();
        controller.initData(selection);
    }
    //partie random
    @FXML
    void onRetourHomePage(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");

    }
}
