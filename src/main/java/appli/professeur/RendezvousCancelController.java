package appli.professeur;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RendezvousCancelController {

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

    @FXML
    private TextField statutField;

    private boolean cancelled = false;
// Initialise la fenêtre avec les infos du rendez-vous.
    public void setRendezvous(String etudiant, String professeur, String date,
                              String heure, String salle, String statut) {
        etudiantField.setText(etudiant);
        professeurField.setText(professeur);
        dateField.setText(date);
        heureField.setText(heure);
        salleField.setText(salle);
        statutField.setText(statut);
    }
//Appelé quand l’utilisateur clique sur Annuler (fermer sans annuler)
    @FXML
    private void onCancel() {
        cancelled = false;
        closeWindow();
    }
//Appelé quand l’utilisateur confirme l’annulation
    @FXML
    private void onConfirmCancel() {
        cancelled = true;
        closeWindow();
    }
//Pour savoir si l’utilisateur a confirmé l’annulation.
    public boolean isCancelled() {
        return cancelled;
    }

    private void closeWindow() {
        Stage stage = (Stage) etudiantField.getScene().getWindow();
        stage.close();
    }
}
