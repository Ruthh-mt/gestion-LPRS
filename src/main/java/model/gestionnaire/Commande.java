package model.gestionnaire;

import model.Utilisateur;

public class Commande {
    private int idCommande;
    private String raisonCommande;
    private Utilisateur refGestionnaire;
    private Fournisseur refFournisseur;
    private String nomCommande;
    private String dateCommande;
    private String status;
    private String dateLivraison;


    public Commande(int idCommande, String raisonCommande, Utilisateur refGestionnaire, Fournisseur refFournisseur, String nomCommande, String dateCommande,String status,String dateLivraison) {
        this.idCommande = idCommande;
        this.raisonCommande = raisonCommande;
        this.refGestionnaire = refGestionnaire;
        this.refFournisseur = refFournisseur;
        this.nomCommande = nomCommande;
        this.dateCommande = dateCommande;
        this.status = status;
        this.dateLivraison = dateLivraison;


    }

    public Commande(String raisonCommande, Utilisateur refGestionnaire, Fournisseur refFournisseur, String nomCommande, String dateCommande,String status,String dateLivraison) {
        this.raisonCommande = raisonCommande;
        this.refGestionnaire = refGestionnaire;
        this.refFournisseur = refFournisseur;
        this.nomCommande = nomCommande;
        this.dateCommande = dateCommande;
        this.status = status;
        this.dateLivraison = dateLivraison;

    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getRaisonCommande() {
        return raisonCommande;
    }

    public void setRaisonCommande(String raisonCommande) {
        this.raisonCommande = raisonCommande;
    }

    public Utilisateur getRefGestionnaire() {
        return refGestionnaire;
    }

    public void setRefGestionnaire(Utilisateur refGestionnaire) {
        this.refGestionnaire = refGestionnaire;
    }

    public Fournisseur getRefFournisseur() {return refFournisseur;}

    public void setRefFournisseur(Fournisseur refFournisseur) {this.refFournisseur = refFournisseur;}

    public String getNomCommande() {
        return nomCommande;
    }

    public void setNomCommande(String nomCommande) {
        this.nomCommande = nomCommande;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(String dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    @Override
    public String toString() {
        return nomCommande +" :: "+dateCommande;
    }
}
