package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class DossierInscription {

    private LocalDate date;
    private Time heure ;
    private int ref_filiere ;
    private String motivation ;
    private  int id ;
    private  int ref_fiche ;



    public DossierInscription(LocalDate date, Time heure,
                              String motivation, int ref_filiere, int ref_fiche) {
        this.date = date;
        this.heure = heure;
        this.motivation = motivation;
        this.ref_filiere = ref_filiere;
        this.ref_fiche = ref_fiche;
    }
    public DossierInscription(int id ,Date date, Time heure, String motivation, int ref_filiere, int ref_fiche) {
        this.date = date.toLocalDate();
        this.heure = heure;
        this.ref_filiere = ref_filiere;
        this.motivation = motivation;
    }



    public LocalDate getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date.toLocalDate();
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


    @Override
    public String toString() {
        return "Date : "+this.date + "\nHeure : "+this.heure+"\nFilière : "+this.ref_filiere+"\nMotivation : "+this.motivation;
    }

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
}
