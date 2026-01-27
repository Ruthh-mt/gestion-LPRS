package appli.professeur;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlanningController {

    @FXML
    private GridPane planningGrid;

    @FXML
    private DatePicker datePicker;

    // Horaires du planning
    private final LocalTime startTime = LocalTime.of(8, 0);
    private final LocalTime endTime = LocalTime.of(18, 0);

    // Nombre de jours affichés (lundi-vendredi)
    private final int daysToShow = 5;

    @FXML
    public void initialize() {
        // Initialiser le DatePicker à aujourd’hui
        datePicker.setValue(LocalDate.now());

        // Construire la grille de planning
        buildGrid();

        // Écouter les changements de date pour reconstruire la grille
        datePicker.valueProperty().addListener((obs, oldDate, newDate) -> buildGrid());
    }

    // Construire la grille avec les jours et créneaux horaires
    private void buildGrid() {
        planningGrid.getChildren().clear();

        LocalDate startOfWeek = getStartOfWeek(datePicker.getValue());

        // Afficher les jours en entête
        for (int col = 1; col <= daysToShow; col++) {
            LocalDate day = startOfWeek.plusDays(col -1);
            Label dayLabel = new Label(day.getDayOfWeek().toString() + "\n" + day.format(DateTimeFormatter.ofPattern("dd/MM")));
            dayLabel.setStyle("-fx-font-weight: bold; -fx-alignment: center;");
            planningGrid.add(dayLabel, col, 0);
        }

        // Afficher les heures en première colonne (ligne)
        int row = 1;
        for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusHours(1)) {
            Label timeLabel = new Label(time.format(DateTimeFormatter.ofPattern("HH:mm")));
            timeLabel.setStyle("-fx-font-weight: bold; -fx-alignment: center-left;");
            planningGrid.add(timeLabel, 0, row);
            row++;
        }

        // Exemple : ajouter des rendez-vous fictifs
        List<Rendezvous> rdvs = getExampleRendezvous(startOfWeek);

        for (Rendezvous rdv : rdvs) {
            addRendezvousToGrid(rdv, startOfWeek);
        }
    }

    // Calcule le lundi de la semaine contenant la date donnée
    private LocalDate getStartOfWeek(LocalDate date) {
        return date.with(DayOfWeek.MONDAY);
    }

    // Ajoute un rendez-vous dans la grille selon sa date et heure
    private void addRendezvousToGrid(Rendezvous rdv, LocalDate startOfWeek) {
        LocalDate rdvDate = rdv.getDate();
        DayOfWeek dayOfWeek = rdvDate.getDayOfWeek();

        // Vérifie si le rendez-vous est dans la semaine affichée et un jour entre lundi-vendredi
        int col = dayOfWeek.getValue();
        if (col < 1 || col > daysToShow) return;

        // Calculer la ligne en fonction de l'heure (heure entière)
        int hour = rdv.getHeure().getHour();
        int row = hour - startTime.getHour() + 1;
        if (row < 1 || row > (endTime.getHour() - startTime.getHour() +1)) return;

        // Créer une "carte" pour le rendez-vous
        VBox box = new VBox();
        box.setStyle(
                "-fx-background-color: #3498db; " +
                        "-fx-background-radius: 6; " +
                        "-fx-padding: 6; " +
                        "-fx-cursor: hand;"
        );

        Text text = new Text(
                rdv.getHeure().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" +
                        rdv.getSalle() + "\n" +
                        rdv.getEtudiant()
        );
        text.setFill(Color.WHITE);

        box.getChildren().add(text);

        // Ajouter la box dans la grille
        planningGrid.add(box, col, row);
    }

    // Exemple simple de rendez-vous
    private List<Rendezvous> getExampleRendezvous(LocalDate startOfWeek) {
        List<Rendezvous> list = new ArrayList<>();

        // Exemples sur lundi et mercredi
        list.add(new Rendezvous("Jean Dupont", startOfWeek.plusDays(0), LocalTime.of(9, 0), "Salle A101"));
        list.add(new Rendezvous("Marie Curie", startOfWeek.plusDays(2), LocalTime.of(14, 0), "Salle B202"));

        return list;
    }

    // Classe interne simple pour exemple
    public static class Rendezvous {
        private final String etudiant;
        private final LocalDate date;
        private final LocalTime heure;
        private final String salle;

        public Rendezvous(String etudiant, LocalDate date, LocalTime heure, String salle) {
            this.etudiant = etudiant;
            this.date = date;
            this.heure = heure;
            this.salle = salle;
        }

        public String getEtudiant() {
            return etudiant;
        }

        public LocalDate getDate() {
            return date;
        }

        public LocalTime getHeure() {
            return heure;
        }

        public String getSalle() {
            return salle;
        }
    }
}
