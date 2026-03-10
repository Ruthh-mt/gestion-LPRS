package appli.gestionnaire.fourniture;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.gestionnaire.Fourniture;
import repository.gestionnaire.FournitureRepository;

import java.io.IOException;
import java.util.Optional;

public class CreateFournitureController {

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

    @FXML
    void onAddFourniture() throws IOException {
        if(libelleField.getText().isEmpty() || stockActuelleField.getText().isEmpty() || stockMinimumField.getText().isEmpty()
        || descriptionField.getText().isEmpty()){
            showAlert(Alert.AlertType.WARNING,"Veuillez remplir tous les champs");
        }else{
            Fourniture fourniture = new Fourniture(
                    libelleField.getText(),
                    descriptionField.getText(),
                    Integer.parseInt(stockActuelleField.getText()),
                    Integer.parseInt(stockMinimumField.getText())
            );
            FournitureRepository fournitureRepo=new FournitureRepository();
            boolean success=fournitureRepo.createFourniture(fourniture);
            if(success){
                Optional<ButtonType>choice=showAlertConfirmation();
                if(choice.isPresent() && choice.get()==ButtonType.OK){
                    StartApplication.changeScene("fournitureFournisseur/addFournisseur","liaison avec Fournisseur");
                }else{
                    StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");
                }

            }else{
                showAlert(Alert.AlertType.ERROR,"Erreur lors de l'ajout de la fourniture");
            }

        }
    }

    @FXML
    void onReturnAccueilGestionnaire() throws IOException {
        StartApplication.changeScene("gestionnaire/AccueilGestionnaire","Control Center");
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
        alert.setTitle("Ajout Fourniture");
        alert.setHeaderText(null);
        alert.setContentText("La fourniture a bien été crée. Voulez vous ajouter un ou des fournisseurs ?.");
        return alert.showAndWait();
    }
}
