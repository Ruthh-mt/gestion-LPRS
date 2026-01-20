package model;

import java.sql.Time;
import java.util.Date;

public class DossierInscription {

    private Date date;
    private Time heure ;
    private String filiere ;
    private String motivation ;
    private  int id ;


    public DossierInscription(Date date, Time heure, String filiere, String motivation) {
        this.date = date;
        this.heure = heure;
        this.filiere = filiere;
        this.motivation = motivation;
    }
    public DossierInscription(int id , Date date, Time heure, String filiere, String motivation) {
        this.date = date;
        this.heure = heure;
        this.filiere = filiere;
        this.motivation = motivation;
    }



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

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }


    @Override
    public String toString() {
        return "Date : "+this.date + "\nHeure : "+this.heure+"\nFilière : "+this.filiere+"\nMotivation : "+this.motivation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
