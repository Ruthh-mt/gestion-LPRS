package appli.professeur;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import model.DossierInscription;
import model.RendezVous;
import model.Salle;
import model.Utilisateur;
import repository.DossierRepository;
import repository.RendezVousRepository;
import repository.SalleRepository;
import session.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RendezvousCreateController {

    @FXML
    private ComboBox<DossierInscription> dossierComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField heureField;

    @FXML
    private ComboBox<Salle> salleComboBox;

    @FXML
    private Label erreurLabel;

    private final Utilisateur professeur = Session.getInstance().getUtilisateur();
    private final DossierRepository dossierRepo = new DossierRepository();
    private final SalleRepository salleRepo = new SalleRepository();
    private final RendezVousRepository rdvRepo = new RendezVousRepository();

    @FXML
    public void initialize() throws SQLException {
        erreurLabel.setVisible(false);

        int refUser = Session.getInstance().getUtilisateur().getIdUtilisateur();

        // Charger les dossiers d'inscription → afficher "Prénom NOM"
        List<DossierInscription> dossiers = dossierRepo.getAllDossiers(refUser);
        dossierComboBox.getItems().setAll(dossiers);
        dossierComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(DossierInscription item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null
                        : item.getPrenomEtudiant() + " " + item.getNomEtudiant());
            }
        });
        dossierComboBox.setButtonCell(dossierComboBox.getCellFactory().call(null));

        // Charger les salles disponibles
        List<Salle> salles = salleRepo.findSallesDisponibles();
        salleComboBox.getItems().setAll(salles);
        salleComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Salle item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null
                        : "Salle " + item.getIdSalle() + " (cap. " + item.getCapacite() + ")");
            }
        });
        salleComboBox.setButtonCell(salleComboBox.getCellFactory().call(null));
    }

    @FXML
    void onValiderClick(ActionEvent event) throws IOException {
        DossierInscription dossier = dossierComboBox.getValue();
        LocalDate date = datePicker.getValue();
        String heureStr = heureField.getText().trim();
        Salle salle = salleComboBox.getValue();

        if (dossier == null || date == null || heureStr.isEmpty() || salle == null) {
            afficherErreur("Veuillez remplir tous les champs.");
            return;
        }

        LocalTime heure;
        try {
            heure = LocalTime.parse(heureStr);
        } catch (DateTimeParseException e) {
            afficherErreur("Format d'heure invalide. Utilisez HH:mm (ex : 09:30).");
            return;
        }

        RendezVous rdv = new RendezVous();
        rdv.setDateRendezVous(date);
        rdv.setHeure(heure);
        rdv.setStatus("Prévus");
        rdv.setRefProfesseur(professeur.getIdUtilisateur());
        rdv.setRefDossierInscription(dossier.getId());
        rdv.setRefSalle(salle.getIdSalle());

        if (rdvRepo.creerRendezVous(rdv)) {
            showAlert(Alert.AlertType.INFORMATION, "Rendez-vous créé avec succès.");
            StartApplication.changeScene("professeur/planning", "Planning");
        } else {
            afficherErreur("Erreur lors de la création du rendez-vous.");
        }
    }

    @FXML
    void onAnnulerClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("professeur/planning", "Planning");
    }

    @FXML
    void onProfilClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("profil/profilRead", "Profil");
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("professeur/planning", "Planning");
    }

    private void afficherErreur(String message) {
        erreurLabel.setText(message);
        erreurLabel.setVisible(true);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Rendez-vous");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}