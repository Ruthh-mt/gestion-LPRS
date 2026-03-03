package appli.gestionnaire.fourniture;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.gestionnaire.Fourniture;
import javafx.scene.control.TextArea;
import repository.gestionnaire.FournitureRepository;

import java.io.IOException;


public class UpdateFournitureController {

    @FXML
    private TextArea descriptionField;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField libelleField;

    @FXML
    private TextField stockActuelleField;

    @FXML
    private TextField stockMinimumField;


    private Fourniture fournitureSel;
    public void initData(Fourniture fourniture) {
        this.fournitureSel=fourniture;
        this.libelleField.setText(fournitureSel.getLibelle());
        this.descriptionField.setText(fournitureSel.getDescription());
        this.stockActuelleField.setText(String.valueOf(fournitureSel.getStockActuelle()));
        this.stockMinimumField.setText(String.valueOf(fournitureSel.getStockMinimum()));
    }
    @FXML
    void onUpdateFourniture(ActionEvent event) {
        if(libelleField.getText().isEmpty() || stockActuelleField.getText().isEmpty() || stockMinimumField.getText().isEmpty()
                || descriptionField.getText().isEmpty()){
            showAlert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs");
        }else{
            fournitureSel.setLibelle(libelleField.getText());
            fournitureSel.setDescription(descriptionField.getText());
            fournitureSel.setStockActuelle(Integer.parseInt(stockActuelleField.getText()));
            fournitureSel.setStockMinimum(Integer.parseInt(stockMinimumField.getText()));
            FournitureRepository fournitureRepo=new FournitureRepository();
            boolean success=fournitureRepo.mettreAJourFourniture(fournitureSel);
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
    void onDeleteFourniture(ActionEvent event) throws IOException {
        FournitureRepository fournitureRepo=new FournitureRepository();
        boolean deleteSuccess=fournitureRepo.supprimerFournitureParId(fournitureSel);
        if(deleteSuccess) {
            showAlert(Alert.AlertType.INFORMATION,"La suppression est reussi");
            StartApplication.changeScene("gestionnaire/AccueilGestionnaire","Control Center");
        }else{
            showAlert(Alert.AlertType.ERROR,"La suppression a echoué");
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Modification Fourniture");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
