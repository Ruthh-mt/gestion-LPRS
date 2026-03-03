package model;

import java.sql.Time;
import java.util.Date;

public class DossierInscription {
    private int id;
    private Date date;
    private Time heure ;
    private int ref_filiere ;
    private String motivation ;
    private  int ref_fiche ;
    private String nomEtudiant;
    private String prenomEtudiant;

    public DossierInscription(Date date, Time heure,
                               String motivation, int ref_filiere, int ref_fiche, String motivation) {
        this.date = date;
        this.heure = heure;
        this.motivation = motivation;
        this.ref_filiere = ref_filiere;
        this.ref_fiche = ref_fiche;
        this.motivation = motivation;
    }
    public DossierInscription(int id , Date date, Time heure, String motivation,int ref_filiere, int ref_fiche) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.ref_filiere = ref_filiere;
        this.motivation = motivation;
        this.ref_fiche = ref_fiche;
    }

    public DossierInscription(int id, Date date, Time heure, String filiere, String motivation,
                              String prenomEtudiant, String nomEtudiant) {
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.ref_filiere = filiere;
        this.motivation = motivation;
        this.prenomEtudiant = prenomEtudiant;
        this.nomEtudiant = nomEtudiant;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }
    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public int getFiliere() {
        return this.ref_filiere;
    }
    public void setFiliere(int  ref_filiere) {
        this.ref_filiere = ref_filiere;
    }

    public String getMotivation() {
        return motivation;
    }
    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getNomEtudiant() { return nomEtudiant; }
    public void setNomEtudiant(String nomEtudiant) { this.nomEtudiant = nomEtudiant; }

    public String getPrenomEtudiant() { return prenomEtudiant; }
    public void setPrenomEtudiant(String prenomEtudiant) { this.prenomEtudiant = prenomEtudiant; }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getRef_fiche() {
        return ref_fiche;
    }
    public void setRef_fiche(int ref_fiche) {
        this.ref_fiche = ref_fiche;
    }

    public FicheEtudiant getFicheEtudiante() { }
    public int getIdDossierInscription() { }

    @Override
    public String toString() {
        return "Dossier n°" +id+ " | " + prenomEtudiant + " " + nomEtudiant "\nDate : "+this.date + "\nHeure : "+this.heure+"\nFilière : "+this.ref_filiere+"\nMotivation : "+this.motivation;
    }
}
