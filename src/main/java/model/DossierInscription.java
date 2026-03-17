package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class DossierInscription {

    private java.sql.Date date;
    private Time heure ;
    private int refFiliere ;
    private String motivation ;
    private  int idDossier ;
    private  int ref_fiche ;
    private String nomEtudiant ;
    private String prenomEtudiant ;
    private String nomFiliere ;
    String emailEtudiant ;



    public DossierInscription(java.sql.Date date, Time heure,
                              String motivation, int refFiliere, int ref_fiche) {
        this.date = date;
        this.heure = heure;
        this.motivation = motivation;
        this.refFiliere = refFiliere;
        this.ref_fiche = ref_fiche;
    }
    public DossierInscription(int idDossier ,Date date, Time heure, String motivation, int refFiliere, int ref_fiche) {
        this.idDossier = idDossier;
        this.date = date ;
        this.heure = heure;
        this.refFiliere = refFiliere ;
        this.ref_fiche = ref_fiche;
        this.motivation = motivation;
    }
    public DossierInscription(Date date, Time heure, String motivation,String nomEtudiant , String prenomEtudiant,String emailEtudiant , String nomFiliere, int refFiliere) {
        this.date = date ;
        this.heure = heure;
        this.motivation = motivation;
        this.prenomEtudiant = prenomEtudiant ;
        this.nomEtudiant = nomEtudiant ;
        this.nomFiliere = nomFiliere;
        this.refFiliere = refFiliere ;
    }



    public java.sql.Date getDate() {
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

    public int getRefFiliere() {
        return this.refFiliere;
    }

    public void setFiliere(int  ref_filiere) {
        this.refFiliere = ref_filiere;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getNomFiliere() {
        return this.nomFiliere ;
    }
    public String getEmailEtudiant() {
        return this.emailEtudiant ;
    }

    @Override
    public String toString() {
        return "Date : "+this.date + "\nHeure : "+this.heure+"\nFilière : "+this.refFiliere+"\nMotivation : "+this.motivation;
    }

    public int getId() {
        return idDossier;
    }

    public void setId(int idDossier) {
        this.idDossier = idDossier;
    }

    public int getRef_fiche() {
        return ref_fiche;
    }

    public void setRef_fiche(int ref_fiche) {
        this.ref_fiche = ref_fiche;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }
    public void setNomEtudiant(String nom) {
        this.nomEtudiant = nom;
    }
    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }
    public void setPrenom(String prenom) {
        this.prenomEtudiant = prenom;
    }
}
