package appli.gestionnaire.fournitureFournisseur;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.Fourniture;
import model.gestionnaire.FournitureFournisseur;
import repository.gestionnaire.FournisseurRepository;
import repository.gestionnaire.FournitureFournisseurRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AddFournisseurPourFournitureController {
    private Fourniture fournitureSel;

    @FXML
    private TextField prixField;

    @FXML
    private ComboBox<Fournisseur> refFournisseurField;


    public void initData(Fourniture founiture) {
        this.fournitureSel = founiture;

    }

    @FXML
    public void initialize() throws SQLException {
        FournisseurRepository fournisseurRepo= new  FournisseurRepository();
        //----------------------------------------//
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>(fournisseurRepo.getAllFournisseur());
        for (Fournisseur fournisseur : fournisseurs) {
            refFournisseurField.getItems().add(fournisseur);
        }
    }
    @FXML
    void onAjouterFournisseur() throws IOException {
        if(refFournisseurField.getValue()!=null && prixField.getText()!=null) {
            FournitureFournisseur fournitureFournisseur = new FournitureFournisseur(fournitureSel, refFournisseurField.getValue(), Double.parseDouble(prixField.getText()));
            FournitureFournisseurRepository fournitureFournisseurRepo = new FournitureFournisseurRepository();
            boolean success= fournitureFournisseurRepo.addFournitureFournisseur(fournitureFournisseur);
            if(success) {
                Optional<ButtonType> choice = showAlertConfirmation();
                if (choice.isPresent() && choice.get().equals(ButtonType.OK)) {
                    StartApplication.changeScene("gestionnaire/fournitureFournisseur/addFournisseurPourFourniture","Ajouter un fournisseur");
                    AddFournisseurPourFournitureController controller =(AddFournisseurPourFournitureController)
                            StartApplication.getControllerFromStage();
                    controller.initData(fournitureSel);
                }else{
                    StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournisseurDUneFourniture","Listes des fournisseurs associée a cette fourniture");
                    ShowFournisseurDUneFournitureController controller = (ShowFournisseurDUneFournitureController)
                            StartApplication.getControllerFromStage();
                    controller.initData(fournitureSel);                 }
            }else{
                showAlert(Alert.AlertType.ERROR,"Erreur lors de l'ajour du fournisseur ( "+ refFournisseurField.getValue().getNomfournisseur()+" ) a la fourniture ( " +fournitureSel.getLibelle() + " )");
            }
        }

    }

    @FXML
    void onRetourControlCenter() throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","ControlCenter");
    }

    @FXML
    void onRetourListeFournisseur() throws IOException {
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournisseurDeFourniture","Listes des fournisseurs associée a cette fourniture");
        ShowFournisseurDUneFournitureController controller = (ShowFournisseurDUneFournitureController)
                StartApplication.getControllerFromStage();
        controller.initData(fournitureSel);

    }
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Ajout Fourniture");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Optional<ButtonType> showAlertConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajout d'un fournisseur pour : " + fournitureSel.getLibelle());
        alert.setHeaderText(null);
        alert.setContentText("L'ajout du fournisseur a eté faite avec succes. '\\n' Voulez vous encore ajouter un fournisseur pour la fourniture : '\n'" + fournitureSel.getLibelle());
        return alert.showAndWait();
    }

}
