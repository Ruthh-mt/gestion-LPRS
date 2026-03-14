package appli.professeur;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import model.RendezVous;
import repository.RendezVousRepository;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class RendezvousReadController {

    @FXML
    private Label dateLabel;

    @FXML
    private Label heureLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label salleLabel;

    @FXML
    private Label dossierLabel;

    private RendezVous rdv;
    private final RendezVousRepository rdvRepo = new RendezVousRepository();

    public void initData(RendezVous rdv) {
        this.rdv = rdv;

        dateLabel.setText(rdv.getDateRendezVous().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        heureLabel.setText(rdv.getHeure().format(DateTimeFormatter.ofPattern("HH:mm")));
        statusLabel.setText(rdv.getStatus());
        salleLabel.setText("Salle " + rdv.getRefSalle());
        dossierLabel.setText("Dossier n°" + rdv.getRefDossierInscription());

        // Couleur du statut
        String couleur;
        switch (rdv.getStatus()) {
            case "Prévus":
                couleur = "#4CAF50";
                break;
            case "Annulé":
                couleur = "#F44336";
                break;
            case "Passé":
                couleur = "#9E9E9E";
                break;
            default:
                couleur = "#2196F3";
                break;
        }
        statusLabel.setStyle("-fx-background-color: " + couleur + "; "
                + "-fx-text-fill: white; -fx-background-radius: 5; "
                + "-fx-padding: 4 10 4 10; -fx-font-family: Consolas; -fx-font-size: 14;");
    }

    @FXML
    void onModifierRdvClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("professeur/rendezvousUpdate", "Modifier le rendez-vous");
        RendezvousUpdateController controller = (RendezvousUpdateController)
                StartApplication.getControllerFromStage();
        controller.initData(rdv);
    }

    @FXML
    void onAnnulerRdvClick(ActionEvent event) throws IOException {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Annulation");
        confirm.setHeaderText(null);
        confirm.setContentText("Voulez-vous vraiment annuler ce rendez-vous ?");
        Optional<ButtonType> choix = confirm.showAndWait();

        if (choix.isPresent() && choix.get() == ButtonType.OK) {
            if (rdvRepo.updateStatut(rdv.getIdRendezVous(), "Annulé")) {
                showAlert(Alert.AlertType.INFORMATION, "Le rendez-vous a été annulé.");
                StartApplication.changeScene("professeur/planning", "Planning");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur lors de l'annulation.");
            }
        }
    }

    @FXML
    void onSupprimerRdvClick(ActionEvent event) throws IOException {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Suppression");
        confirm.setHeaderText(null);
        confirm.setContentText("Voulez-vous vraiment supprimer ce rendez-vous ?");
        Optional<ButtonType> choix = confirm.showAndWait();

        if (choix.isPresent() && choix.get() == ButtonType.OK) {
            if (rdvRepo.supprimerRendezVous(rdv.getIdRendezVous())) {
                showAlert(Alert.AlertType.INFORMATION, "Le rendez-vous a été supprimé.");
                StartApplication.changeScene("professeur/planning", "Planning");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur lors de la suppression.");
            }
        }
    }

    @FXML
    void onProfilClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("profil/profilRead", "Profil");
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("professeur/planning", "Planning");
    }
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Rendez-vous");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}