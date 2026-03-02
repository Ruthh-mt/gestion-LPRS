package appli.gestionnaire.fournisseur;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.gestionnaire.Fournisseur;
import repository.gestionnaire.FournisseurRepository;

import java.io.IOException;


public class UpdateFournisseurController {

    @FXML
    private TextField adresseFournisseurField;

    @FXML
    private TextField delaisLivraisonMoyenField;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField fraisLivraisonField;

    @FXML
    private TextField mailFournisseurField;

    @FXML
    private TextField nomFournisseurField;

    @FXML
    private TextField telephoneFournisseurField;

    private Fournisseur fournisseurSel;
    public void initData(Fournisseur fournisseur) {
        this.fournisseurSel = fournisseur;
        nomFournisseurField.setText(fournisseurSel.getNomfournisseur());
        adresseFournisseurField.setText(fournisseurSel.getAdresseFournisseur());
        mailFournisseurField.setText(fournisseurSel.getMailFournisseur());
        telephoneFournisseurField.setText(fournisseurSel.getTelephoneFournisseur());
        delaisLivraisonMoyenField.setText(String.valueOf(fournisseurSel.getDelaiLivraisionMoyen()));
        fraisLivraisonField.setText(String.valueOf(fournisseurSel.getFraisLivraison()));

        

    }
    @FXML
    void onUpdateFournisseur(ActionEvent event) {
        if(nomFournisseurField.getText().isEmpty()|| adresseFournisseurField.getText().isEmpty() || mailFournisseurField.getText().isEmpty()
        || telephoneFournisseurField.getText().isEmpty() || delaisLivraisonMoyenField.getText().isEmpty() || fraisLivraisonField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs");
        }else{
            fournisseurSel.setNomfournisseur(nomFournisseurField.getText());
            fournisseurSel.setAdresseFournisseur(adresseFournisseurField.getText());
            fournisseurSel.setMailFournisseur(mailFournisseurField.getText());
            fournisseurSel.setTelephoneFournisseur(telephoneFournisseurField.getText());
            fournisseurSel.setDelaiLivraisionMoyen(Integer.parseInt(delaisLivraisonMoyenField.getText()));
            fournisseurSel.setFraisLivraison(Double.parseDouble(fraisLivraisonField.getText()));
            FournisseurRepository fournisseurRepo=new FournisseurRepository();
            boolean success=fournisseurRepo.mettreAJourFournisseur(fournisseurSel);
            if(success) {
                showAlert(Alert.AlertType.INFORMATION,"La modification est reussi");
            }else{
                showAlert(Alert.AlertType.ERROR,"La modification a echoué. Veuillez réessayer");
            }
        }

    }

    @FXML
    void onReturnAccueilGestionnaire(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");

    }
    @FXML
    void onDeleteFournisseur(ActionEvent event) throws IOException {
        FournisseurRepository fournisseurRepo=new FournisseurRepository();
        boolean deleteSuccess=fournisseurRepo.supprimerFournisseurParId(fournisseurSel);
        if(deleteSuccess) {
            showAlert(Alert.AlertType.INFORMATION,"La suppression est reussi");
            StartApplication.changeScene("gestionnaire/AccueilGestionnaire","Control Center");
        }else{
            showAlert(Alert.AlertType.ERROR,"La suppression a echoué");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Modification Fournisseur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
