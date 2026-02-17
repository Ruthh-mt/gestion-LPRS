package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.FicheEtudiant;
import session.Session;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.format.DateTimeFormatter;

public class DossierCreateController {


    @FXML
    public TextField dateTextfield;
    @FXML
    public TextField heureTextfield;
    @FXML
    public TextArea motivationTextfield;
    @FXML
    public Button validerButton;
    @FXML
    public Button annulerButton;
    @FXML
    public Button retourButton;
    @FXML
    public TextField refFiliereTextfield;
    @FXML
    public TextField refFicheTextfield;
    @FXML
    private Label sessionLabel ;

    @FXML
    public void ajouterDossier() {
      int ref_filiere = Integer.parseInt(refFiliereTextfield.getText());
      int ref_fiche =  Integer.parseInt(refFicheTextfield.getText());
      Date date = Date.valueOf(dateTextfield.getText());
      Time heure = Time.valueOf(heureTextfield.getText());
      String motivation = motivationTextfield.getText();


    }

    @FXML
    public void initialize() {
        sessionLabel.setText("Session de ");
    }

    @FXML
    public void redirectionListeDossier() throws IOException {
        StartApplication.changeScene("secretaire/dossierList","Dossier");
    }
}
