package appli.gestionnaire.fournisseur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.gestionnaire.Fournisseur;


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
        nomFournisseurField.setText(fournisseur.getNomfournisseur());
        adresseFournisseurField.setText(fournisseur.getAdresseFournisseur());
        mailFournisseurField.setText(fournisseur.getMailFournisseur());
        

    }
    @FXML
    void onUpdateFournisseur(ActionEvent event) {

    }

    @FXML
    void onReturnAccueilGestionnaire(ActionEvent event) {

    }

}
