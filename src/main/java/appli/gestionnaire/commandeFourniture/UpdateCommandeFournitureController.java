package appli.gestionnaire.commandeFourniture;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.gestionnaire.CommandeFourniture;
import model.gestionnaire.FournitureFournisseur;
import repository.gestionnaire.CommandeFournitureRepository;
import repository.gestionnaire.FournitureFournisseurRepository;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateCommandeFournitureController {
    private CommandeFourniture commandeFournitureSel;
    @FXML
    private TextField nomCommandeField;

    @FXML
    private TextField qteField;

    @FXML
    private ComboBox<FournitureFournisseur> refFournitureField;

    public void initData(CommandeFourniture commandeFourniture) {
        this.commandeFournitureSel = commandeFourniture;
        nomCommandeField.setText(commandeFournitureSel.getRefCommande().getNomCommande());
        FournitureFournisseurRepository fournitureRepo= new  FournitureFournisseurRepository();
        //----------------------------------------//
        ArrayList<FournitureFournisseur> fournitures = new ArrayList<>(fournitureRepo.getAllFournitureByFournisseursId(commandeFournitureSel.getRefCommande().getRefFournisseur()));
        for (FournitureFournisseur fourniture : fournitures) {
            refFournitureField.getItems().add(fourniture);
            if(fourniture.getRefFourniture().getIdFourniture()==commandeFournitureSel.getRefFourniture().getIdFourniture()) {
                refFournitureField.setValue(fourniture);
            }
        }
        qteField.setText(String.valueOf(this.commandeFournitureSel.getQte()));

    }
   /* @FXML
    public void initialize() throws SQLException {
        FournitureFournisseurRepository fournitureRepo= new  FournitureFournisseurRepository();
        //----------------------------------------//
        ArrayList<FournitureFournisseur> fournitures = new ArrayList<>(fournitureRepo.getAllFournitureByFournisseursId(commandeFournitureSel.getRefCommande().getRefFournisseur()));
        for (FournitureFournisseur fourniture : fournitures) {
            refFournitureField.getItems().add(fourniture);
            if(fourniture.getRefFourniture().getIdFourniture()==commandeFournitureSel.getRefFourniture().getIdFourniture()) {
                refFournitureField.setValue(fourniture);
            }
        }
        qteField.setText(String.valueOf(this.commandeFournitureSel.getQte()));
    }*/
    @FXML
    void onUpdateFournitureCommande() {
        if(refFournitureField.getValue()!=null && !qteField.getText().isEmpty()){
            CommandeFourniture CommandeFourniturenew = new CommandeFourniture(
                    commandeFournitureSel.getRefCommande(),
                    refFournitureField.getValue().getRefFourniture(),
                    Integer.parseInt(qteField.getText())
            );
            CommandeFournitureRepository commandeFournitureRepo = new CommandeFournitureRepository();
            boolean sucess=commandeFournitureRepo.mettreAJourCommandeFourniture(CommandeFourniturenew);
            if(sucess){
                showAlert(Alert.AlertType.INFORMATION,"modification reussi","La modification de la fourniture est reussi. '\n' Ancienne fourniture : "+ this.commandeFournitureSel.getRefFourniture() + " -> Nouvelle fourniture : "+ refFournitureField.getValue().getRefFourniture() );
            }else{
                showAlert(Alert.AlertType.ERROR,"Erreur lors de la modification","Un probleme est survenue lors de la modfification de la fourniture. Veuillez reéssayer dans quelques instant");
            }

        }else{
            showAlert(Alert.AlertType.WARNING,"Champs nom rempli","Veuillez remplir tout les champs");
        }

    }

    @FXML
    void onRetourAddFourniture() throws IOException {
        StartApplication.changeScene("gestionnaire/commandeFourniture/addCommandeFourniture","Ajout des fourniture pour la commande");
        AddCommandeFournitureController controller = (AddCommandeFournitureController)
                StartApplication.getControllerFromStage();
        controller.initData(commandeFournitureSel.getRefCommande());
    }

    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
