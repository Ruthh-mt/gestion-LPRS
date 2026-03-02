package appli.secretaire;

import appli.StartApplication;
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

public class DossierCreateController {



    @FXML
    public ComboBox<String> heureTextfield;
    @FXML
    public ComboBox<String> minuteTextfield;
    @FXML
    public TextArea motivationTextfield;
    @FXML
    public Button validerButton;
    @FXML
    public Button annulerButton;
    @FXML
    public Button retourButton;
    @FXML
    public ComboBox<Integer> refFiliereTextfield;
    @FXML
    public ComboBox<Integer> refFicheTextfield;
    @FXML
    public DatePicker dateTextField;
    @FXML
    private Label sessionLabel ;

    FiliereRepository filiereRepository = new FiliereRepository();

    @FXML
    public void ajouterDossier() {
      int ref_filiere = refFiliereTextfield.getValue();
      int ref_fiche =  refFicheTextfield.getValue();
      Date date = Date.valueOf(dateTextField.getValue());
      int minutes = Integer.parseInt(minuteTextfield.getValue());
      int heures = Integer.parseInt(heureTextfield.getValue());
      String motivation = motivationTextfield.getText();

    }

    @FXML
    public void initialize() throws SQLException {
       ArrayList<Filiere> filieres = new ArrayList<>();
        String[] heures =
                { "Monday", "Tuesday", "Wednesday",
                        "Thursday", "Friday" };
       filieres = filiereRepository.getAllFiliere();
        sessionLabel.setText("Session de ")
        ;
    }

    @FXML
    public void redirectionListeDossier() throws IOException {
        StartApplication.changeScene("secretaire/dossierList","Dossier");
    }
}
