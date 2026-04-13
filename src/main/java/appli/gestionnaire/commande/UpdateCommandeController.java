package appli.gestionnaire.commande;

import appli.StartApplication;
import appli.gestionnaire.commandeFourniture.AddCommandeFournitureController;
import appli.gestionnaire.commandeFourniture.AddCommandeFournitureFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.gestionnaire.Commande;
import model.gestionnaire.Fournisseur;
import repository.gestionnaire.CommandeFournitureRepository;
import repository.gestionnaire.CommandeRepository;
import repository.gestionnaire.FournisseurRepository;
import session.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class UpdateCommandeController {

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
    private ComboBox<Fournisseur> refFournisseurField;

    @FXML
    private ComboBox<String> statusField;

    private Commande commandeSel;

    public void initData(Commande commande){
     this.commandeSel=commande;
        statusField.getItems().addAll("En cours", "Complete");
     //----------------------------------------//
        nomCommandeField.setText(commandeSel.getNomCommande());
        raisonCommandeField.setText(commandeSel.getRaisonCommande());
        statusField.setValue(commandeSel.getStatus());
        dateCommandeField.setText(commandeSel.getDateCommande());
        dateLivraisonField.setText(commandeSel.getDateLivraison());
    }

    @FXML
    void onUpdateCommande(ActionEvent event) throws IOException {
        if(refFournisseurField.getValue()!=null && !nomCommandeField.getText().isEmpty()
                && !raisonCommandeField.getText().isEmpty() && !dateCommandeField.getText().isEmpty() &&
                statusField.getValue()!=null && !dateLivraisonField.getText().isEmpty()) {
            Commande commande = new Commande(
                    commandeSel.getIdCommande(),
                    raisonCommandeField.getText(),
                    this.session.getUtilisateur(),
                    refFournisseurField.getValue(),
                    nomCommandeField.getText(),
                    dateCommandeField.getText(),
                    statusField.getValue(),
                    dateLivraisonField.getText()
            );
            CommandeRepository commandeRepo=new CommandeRepository();

            boolean success = commandeRepo.mettreAJourCommande(commande);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION,"Modification d'une commande","La commande a été modifié avec succes"+'\n');
                System.out.println(commande);
                StartApplication.changeScene("gestionnaire/commandeFourniture/addCommandeFourniture","Ajout des fourniture pour la commande");
                UpdateCommandeController controller = (UpdateCommandeController)
                        StartApplication.getControllerFromStage();
                controller.initData(commande);

            }else{
                showAlert(Alert.AlertType.ERROR,"Erreur Modification","Erreur lors de la modification de la commande");
            }
        }else{
            showAlert(Alert.AlertType.WARNING,"Manque d'info","Veuillez remplir tout les champs");
        }

    }

    @FXML
    void onDeleteCommande(ActionEvent event) {
        Optional<ButtonType> choice = showAlertConfirmation();
        if (choice.isPresent() && choice.get().equals(ButtonType.OK)) {
            CommandeRepository commandeRepo=new CommandeRepository();
            CommandeFournitureRepository commandeFournitureRepo=new CommandeFournitureRepository();
            boolean success=commandeFournitureRepo.supprimerToutesFournituresByCommandeId(commandeSel.getIdCommande());
            if(success){
                if(commandeRepo.supprimerCommandeParId(commandeSel)){
                    showAlert(Alert.AlertType.INFORMATION,"Suppression reussi","La suppression es reuissi");
                }
            }else{
                showAlert(Alert.AlertType.ERROR,"Erreur lors de la suppression", "Une erreur est survenue lors de la suppression");
            }
        }else{
            showAlert(Alert.AlertType.INFORMATION,"Suppression Anuler","Nous avons stoper la suppression");
        }
    }

    @FXML
    void onRetourControlCenter(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");
    }
    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Optional<ButtonType> showAlertConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajout d'une founiture pour la commande : " + commandeSel.getNomCommande());
        alert.setHeaderText(null);
        alert.setContentText("L'ajout de la fourniture a eté faite avec succes. '\\n' Voulez vous encore ajouter une fourniture pour la commande : '\n'" +  commandeSel.getNomCommande());
        return alert.showAndWait();
    }
}
