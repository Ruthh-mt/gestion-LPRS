package appli.gestionnaire.commandeFourniture;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.gestionnaire.Commande;
import model.gestionnaire.CommandeFourniture;
import model.gestionnaire.FournitureFournisseur;
import repository.gestionnaire.CommandeFournitureRepository;
import repository.gestionnaire.FournitureFournisseurRepository;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Optional;


public class AddCommandeFournitureFormController {

    private Commande commandeSel;

    @FXML
    private TextField nomCommandeField;

    @FXML
    private TextField qteField;

    @FXML
    private ComboBox<FournitureFournisseur> refFournitureField;

    public void initData(Commande commande){
        this.commandeSel = commande;
        nomCommandeField.setText(commandeSel.getNomCommande());
        FournitureFournisseurRepository fournitureRepo= new  FournitureFournisseurRepository();
        //----------------------------------------//
        ArrayList<FournitureFournisseur> fournitures = new ArrayList<>(fournitureRepo.getAllFournitureByFournisseursId(commandeSel.getRefFournisseur()));
        for (FournitureFournisseur fourniture : fournitures) {
            refFournitureField.getItems().add(fourniture);
        }
    }

  /*  @FXML
    public void initialize() throws SQLException {
        nomCommandeField.setText(commandeSel.getNomCommande());
        FournitureFournisseurRepository fournitureRepo= new  FournitureFournisseurRepository();
        //----------------------------------------//
        ArrayList<FournitureFournisseur> fournitures = new ArrayList<>(fournitureRepo.getAllFournitureByFournisseursId(commandeSel.getRefFournisseur()));
        for (FournitureFournisseur fourniture : fournitures) {
            refFournitureField.getItems().add(fourniture);
        }
    }*/
    @FXML
    void onAddFournitureCommande() throws IOException {
        if(!(qteField.getText().isEmpty() || refFournitureField.getValue()==null)){
            CommandeFournitureRepository commandeFournitureRepo=  new CommandeFournitureRepository();
            CommandeFourniture fournitures = new CommandeFourniture(
                    commandeSel,
                    refFournitureField.getValue().getRefFourniture(),
                    Integer.parseInt(qteField.getText())
            );
            boolean success = commandeFournitureRepo.addCommandeFourniture(fournitures);
            if(success){
                Optional<ButtonType> choice = showAlertConfirmation();
                if (choice.isPresent() && choice.get().equals(ButtonType.OK)) {
                    StartApplication.changeScene("gestionnaire/commandeFourniture/addCommandeFournitureForm","Ajouter une fourniture à la commande");
                    AddCommandeFournitureFormController controller =(AddCommandeFournitureFormController)
                            StartApplication.getControllerFromStage();
                    controller.initData(commandeSel);
                }else{
                    StartApplication.changeScene("gestionnaire/commandeFourniture/addCommandeFourniture","Listes des fournisseurs associée a cette fourniture");
                    AddCommandeFournitureController controller = (AddCommandeFournitureController)
                            StartApplication.getControllerFromStage();
                    controller.initData(commandeSel);                 }
            }else{
                showAlert(Alert.AlertType.ERROR,"Erreur lors de l'ajout", "L'ajout de la fourniture a echoué. Veuillez reessayer.");
            }
        }else{
            showAlert(Alert.AlertType.WARNING,"Champs non complet","Il semblerait que vous n'avez pas remplit tout les champs. '\n' Veuillez remplir tout les champs");
        }

    }

    @FXML
    void onRetourAddFourniture() throws IOException {
        StartApplication.changeScene("gestionnaire/commandeFourniture/addCommandeFourniture","Ajout des fourniture pour la commande");
        AddCommandeFournitureController controller = (AddCommandeFournitureController)
                StartApplication.getControllerFromStage();
        controller.initData(commandeSel);
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
