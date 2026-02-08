package model.gestionnaire;

public class Commande {
    private int idCommande;
    private String raisonCommande;
    private int refGestionnaire;
    private int refFournisseur;
    private String nomCommande;
    private String dateCommande;


    public Commande(int idCommande, String raisonCommande, int refGestionnaire, int refFournisseur, String nomCommande, String dateCommande) {
        this.idCommande = idCommande;
        this.raisonCommande = raisonCommande;
        this.refGestionnaire = refGestionnaire;
        this.refFournisseur = refFournisseur;
        this.nomCommande = nomCommande;
        this.dateCommande = dateCommande;
    }

    public Commande(String raisonCommande, int refGestionnaire, int refFournisseur, String nomCommande, String dateCommande) {
        this.raisonCommande = raisonCommande;
        this.refGestionnaire = refGestionnaire;
        this.refFournisseur = refFournisseur;
        this.nomCommande = nomCommande;
        this.dateCommande = dateCommande;

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

    public int getRefGestionnaire() {
        return refGestionnaire;
    }

    public void setRefGestionnaire(int refGestionnaire) {
        this.refGestionnaire = refGestionnaire;
    }

    public int getRefFournisseur() {
        return refFournisseur;
    }

    public void setRefFournisseur(int refFournisseur) {
        this.refFournisseur = refFournisseur;
    }

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
}
