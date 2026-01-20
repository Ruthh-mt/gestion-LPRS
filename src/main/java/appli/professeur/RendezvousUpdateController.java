package appli.professeur;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RendezvousUpdateController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Spinner<Integer> heureSpinner;

    @FXML
    private Spinner<Integer> minuteSpinner;

    @FXML
    private ChoiceBox<String> salleChoiceBox;

    @FXML
    private ChoiceBox<String> statutChoiceBox;

    @FXML
    private Button validerButton;

    @FXML
    private Button annulerButton;

    // Pour stocker l'ID du rendez-vous, si besoin
    private int rendezvousId;

    @FXML
    public void initialize() {
        // Initialiser les spinners pour heure et minute
        heureSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 9));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 5));

        // Exemple : initialiser les salles
        salleChoiceBox.setItems(FXCollections.observableArrayList(
                "Salle A101", "Salle B202", "Salle C303"
        ));

        // Initialiser les statuts possibles
        statutChoiceBox.setItems(FXCollections.observableArrayList(
                "Actif", "Annulé"
        ));

        // Sélection par défaut
        salleChoiceBox.getSelectionModel().selectFirst();
        statutChoiceBox.getSelectionModel().selectFirst();
    }

    // Injecter les données existantes du rendez-vous
    public void setRendezvous(int id, LocalDate date, LocalTime heure, String salle, String statut) {
        this.rendezvousId = id;
        datePicker.setValue(date);
        heureSpinner.getValueFactory().setValue(heure.getHour());
        minuteSpinner.getValueFactory().setValue(heure.getMinute());

        salleChoiceBox.getSelectionModel().select(salle);
        statutChoiceBox.getSelectionModel().select(statut);
    }

    @FXML
    private void onValider() {
        LocalDate date = datePicker.getValue();
        Integer heure = heureSpinner.getValue();
        Integer minute = minuteSpinner.getValue();
        String salle = salleChoiceBox.getValue();
        String statut = statutChoiceBox.getValue();

        if (date == null) {
            showAlert("Erreur", "La date doit être renseignée.");
            return;
        }

        LocalTime time = LocalTime.of(heure, minute);

        // TODO: Mettre à jour en BDD ou service
        System.out.printf("Mise à jour RDV id=%d : %s %s, Salle: %s, Statut: %s%n",
                rendezvousId, date.toString(), time.toString(), salle, statut);

        closeWindow();
    }

    @FXML
    private void onAnnuler() {
        closeWindow();
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) validerButton.getScene().getWindow();
        stage.close();
    }
}
