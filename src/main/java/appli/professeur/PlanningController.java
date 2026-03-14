package appli.professeur;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.RendezVous;
import repository.RendezVousRepository;
import session.Session;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class PlanningController {

    @FXML
    private Label moisLabel;

    @FXML
    private GridPane joursSemaineGrid;

    @FXML
    private GridPane calendrierGrid;

    private YearMonth moisActuel;

    private final RendezVousRepository rdvRepo = new RendezVousRepository();

    @FXML
    public void initialize() {
        moisActuel = YearMonth.now();
        construireEnTetes();
        chargerCalendrier();
    }

    // ── Navigation ──────────────────────────────────────────────

    @FXML
    void onMoisPrecedentClick(ActionEvent event) {
        moisActuel = moisActuel.minusMonths(1);
        chargerCalendrier();
    }

    @FXML
    void onMoisSuivantClick(ActionEvent event) {
        moisActuel = moisActuel.plusMonths(1);
        chargerCalendrier();
    }

    @FXML
    void onAujourdhuiClick(ActionEvent event) {
        moisActuel = YearMonth.now();
        chargerCalendrier();
    }

    // ── Construction du calendrier ──────────────────────────────

    private void construireEnTetes() {
        String[] jours = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
        for (int i = 0; i < 7; i++) {
            Label label = new Label(jours[i]);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-font-family: Consolas;");
            joursSemaineGrid.add(label, i, 0);
        }
    }

    private void chargerCalendrier() {
        calendrierGrid.getChildren().clear();

        // Mettre à jour le titre
        String titre = moisActuel.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH)
                + " " + moisActuel.getYear();
        moisLabel.setText(titre.substring(0, 1).toUpperCase() + titre.substring(1));

        // Récupérer les rendez-vous du professeur
        int refProf = Session.getInstance().getUtilisateur().getIdUtilisateur();
        List<RendezVous> tousLesRdv = rdvRepo.findByProfesseur(refProf);

        // Calculer le jour de départ (lundi = 0)
        LocalDate premierJour = moisActuel.atDay(1);
        int decalage = premierJour.getDayOfWeek().getValue() - 1; // Lundi=0 ... Dimanche=6

        int totalJours = moisActuel.lengthOfMonth();

        for (int jour = 1; jour <= totalJours; jour++) {
            int cellIndex = decalage + jour - 1;
            int colonne = cellIndex % 7;
            int ligne = cellIndex / 7;

            LocalDate dateJour = moisActuel.atDay(jour);

            VBox cellule = creerCellule(jour, dateJour, tousLesRdv);
            calendrierGrid.add(cellule, colonne, ligne);
        }
    }

    private VBox creerCellule(int jour, LocalDate date, List<RendezVous> rdvList) {
        VBox cellule = new VBox(2);
        cellule.setPadding(new Insets(4));
        cellule.setAlignment(Pos.TOP_LEFT);

        // Style de base
        String bgColor = "#FFFFFF";
        if (date.equals(LocalDate.now())) {
            bgColor = "#E3F2FD"; // bleu clair pour aujourd'hui
        }
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            bgColor = "#F5F5F5"; // gris clair pour le week-end
        }
        cellule.setStyle("-fx-background-color: " + bgColor + "; "
                + "-fx-border-color: #DDDDDD; -fx-border-width: 0.5;");

        // Numéro du jour
        Label numJour = new Label(String.valueOf(jour));
        if (date.equals(LocalDate.now())) {
            numJour.setStyle("-fx-font-weight: bold; -fx-font-size: 13; "
                    + "-fx-text-fill: white; -fx-background-color: #1976D2; "
                    + "-fx-background-radius: 50; -fx-padding: 2 6 2 6; -fx-font-family: Consolas;");
        } else {
            numJour.setStyle("-fx-font-size: 12; -fx-text-fill: #555555; -fx-font-family: Consolas;");
        }
        cellule.getChildren().add(numJour);

        // Ajouter les rendez-vous du jour
        for (RendezVous rdv : rdvList) {
            if (rdv.getDateRendezVous().equals(date)) {
                Label rdvLabel = creerLabelRdv(rdv);
                cellule.getChildren().add(rdvLabel);
            }
        }

        return cellule;
    }

    private Label creerLabelRdv(RendezVous rdv) {
        String heureStr = rdv.getHeure().format(DateTimeFormatter.ofPattern("HH:mm"));
        Label label = new Label(heureStr + " - " + rdv.getStatus());
        label.setMaxWidth(Double.MAX_VALUE);

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

        label.setStyle("-fx-background-color: " + couleur + "; "
                + "-fx-text-fill: white; -fx-font-size: 10; "
                + "-fx-background-radius: 3; -fx-padding: 2 4 2 4; "
                + "-fx-font-family: Consolas; -fx-cursor: hand;");

        label.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    StartApplication.changeScene("professeur/rendezvousRead", "Détails du rendez-vous");
                    RendezvousReadController controller = (RendezvousReadController)
                            StartApplication.getControllerFromStage();
                    controller.initData(rdv);
                } catch (IOException e) {
                    System.out.println("Erreur ouverture rendez-vous : " + e.getMessage());
                }
            }
        });

        return label;
    }

    // ── Navigation pages ────────────────────────────────────────

    @FXML
    void onProfilClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("profil/profilRead", "Profil");
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/homePage", "Accueil");
    }

    @FXML
    void onRdvClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("professeur/rendezvousCreate", "Rendez-vous");
    }
}