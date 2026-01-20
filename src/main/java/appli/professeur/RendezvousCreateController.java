package appli.professeur;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RendezvousCreateController {

    @FXML
    private TextField etudiantField; // ou Label selon ton FXML

    @FXML
    private TextField professeurField; // ou Label selon ton FXML

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> heureSpinner; // pour heures (0-23)

    @FXML
    private Spinner<Integer> minuteSpinner; // pour minutes (0-59)

    @FXML
    private ChoiceBox<String> salleChoiceBox;

    @FXML
    private Button validerButton;

    @FXML
    private Button annulerButton;

    // Initialisation automatique après chargement FXML
    @FXML
    public void initialize() {
        // Initialiser spinners heures et minutes
        heureSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 9));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 5));

        // Exemple : initialiser les salles disponibles
        salleChoiceBox.setItems(FXCollections.observableArrayList(
                "Salle A101", "Salle B202", "Salle C303"
        ));
        salleChoiceBox.getSelectionModel().selectFirst();

        // Valider désactivé par défaut (optionnel)
        validerButton.setDisable(true);

        // Activer bouton valider si date choisie
        datePicker.valueProperty().addListener((obs, oldV, newV) -> {
            validerButton.setDisable(newV == null);
        });
    }

    // Injection des noms (non modifiables)
    public void setEtudiantProfesseur(String etudiant, String professeur) {
        etudiantField.setText(etudiant);
        professeurField.setText(professeur);

        etudiantField.setEditable(false);
        professeurField.setEditable(false);
    }

    // Action du bouton Valider
    @FXML
    private void onValider() {
        LocalDate date = datePicker.getValue();
        Integer heure = heureSpinner.getValue();
        Integer minute = minuteSpinner.getValue();
        String salle = salleChoiceBox.getValue();

        if (date == null) {
            showAlert("Erreur", "La date doit être renseignée.");
            return;
        }

        LocalTime time = LocalTime.of(heure, minute);
        String datetimeStr = date.format(DateTimeFormatter.ISO_DATE) + " " + time.toString();

        // TODO: appeler un service pour créer le rendez-vous
        System.out.println("Création RDV : " + datetimeStr + ", Salle: " + salle);

        // Fermer la fenêtre après succès
        closeWindow();
    }

    // Action du bouton Annuler
    @FXML
    private void onAnnuler() {
        closeWindow();
    }

    // Affiche une alerte simple
    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Ferme la fenêtre actuelle
    private void closeWindow() {
        Stage stage = (Stage) validerButton.getScene().getWindow();
        stage.close();
    }
}
