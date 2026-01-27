package appli.professeur;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RendezvousDeleteController {

    @FXML
    private TextField etudiantField;

    @FXML
    private TextField professeurField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField heureField;

    @FXML
    private TextField salleField;

    private boolean deleted = false;

    // Initialise la fenêtre avec les infos du rendez-vous.
    public void setRendezvous(String etudiant, String professeur, String date,
                              String heure, String salle) {
        etudiantField.setText(etudiant);
        professeurField.setText(professeur);
        dateField.setText(date);
        heureField.setText(heure);
        salleField.setText(salle);
    }

    // Action du bouton Annuler (ferme sans supprimer)
    @FXML
    private void onAnnuler() {
        deleted = false;
        closeWindow();
    }

    // Action du bouton Supprimer (confirme la suppression)
    @FXML
    private void onSupprimer() {
        deleted = true;
        closeWindow();
    }

    // Indique si la suppression a été confirmée.
    public boolean isDeleted() {
        return deleted;
    }

    // Ferme la fenêtre actuelle.
    private void closeWindow() {
        Stage stage = (Stage) etudiantField.getScene().getWindow();
        stage.close();
    }
}
