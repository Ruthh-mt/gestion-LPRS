package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class RendezVous {

    private int idRendezVous;
    private LocalDate dateRendezVous;
    private LocalTime heure;
    private String status; // 'Prévus', 'Annulé', 'Passé'
    private int refProfesseur;
    private int refDossierInscription;
    private int refSalle;

    public RendezVous() {}

    public RendezVous(LocalDate dateRendezVous, LocalTime heure, String status,
                      int refProfesseur, int refDossierInscription, int refSalle) {
        this.dateRendezVous = dateRendezVous;
        this.heure = heure;
        this.status = status;
        this.refProfesseur = refProfesseur;
        this.refDossierInscription = refDossierInscription;
        this.refSalle = refSalle;
    }

    public RendezVous(int idRendezVous, LocalDate dateRendezVous, LocalTime heure, String status,
                      int refProfesseur, int refDossierInscription, int refSalle) {
        this.idRendezVous = idRendezVous;
        this.dateRendezVous = dateRendezVous;
        this.heure = heure;
        this.status = status;
        this.refProfesseur = refProfesseur;
        this.refDossierInscription = refDossierInscription;
        this.refSalle = refSalle;
    }

    public int getIdRendezVous() { return idRendezVous; }
    public void setIdRendezVous(int idRendezVous) { this.idRendezVous = idRendezVous; }

    public LocalDate getDateRendezVous() { return dateRendezVous; }
    public void setDateRendezVous(LocalDate dateRendezVous) { this.dateRendezVous = dateRendezVous; }

    public LocalTime getHeure() { return heure; }
    public void setHeure(LocalTime heure) { this.heure = heure; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getRefProfesseur() { return refProfesseur; }
    public void setRefProfesseur(int refProfesseur) { this.refProfesseur = refProfesseur; }

    public int getRefDossierInscription() { return refDossierInscription; }
    public void setRefDossierInscription(int refDossierInscription) { this.refDossierInscription = refDossierInscription; }

    public int getRefSalle() { return refSalle; }
    public void setRefSalle(int refSalle) { this.refSalle = refSalle; }

    @Override
    public String toString() {
        return "RDV " + idRendezVous + " | " + dateRendezVous + " " + heure + " | " + status;
    }
}