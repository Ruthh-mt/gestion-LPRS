package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.FicheEtudiant;
import model.Filiere;
import repository.FiliereRepository;
import session.Session;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DossierCreateController {



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
      int ref_filiere = Integer.parseInt(refFiliereTextfield.getText());
      int ref_fiche =  Integer.parseInt(refFicheTextfield.getText());
      Date date = Date.valueOf(dateTextfield.getText());
      Time heure = Time.valueOf(heureTextfield.getText());
      String motivation = motivationTextfield.getText();
    }

    @FXML
    public void initialize() throws SQLException {
       ArrayList<Filiere> filieres = new ArrayList<>();
       filieres = filiereRepository.getAllFiliere();
        sessionLabel.setText("Session de ")
        ;
    }

    @FXML
    public void redirectionListeDossier() throws IOException {
        StartApplication.changeScene("secretaire/dossierList","Dossier");
    }
}
