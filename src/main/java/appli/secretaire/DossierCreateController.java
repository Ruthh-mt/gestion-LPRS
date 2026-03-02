package appli.secretaire;

import appli.StartApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.DossierInscription;
import model.FicheEtudiant;
import model.Filiere;
import repository.FiliereRepository;
import session.Session;
import java.time.LocalTime ;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class DossierCreateController {


    @FXML
    private ComboBox<Integer> heureTextfield ;
    @FXML
    private ComboBox<Integer> minuteTextfield ;
    @FXML
    public TextArea motivationTextfield;
    @FXML
    public Button validerButton;
    @FXML
    public Button annulerButton;
    @FXML
    public Button retourButton;
    @FXML
    public ComboBox<Filiere> refFiliereTextfield = new ComboBox<>();
    @FXML
    public ComboBox<Integer> refFicheTextfield = new ComboBox<>();
    @FXML
    public DatePicker dateTextField;

    @FXML
    private Label sessionLabel ;

    @FXML
    FiliereRepository filiereRepository = new FiliereRepository();

    @FXML
    public void ajouterDossier() {

    }

    @FXML
    public void initialize() throws SQLException {
        ArrayList<Integer> nombres = new ArrayList<>();
        int i;
        for (i = 0; i < 25; i++) {
            heureTextfield.getItems().addAll(i);
        }

        for (i = 0; i < 61; i++) {
            minuteTextfield.getItems().addAll(i);
        }
    }

    @FXML
    public void redirectionListeDossier() throws IOException {
        StartApplication.changeScene("secretaire/dossierList","Dossier");
    }
}
