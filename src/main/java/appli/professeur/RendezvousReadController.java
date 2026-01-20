package appli.professeur;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RendezvousReadController {

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

    /**
     * Méthode appelée automatiquement après le chargement du FXML
     */
    @FXML
    public void initialize() {
        // Rien par défaut
        // Les données seront injectées via setRendezvous(...)
    }

    /**
     * Injection des données du rendez-vous
     * (à appeler depuis le controller parent ou la navigation)
     */
    public void setRendezvous(
            String etudiant,
            String professeur,
            String date,
            String heure,
            String salle,
            String statut
    ) {
        etudiantField.setText(etudiant);
        professeurField.setText(professeur);
        dateField.setText(date);
        heureField.setText(heure);
        salleField.setText(salle);
        statutField.setText(statut);
    }

    /* =======================
       Actions des boutons
       ======================= */

    @FXML
    private void onAnnuler() {
        closeWindow();
    }

    @FXML
    private void onEditer() {
        // Navigation vers rendezvousUpdateView.fxml
        // Exemple :
        // Router.goToUpdate(rendezvousId);
        System.out.println("Éditer le rendez-vous");
    }

    @FXML
    private void onSupprimer() {
        // Navigation vers rendezvousDeleteView.fxml
        // Exemple :
        // Router.goToDelete(rendezvousId);
        System.out.println("Supprimer le rendez-vous");
    }

    private void closeWindow() {
        Stage stage = (Stage) etudiantField.getScene().getWindow();
        stage.close();
    }
}
