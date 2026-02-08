package appli.gestionnaire.fournisseur;
import appli.StartApplication;
import model.gestionnaire.Fournisseur;
import repository.gestionnaire.FournisseurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;

public class CreateFournisseurController {

    @FXML
    private TextField adresseFournisseurField;

    @FXML
    private TextField delaisLivraisonMoyenField;

    @FXML
    private TextField fraisLivraisonField;

    @FXML
    private TextField mailFournisseurField;

    @FXML
    private TextField nomFournisseurField;

    @FXML
    private TextField telephoneFournisseurField;

    @FXML
    void onAddFournisseur(ActionEvent event) throws IOException {
        if(adresseFournisseurField.getText().isEmpty()|| delaisLivraisonMoyenField.getText().isEmpty()|| fraisLivraisonField.getText().isEmpty()||
                mailFournisseurField.getText().isEmpty()|| nomFournisseurField.getText().isEmpty()|| telephoneFournisseurField.getText().isEmpty()){
            showAlert(AlertType.WARNING, "Veuillez remplir tout les champs");

        }else{
            int delaisLivraison= Integer.parseInt(delaisLivraisonMoyenField.getText());
            double fraisLivraison=Double.parseDouble(fraisLivraisonField.getText());
            Fournisseur fournisseur=new Fournisseur(nomFournisseurField.getText(),adresseFournisseurField.getText(),mailFournisseurField.getText()
            , telephoneFournisseurField.getText(),delaisLivraison,fraisLivraison);
            FournisseurRepository repoFournisseur = new FournisseurRepository();
            boolean result=repoFournisseur.createFournisseur(fournisseur);
            if(result){
                showAlert(AlertType.INFORMATION,"L'ajout du fournisseur a bien été reussie");
                StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");
            }else{
                showAlert(AlertType.ERROR,"Erreur lors de l'ajout du fournisseur");

            }

        }
    }

    @FXML
    void onReturnAccueilGestionnaire(ActionEvent event) throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Ajout Fournisseur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
