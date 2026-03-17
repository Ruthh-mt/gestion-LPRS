package appli.gestionnaire.fournitureFournisseur;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.Fourniture;
import model.gestionnaire.FournitureFournisseur;
import repository.gestionnaire.FournitureFournisseurRepository;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateFournitureFournisseurController {

    @FXML
    private TextField prixField;

    @FXML
    private ComboBox<Fournisseur> refFournisseurField;

    @FXML
    private ComboBox<Fourniture> refFournitureField;
    private FournitureFournisseur fournitureFournisseurSel;

    public void initData(FournitureFournisseur fournitureFournisseur){
        this.fournitureFournisseurSel=fournitureFournisseur;
    }

    @FXML
    public void initialize(FournitureFournisseur fournitureFournisseur) throws SQLException {
        this.fournitureFournisseurSel=fournitureFournisseur;
        refFournisseurField.setValue(this.fournitureFournisseurSel.getRefFournisseur());
        refFournitureField.setValue(this.fournitureFournisseurSel.getRefFourniture());
        prixField.setText(String.valueOf(this.fournitureFournisseurSel.getPrix()));
    }

    @FXML
    void onModifierFournisseurFourniture() throws IOException {
        if(refFournitureField.getValue() !=null||!prixField.getText().isEmpty()||refFournisseurField.getValue()!=null){
            this.fournitureFournisseurSel.setPrix(Double.parseDouble(prixField.getText()));
            FournitureFournisseurRepository fournitureFournisseurRepository = new FournitureFournisseurRepository();
            boolean success = fournitureFournisseurRepository.mettreAJourFournitureFournisseur(this.fournitureFournisseurSel);
            if(success){
                System.out.println("La modification du prix est reussi");
                showAlert(Alert.AlertType.INFORMATION,"Modification FournitureFournisseur","La modification est reussi");
                StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournisseurDUneFourniture","Listes des fournisseurs associée a cette fourniture");
            }else{
                showAlert(Alert.AlertType.ERROR,"Modification FournitureFournisseur","La modification a echoué");
            }
        }else{
            showAlert(Alert.AlertType.WARNING,"Modification FournitureFournisseur","Veuillez remplir tout les champs");
        }

    }


    @FXML
    void onDeleteFournitureFournisseur() {
        FournitureFournisseurRepository fournitureFournisseurRepo = new FournitureFournisseurRepository();
        boolean success=fournitureFournisseurRepo.supprimerFournitureFournisseur(fournitureFournisseurSel);
        if (success) {
            System.out.println("Suppression fourniture fournisseur reussi ");
            showAlert(Alert.AlertType.INFORMATION,"Suppression de la fourniture : "+fournitureFournisseurSel.getRefFourniture().getLibelle()+"au fournisseur "+fournitureFournisseurSel.getRefFournisseur().getNomfournisseur() ,"La suppression est reussi");
        }else{
            showAlert(Alert.AlertType.ERROR,"Suppression de la fourniture : "+fournitureFournisseurSel.getRefFourniture().getLibelle()+"au fournisseur "+fournitureFournisseurSel.getRefFournisseur().getNomfournisseur() ,"Erreur lors de la suppression");
        }
    }

    @FXML
    void onRetourControlCenter() throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","ControlCenter");

    }


    @FXML
    void onRetourListeFournisseur() throws IOException {
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournisseurDUneFourniture","Listes des fournisseurs associée a cette fourniture");
        ShowFournisseurDUneFournitureController controller = (ShowFournisseurDUneFournitureController)
                StartApplication.getControllerFromStage();
        controller.initData(this.fournitureFournisseurSel.getRefFourniture());
    }

    private void showAlert(Alert.AlertType type, String titre,String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}