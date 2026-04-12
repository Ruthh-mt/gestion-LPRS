package appli.gestionnaire.commande;

import appli.StartApplication;
import appli.gestionnaire.commandeFourniture.AddCommandeFournitureController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.gestionnaire.Commande;
import model.gestionnaire.Fournisseur;
import repository.gestionnaire.CommandeRepository;
import repository.gestionnaire.FournisseurRepository;
import session.Session;

import java.io.IOException;
import java.time.LocalDate;

import java.sql.SQLException;
import java.util.ArrayList;

public class CreateCommandeController {

    private Session session = Session.getInstance();

    @FXML
    private TextField dateCommandeField;

    @FXML
    private TextField dateLivraisonField;

    @FXML
    private TextField nomCommandeField;

    @FXML
    private TextArea raisonCommandeField;
    @FXML
    private ComboBox<String> statusField;

    @FXML
    private ComboBox<Fournisseur> refFournisseurField;

    @FXML
    public void initialize() throws SQLException {

        FournisseurRepository fournisseurRepo= new  FournisseurRepository();
        //----------------------------------------//
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>(fournisseurRepo.getAllFournisseur());
        for (Fournisseur fournisseur : fournisseurs) {
            refFournisseurField.getItems().add(fournisseur);
        }
        statusField.setValue("En cours");
        dateCommandeField.setText(LocalDate.now().toString());
    }

    @FXML
    void onCreateCommande() throws IOException {
        if(refFournisseurField.getValue()!=null && !nomCommandeField.getText().isEmpty()
                && !raisonCommandeField.getText().isEmpty() && !dateCommandeField.getText().isEmpty() &&
        statusField.getValue()!=null && !dateLivraisonField.getText().isEmpty()) {
            CommandeRepository commandeRepo = new CommandeRepository();
            Commande commande = new Commande(
                    raisonCommandeField.getText(),
                    this.session.getUtilisateur(),
                    refFournisseurField.getValue(),
                       nomCommandeField.getText(),
                    dateCommandeField.getText(),
                    statusField.getValue(),
                    dateLivraisonField.getText()
            );
            int success = commandeRepo.createCommande(commande);
            if (success !=0) {
                showAlert(Alert.AlertType.INFORMATION,"Creation d'une commande","La commande a été créer avec succes"+'\n'+"Vous allez etre redirigé pour ajoutez des fournitures");
                commande = commandeRepo.getCommandeById(success);
                System.out.println(commande);
                StartApplication.changeScene("gestionnaire/commandeFourniture/addCommandeFourniture","Ajout des fourniture pour la commande");
                AddCommandeFournitureController controller = (AddCommandeFournitureController)
                        StartApplication.getControllerFromStage();
                controller.initData(commande);

            }else{
                showAlert(Alert.AlertType.ERROR,"Erreur creation","Erreur lors de la creation de la commande");
            }
        }else{
            showAlert(Alert.AlertType.WARNING,"Manque d'info","Veuillez remplir tout les champs");
        }

    }
    @FXML
    public void onSeeDateLivraison() {
        if (refFournisseurField.getValue()!=null) {
            dateLivraisonField.setText(LocalDate.now().plusDays(refFournisseurField.getValue().getDelaiLivraisionMoyen()).toString());
        }

    }

    @FXML
    void onRetourControlCenter() throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");
    }
    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
