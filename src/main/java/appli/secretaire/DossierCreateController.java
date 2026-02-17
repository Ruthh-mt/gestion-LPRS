package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import session.Session;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class DossierCreateController {
    public TextField filiereTextfield;
    public TextField dateTextfield;
    public TextField heureTextfield;
    public TextArea motivationTextfield;
    public Button validerButton;
    public Button annulerButton;
    public Button retourButton;
    public TextField refFiliereTextfield;
    public TextField refFicheTextfield;
    @FXML
    private Label sessionLabel ;

    public void ajouterDossier() {

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
