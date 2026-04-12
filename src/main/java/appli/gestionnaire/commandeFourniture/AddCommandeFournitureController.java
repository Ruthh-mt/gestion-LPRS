package appli.gestionnaire.commandeFourniture;

import appli.StartApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.gestionnaire.Commande;
import model.gestionnaire.CommandeFourniture;
import repository.gestionnaire.CommandeFournitureRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCommandeFournitureController implements Initializable {

    private Commande commandeSel;

    @FXML
    private Label titleLabel;
    @FXML
    private Button deleteCommandeFourniturebtn;

    @FXML
    private TableView<CommandeFourniture> commandeFournitureTableView;

    public  void initData(Commande commande) {
        this.commandeSel = commande;
        titleLabel.setText("Fournitures de la commande : "+ commandeSel.getNomCommande());
        commandeFournitureTableView.getItems().clear();
        CommandeFournitureRepository commandeFournitureRepo = new CommandeFournitureRepository();
        ObservableList<CommandeFourniture> allFournitures= FXCollections.observableList(commandeFournitureRepo.getAllFournitureByCommandeId(commandeSel));
        commandeFournitureTableView.getItems().setAll(allFournitures);
    }

    public void initialize(URL location, ResourceBundle resources) {

        String [][] colonnes = {
                {"Fourniture","refFourniture"},
                {"Quantité","qte"}
        };

        for (String[] colonne : colonnes) {
            //Création de la colonne avec le titre
            TableColumn<CommandeFourniture, String> maCol = new TableColumn<>(colonne[0]);
//Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<>(colonne[1]));
            //Ajout de la colonne dans notre tableau
            commandeFournitureTableView.getColumns().add(maCol);



        }
    }

    @FXML
    void onAddCommandeFourniture() throws IOException {
        StartApplication.changeScene("gestionnaire/commandeFourniture/addCommandeFournitureForm", "Formulaire d'ajout");
         AddCommandeFournitureFormController controller = (AddCommandeFournitureFormController)
                 StartApplication.getControllerFromStage();
        controller.initData(commandeSel);
    }

    @FXML
    void onCommandeFournitureTableViewClicked(MouseEvent event) throws IOException {
        CommandeFourniture selectionCommandeFourniture= commandeFournitureTableView.getSelectionModel().getSelectedItem();
         if(selectionCommandeFourniture != null) {
             deleteCommandeFourniturebtn.setDisable(false);
             if (event.getClickCount() == 2) {
                 StartApplication.changeScene("gestionnaire/commandeFourniture/updateCommandeFourniture", "Modification Commande");
                 UpdateCommandeFournitureController controller = (UpdateCommandeFournitureController)
                         StartApplication.getControllerFromStage();
                 controller.initData(selectionCommandeFourniture);
             }
         }
    }

    @FXML
    void onCompleteCommande() throws IOException {
        showAlert(Alert.AlertType.INFORMATION,"Commande Complete","Vous avez complete la commande. Vous allez etre dedirigé vers le control center");
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");
    }

    @FXML
    void onDeleteCommandeFourniture() {
        CommandeFournitureRepository commandeFournitureRepo = new CommandeFournitureRepository();
        CommandeFourniture commandeFourniture =  commandeFournitureTableView.getSelectionModel().getSelectedItem();
        boolean success = commandeFournitureRepo.supprimerUneFournitureByCommandeId(commandeFourniture);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION,"Suppression réussi","La fourniture selectionné a été supprimer de la commande");
        }
    }

    @FXML
    void onRetourControlCenter() throws IOException {
        StartApplication.changeScene("gestionnaire/accueilGestionnaire","Control Center");

    }
    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
