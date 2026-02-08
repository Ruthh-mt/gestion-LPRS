package model.gestionnaire;

public class Fournisseur {
    private int idFournisseur;
    private String nomfournisseur;
    private String adresseFournisseur;
    private String mailFournisseur;
    private String telephoneFournisseur;
    private int delaiLivraisionMoyen;
    private double fraisLivraison;

    public Fournisseur(int idFournisseur, String nomfournisseur, String adresseFournisseur, String mailFournisseur, String telephoneFournisseur, int delaiLivraisionMoyen, double fraisLivraison) {
        this.idFournisseur = idFournisseur;
        this.nomfournisseur = nomfournisseur;
        this.adresseFournisseur = adresseFournisseur;
        this.mailFournisseur = mailFournisseur;
        this.telephoneFournisseur = telephoneFournisseur;
        this.delaiLivraisionMoyen = delaiLivraisionMoyen;
        this.fraisLivraison = fraisLivraison;
    }

    public Fournisseur(String nomfournisseur, String adresseFournisseur, String mailFournisseur, String telephoneFournisseur, int delaiLivraisionMoyen, double fraisLivraison) {
        this.nomfournisseur = nomfournisseur;
        this.adresseFournisseur = adresseFournisseur;
        this.mailFournisseur = mailFournisseur;
        this.telephoneFournisseur = telephoneFournisseur;
        this.delaiLivraisionMoyen = delaiLivraisionMoyen;
        this.fraisLivraison = fraisLivraison;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNomfournisseur() {
        return nomfournisseur;
    }

    public void setNomfournisseur(String nomfournisseur) {
        this.nomfournisseur = nomfournisseur;
    }

    public String getAdresseFournisseur() {
        return adresseFournisseur;
    }

    public void setAdresseFournisseur(String adresseFournisseur) {
        this.adresseFournisseur = adresseFournisseur;
    }

    public String getMailFournisseur() {
        return mailFournisseur;
    }

    public void setMailFournisseur(String mailFournisseur) {
        this.mailFournisseur = mailFournisseur;
    }

    public String getTelephoneFournisseur() {
        return telephoneFournisseur;
    }

    public void setTelephoneFournisseur(String telephoneFournisseur) {
        this.telephoneFournisseur = telephoneFournisseur;
    }

    public double getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(double fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public int getDelaiLivraisionMoyen() {
        return delaiLivraisionMoyen;
    }

    public void setDelaiLivraisionMoyen(int delaiLivraisionMoyen) {
        this.delaiLivraisionMoyen = delaiLivraisionMoyen;
    }
}
