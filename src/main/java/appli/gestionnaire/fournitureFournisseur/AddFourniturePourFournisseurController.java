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
import repository.gestionnaire.FournitureFournisseurRepository;
import repository.gestionnaire.FournitureRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AddFourniturePourFournisseurController {

    @FXML
    private TextField prixField;

    @FXML
    private ComboBox<Fourniture> refFournitureField;

    private Fournisseur fournisseurSel;

    public void initData(Fournisseur fournisseur) {
        this.fournisseurSel = fournisseur;
    }
    @FXML
    public void initialize() throws SQLException {
        FournitureRepository fournitureRepo= new  FournitureRepository();
        //----------------------------------------//
        ArrayList<Fourniture> fournitures = new ArrayList<>(fournitureRepo.getAllFournitures());
        for (Fourniture fourniture : fournitures) {
            refFournitureField.getItems().add(fourniture);
        }
    }

    @FXML
    void onAjouterFourniture() throws IOException {
        if (refFournitureField.getValue() != null && prixField.getText() != null) {
            FournitureFournisseur fournitureFournisseur = new FournitureFournisseur(refFournitureField.getValue(), fournisseurSel, Double.parseDouble(prixField.getText()));
            FournitureFournisseurRepository fournitureFournisseurRepo = new FournitureFournisseurRepository();
            boolean success = fournitureFournisseurRepo.addFournitureFournisseur(fournitureFournisseur);
            if (success) {
                Optional<ButtonType> choice = showAlertConfirmation();
                if (choice.isPresent() && choice.get().equals(ButtonType.OK)) {
                    StartApplication.changeScene("gestionnaire/fournitureFournisseur/addFournisseurPourFourniture", "Ajouter un fournisseur");
                    AddFourniturePourFournisseurController controller = (AddFourniturePourFournisseurController)
                            StartApplication.getControllerFromStage();
                    controller.initData(fournisseurSel);
                } else {
                    StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournisseurDUneFourniture", "Listes des fournisseurs associée a cette fourniture");
                    ShowFournitureDUnFournisseurController controller = (ShowFournitureDUnFournisseurController)
                            StartApplication.getControllerFromStage();
                    controller.initData(fournisseurSel);
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur lors de l'ajout de la fourniture ( " + refFournitureField.getValue().getLibelle() + " ) a le fournisseur ( " + fournisseurSel.getNomfournisseur() + " )");
            }
        }
    }

    @FXML
    void onRetourControlCenter() throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","ControlCenter");
    }

    @FXML
    void onRetourListeFournitures() throws IOException {
        StartApplication.changeScene("gestionnaire/fournitureFournisseur/showFournisseurDeFourniture","Listes des fournisseurs associée a cette fourniture");
        ShowFournitureDUnFournisseurController controller = (ShowFournitureDUnFournisseurController)
                StartApplication.getControllerFromStage();
        controller.initData(fournisseurSel);
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
        alert.setTitle("Ajout d'une fourniture pour : " + fournisseurSel.getNomfournisseur());
        alert.setHeaderText(null);
        alert.setContentText("L'ajout du fournisseur a eté faite avec succes. '\\n' Voulez vous encore ajouter une fourniture pour le fournisseur : '\n'" + fournisseurSel.getNomfournisseur());
        return alert.showAndWait();
    }


}
