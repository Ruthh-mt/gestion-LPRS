package appli.secretaire;

import appli.StartApplication;
import appli.accueil.TimeSpinner;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.LocalTimeStringConverter;
import model.DossierInscription;
import model.FicheEtudiant;
import model.Filiere;
import repository.DossierRepository;
import repository.FicheEtudiantRepository;
import repository.FiliereRepository;
import session.Session;

import javax.print.DocFlavor;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime ;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DossierCreateController implements Initializable {

    public Spinner<LocalTime> heureTextfield;

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
    public ComboBox<FicheEtudiant> refFicheTextfield = new ComboBox<>();
    @FXML
    public DatePicker dateTextField;

    @FXML
    private Label sessionLabel ;

    @FXML
    private Label erreurLabel;


    FiliereRepository filiereRepository = new FiliereRepository();
    DossierRepository dossierRepository = new DossierRepository();
    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    @FXML
    public void ajouterDossier() throws SQLException, IOException {
     java.sql.Date date = Date.valueOf(dateTextField.getValue());    ;
        Time heure = Time.valueOf(heureTextfield.getValue());
      String motivation = motivationTextfield.getText();
      int refFiliere = refFiliereTextfield.getValue().getIdFiliere();
      int refFiche = refFicheTextfield.getValue().getIdFicheEtudiante();

        System.out.println("Date : "+date);
        System.out.println("Heure : "+heure);
        System.out.println("Motivation : "+motivation);
        System.out.println("RefFiliere : "+refFiliere);
        System.out.println("RefFiche : "+refFiche);
        DossierInscription dossierInscription = new DossierInscription(date, heure, motivation, refFiliere, refFiche);
        boolean ok = dossierRepository.ajouterDossier(dossierInscription);
        if(ok){
            erreurLabel.setVisible(true);
              erreurLabel.setText("Dossier ajouté avec succès");
            erreurLabel.setStyle("-fx-text-fill: green;");
            dateTextField.getEditor().clear();
            heureTextfield.getEditor().clear();
            motivationTextfield.clear();
            refFiliereTextfield.getItems().clear();
            refFicheTextfield.getItems().clear();
            StartApplication.changeScene("secretaire/dossierCreate","creer un dossier");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<LocalTime>() {
            private LocalTime time = LocalTime.now(); @Override public void decrement(int steps) {
                time = time.minusMinutes(steps); setValue(time);
            }
            @Override public void increment(int steps) {
                time = time.plusMinutes(steps); setValue(time);
            }
        };
        valueFactory.setConverter(new LocalTimeStringConverter( DateTimeFormatter.ofPattern("HH:mm"), null));
        heureTextfield.setValueFactory(valueFactory);
        heureTextfield.getValueFactory().setValue(LocalTime.now());
        //----------------------------------------//
        ArrayList<Filiere> filieres = null;
        try {
            filieres = new ArrayList<>(filiereRepository.getAllFilieres());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Filiere filiere : filieres) {
            refFiliereTextfield.getItems().add(filiere);
        }
        //---------------------------------------------------------------------
        ArrayList<FicheEtudiant> ficheEtudiants = null;
        try {
            ficheEtudiants = new ArrayList<>(ficheEtudiantRepository.getToutesLesFiches(Session.getInstance().getUtilisateur().getIdUtilisateur()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(FicheEtudiant ficheEtudiant: ficheEtudiants) {
            refFicheTextfield.getItems().add(ficheEtudiant);
        }

     erreurLabel.setVisible(false);
    }


    @FXML
    public void redirectionListeDossier() throws IOException {
        StartApplication.changeScene("secretaire/dossierList","Dossier");
    }

}
