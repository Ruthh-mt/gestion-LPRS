package model.gestionnaire;

public class Demande {
    private int idDemande;
    private boolean estValide;
    private int refProfesseur;
    private int refGestionnaire;
    private String raisonDemande;
    private String status;
    private String urgence;
    private String dateDemande;

    public Demande(int idDemande, boolean estValide, int refProfesseur, int refGestionnaire, String raisonDemande, String status, String urgence, String dateDemande) {
        this.idDemande = idDemande;
        this.estValide = estValide;
        this.refProfesseur = refProfesseur;
        this.refGestionnaire = refGestionnaire;
        this.raisonDemande = raisonDemande;
        this.status = status;
        this.urgence = urgence;
        this.dateDemande = dateDemande;
    }

    public Demande(boolean estValide, int refProfesseur, int refGestionnaire, String raisonDemande, String status, String urgence, String dateDemande) {
        this.estValide = estValide;
        this.refProfesseur = refProfesseur;
        this.refGestionnaire = refGestionnaire;
        this.raisonDemande = raisonDemande;
        this.status = status;
        this.urgence = urgence;
        this.dateDemande = dateDemande;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    public int getRefProfesseur() {
        return refProfesseur;
    }

    public void setRefProfesseur(int refProfesseur) {
        this.refProfesseur = refProfesseur;
    }

    public int getRefGestionnaire() {
        return refGestionnaire;
    }

    public void setRefGestionnaire(int refGestionnaire) {
        this.refGestionnaire = refGestionnaire;
    }

    public String getRaisonDemande() {
        return raisonDemande;
    }

    public void setRaisonDemande(String raisonDemande) {
        this.raisonDemande = raisonDemande;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrgence() {
        return urgence;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public String getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(String dateDemande) {
        this.dateDemande = dateDemande;
    }
}
