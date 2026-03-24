package appli.secretaire;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.LocalTimeStringConverter;
import model.DossierInscription;
import model.FicheEtudiant;
import model.Filiere;
import model.Utilisateur;
import repository.DossierRepository;
import repository.FicheEtudiantRepository;
import repository.FiliereRepository;
import session.Session;
import session.SessionDossier;
import session.SessionFiche;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DossierUpdateController {

    @FXML
    public Spinner<LocalTime> heureTextfield;

    @FXML
    private Button annulerButton;

    @FXML
    private DatePicker dateTextField;

    @FXML
    public Spinner<LocalTime> heureTextField;

    @FXML
    private TextArea motivationTextfield;

    @FXML
    private ComboBox<FicheEtudiant> refFicheTextfield;

    @FXML
    private ComboBox<Filiere> refFiliereTextfield;

    @FXML
    private Button retourButton;
    @FXML
    private Label sessionLabel;
    @FXML
    private Label erreurLabel;
    @FXML
    private Button validerButton;

    private Utilisateur userActuel = Session.getInstance().getUtilisateur();
    private DossierInscription dossier_actuel;
    private DossierRepository dossierRepository = new DossierRepository();
    private FiliereRepository filiereRepository = new FiliereRepository();
    private FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    @FXML
    public void initialize() throws SQLException {
        sessionLabel.setText("Session de " + userActuel.getNom() + userActuel.getPrenom());
        erreurLabel.setVisible(false);
        this.sessionLabel.setText("Session de " + userActuel.getNom());

        //---------Remplir comboBox Ref Filiere-------------------------------//
        ArrayList<Filiere> filieres = null;
        try {
            filieres = new ArrayList<>(filiereRepository.getAllFilieres());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Filiere filiere : filieres) {
            refFiliereTextfield.getItems().add(filiere);
        }
        //----------------------Remplir comboBox Ref Fiche-----------------------------------------------
        ArrayList<FicheEtudiant> ficheEtudiants = null;
        try {
            ficheEtudiants = new ArrayList<>(ficheEtudiantRepository.getToutesLesFiches(Session.getInstance().getUtilisateur().getIdUtilisateur()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (FicheEtudiant ficheEtudiant : ficheEtudiants) {
            refFicheTextfield.getItems().add(ficheEtudiant);
        }
    }

    @FXML
    public void initData(DossierInscription dossierInscription) throws SQLException {

        this.dossier_actuel = dossierInscription;
        System.out.println(this.dossier_actuel);
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<LocalTime>() {
            private LocalTime time = LocalTime.now();

            @Override
            public void decrement(int steps) {
                time = time.minusMinutes(steps);
                setValue(time);
            }

            @Override
            public void increment(int steps) {
                time = time.plusMinutes(steps);
                setValue(time);
            }
        };

        //REQUETE RECUPERER FICHE
        int idDossier = this.dossier_actuel.getId();
        Filiere filiereTrouve = filiereRepository.getFiliere(this.dossier_actuel.getRefFiliere());
        FicheEtudiant ficheTrouve = ficheEtudiantRepository.getFicheEtudiant(this.dossier_actuel.getRef_fiche());
        valueFactory.setConverter(new LocalTimeStringConverter(DateTimeFormatter.ofPattern("HH:mm"), null));
        heureTextfield.setValueFactory(valueFactory);
        heureTextfield.getValueFactory().setValue(LocalTime.now());
        //SET DATE
        dateTextField.setValue(dossier_actuel.getDate().toLocalDate());
        //SET HEURE
        heureTextfield.getValueFactory().setValue(dossier_actuel.getHeure().toLocalTime());
        // SET MOTIVATION
        motivationTextfield.setText(dossier_actuel.getMotivation());
        //SET FILIERE
        refFiliereTextfield.setValue(filiereTrouve);
        // SET FICHE
        refFicheTextfield.setValue(ficheTrouve);
    }

    @FXML
    public void updateDossier() throws SQLException, IOException {
        java.sql.Date date = java.sql.Date.valueOf(dateTextField.getValue());
        Time heure = Time.valueOf(heureTextfield.getValue());
        String motivation = motivationTextfield.getText();
        int ref_filiere = refFiliereTextfield.getValue().getIdFiliere();
        int ref_fiche = refFicheTextfield.getValue().getIdFicheEtudiante();
        int idActuel = dossier_actuel.getId();
        if(motivation.isEmpty()){
            erreurLabel.setVisible(true);
            erreurLabel.setText("Motivation non remplie");
        }
        DossierInscription dossier = new DossierInscription(idActuel ,date, heure, motivation, ref_filiere, ref_fiche);
       boolean ok = dossierRepository.mettreAjourDossier(dossier);
       if (ok) {
           erreurLabel.setVisible(true);
           erreurLabel.setText("Dossier modifié avec succès");
           erreurLabel.setStyle("-fx-text-fill: green;");

       }
    }

    @FXML
    public void redirectionListDossier(ActionEvent actionEvent) throws IOException {
        StartApplication.changeScene("secretaire/dossierList", "Liste des dossiers");
    }

    @FXML
    public void annuler(ActionEvent actionEvent) throws IOException, SQLException {
        SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<LocalTime>() {
            private LocalTime time = LocalTime.now();

            @Override
            public void decrement(int steps) {
                time = time.minusMinutes(steps);
                setValue(time);
            }

            @Override
            public void increment(int steps) {
                time = time.plusMinutes(steps);
                setValue(time);
            }
        };

        //REQUETE RECUPERER DOSSIER
        int idDossier = this.dossier_actuel.getId();
        Filiere filiereTrouve = filiereRepository.getFiliere(this.dossier_actuel.getRefFiliere());
        FicheEtudiant ficheTrouve = ficheEtudiantRepository.getFicheEtudiant(this.dossier_actuel.getRef_fiche());
        valueFactory.setConverter(new LocalTimeStringConverter(DateTimeFormatter.ofPattern("HH:mm"), null));
        heureTextfield.setValueFactory(valueFactory);
        heureTextfield.getValueFactory().setValue(LocalTime.now());
        //SET DATE
        dateTextField.setValue(dossier_actuel.getDate().toLocalDate());
        //SET HEURE
        heureTextfield.getValueFactory().setValue(dossier_actuel.getHeure().toLocalTime());
        // SET MOTIVATION
        motivationTextfield.setText(dossier_actuel.getMotivation());

        //SET FILIERE
        refFiliereTextfield.setValue(filiereTrouve);
        // SET FICHE
        refFicheTextfield.setValue(ficheTrouve);
    }


}





