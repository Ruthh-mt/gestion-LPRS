package appli.gestionnaire;

import appli.StartApplication;
import appli.gestionnaire.commande.UpdateCommandeController;
import appli.gestionnaire.fournisseur.UpdateFournisseurController;
import appli.gestionnaire.fourniture.UpdateFournitureController;
import appli.gestionnaire.fournitureFournisseur.ShowFournisseurDUneFournitureController;
import appli.gestionnaire.fournitureFournisseur.ShowFournitureDUnFournisseurController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Utilisateur;
import model.gestionnaire.Commande;
import model.gestionnaire.Demande;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.Fourniture;
import repository.gestionnaire.CommandeRepository;
import repository.gestionnaire.DemandeRepository;
import repository.gestionnaire.FournisseurRepository;
import repository.gestionnaire.FournitureRepository;
import session.Session;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccueilGestionnaireController  implements Initializable {

    private Session session;
    private CommandeRepository commandeRepository;

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
    private TableView<Demande> demandesTableView;

    @FXML
    private Label nbFournitureVide;

    @FXML
    private Button voirLesFournisseursAssocie;

    @FXML
    private Button voirLesFournituresAssocie;

    public void initData(Session s) throws IOException {
        this.session = s;
        if(s.getUtilisateur().getRole()!="gestionnaire") {
            showAlert(Alert.AlertType.ERROR,"Access Denied","Vous n'etes pas autorisé a etre sur cette page ");
            StartApplication.changeScene("accueil/homePage","Accueil");

        } else if (s.getUtilisateur()==null) {
            showAlert(Alert.AlertType.ERROR,"Who are you ??","Vous n'etes pas connecté veuiller vous connecter ou vous inscrire");
            StartApplication.changeScene("accueil/login","Connexion");
        }
    }
    // partie qui remplie le tableau
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String [][] colonnes = {
                {"id Commande","idCommande"},
                {"Nom","nomCommande"},
                {"Chez","refFournisseur"},
                {"Faite le ","dateCommande"},
                {"Par ","refGestionnaire"}
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

         colonnes = new String[][]{
                {"id Demande","idDemande"},
                {"est Validé","estValide"},
                {"Fait par ","refProfesseur"},
                {"Validé par ","refGestionnaire"},
                {"Status ","status"},
                 {"Niveau d'urgence","urgence"},
                 {"Date demande","dateDemande"}
        };
        for (String[] colonne : colonnes) {
            //Création de la colonne avec le titre
            TableColumn<Demande, String> maCol = new TableColumn<>(colonne[0]);
//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<>(colonne[1]));
            //Ajout de la colonne dans notre tableau
            demandesTableView.getColumns().add(maCol);
        }
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
    void onNewCommand() throws IOException {
        StartApplication.changeScene("gestionnaire/commande/createCommande","Faire une Commande");

    }


    @FXML
    void onShowPastCommand() throws IOException {
        StartApplication.changeScene("gestionnaire/commande/historiqueCommandes","Historique Des commandes");
    }

    @FXML
    void onShowMyCommandes() throws IOException {
        StartApplication.changeScene("gestionnaire/commande/showMesCommandeUserView","Mes commandes");
    }

    @FXML
    void onCommandeTableClicked(MouseEvent event) throws IOException {
        Commande selectionCommande = commandeTableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            if (selectionCommande != null) {
                StartApplication.changeScene("gestionnaire/commande/updateCommande", "Modification Commande");
                UpdateCommandeController controller = (UpdateCommandeController)
                        StartApplication. getControllerFromStage();
                controller.initData(selectionCommande);
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
    void onNewFournisseur() throws IOException {
        StartApplication.changeScene("gestionnaire/fournisseur/createFournisseur","Ajouter un fournisseur");

    }

    @FXML
    void onFournisseurTableClicked(MouseEvent event) throws IOException {
        Fournisseur selectionFournisseur = fournisseurTableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            if (selectionFournisseur != null) {
                StartApplication.changeScene("gestionnaire/fournisseur/updateFournisseur", "Modification Fournisseur");
                UpdateFournisseurController controller = (UpdateFournisseurController )
                        StartApplication. getControllerFromStage();
                controller.initData(selectionFournisseur);
            }else{
                System.out.println("c'est null");
            }
        }else if(selectionFournisseur!=null){
            voirLesFournituresAssocie.setDisable(false);
        }
    }

    @FXML
    void onShowFournitureDuFournisseur() throws IOException {
        Fournisseur selectionFournisseur = fournisseurTableView.getSelectionModel().getSelectedItem();
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournitureDUnFournisseur","Listes des fourniture associés a ce fournisseur");

        ShowFournitureDUnFournisseurController controller = (ShowFournitureDUnFournisseurController)
                StartApplication.getControllerFromStage();
        controller.initData(selectionFournisseur);
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
    void onNewFourniture() throws IOException {
        StartApplication.changeScene("gestionnaire/fourniture/createFourniture","Ajouter une fourniture");

    }

    @FXML
    void onShowFinishedFourniture() {

    }

    @FXML
    void onFournitureTableClicked(MouseEvent event) throws IOException {
        Fourniture selectionFourniture = fournitureTableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            if (selectionFourniture != null) {
                StartApplication.changeScene("gestionnaire/fourniture/updateFourniture", "Modification Fourniture");
                UpdateFournitureController controller = (UpdateFournitureController)
                        StartApplication. getControllerFromStage();
                controller.initData(selectionFourniture);
            }
        }else if(selectionFourniture!=null) {
            voirLesFournisseursAssocie.setDisable(false);
        }
    }

    @FXML
    void onShowFournisseurDeFourniture() throws IOException {
        Fourniture selectionFourniture = fournitureTableView.getSelectionModel().getSelectedItem();
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournisseurDUneFourniture","Listes des fournisseurs associée a cette fourniture");
        ShowFournisseurDUneFournitureController controller = (ShowFournisseurDUneFournitureController)
                StartApplication.getControllerFromStage();
        controller.initData(selectionFourniture);

    }

    //partie Demandes
    @FXML
    void onDemandeChosed() {
        demandesTableView.getItems().clear();
        DemandeRepository demandeRepo = new DemandeRepository();
        ObservableList <Demande>allDemandes= FXCollections.observableList(demandeRepo.getAllDemandes());
        demandesTableView.getItems().setAll(allDemandes);

    }

    @FXML
    void onShowUnvalidatedDemandes() {

    }

    //partie random
    @FXML
    void onRetourHomePage() throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");

    }

    @FXML
    void onProfilClicked() throws IOException {
        StartApplication.changeScene("profil/profilRead","Mon Profil");
    }
    @FXML
    void onDeconnexionClicked() throws IOException {
        this.session.deconnecter();
        if(this.session==null){
            showAlert(Alert.AlertType.INFORMATION,"Deconnexion","Vous vous etes deconnecté");
            StartApplication.changeScene("accueil/login","Connexion");
        }

    }
    private void showAlert(Alert.AlertType type,String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
