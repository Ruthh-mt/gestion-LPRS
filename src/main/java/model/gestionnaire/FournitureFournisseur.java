package model.gestionnaire;


public class FournitureFournisseur {
    private Fourniture refFourniture;
    private Fournisseur refFournisseur;
    private double prix;

    public FournitureFournisseur(Fourniture fourniture, Fournisseur fournisseur, double prix) {
        this.refFourniture=fourniture;
        this.refFournisseur=fournisseur;
        this.prix=prix;
    }

    public Fourniture getRefFourniture() {
        return refFourniture;
    }

    public void setRefFourniture(Fourniture refFourniture) {
        this.refFourniture = refFourniture;
    }

    public Fournisseur getRefFournisseur() {
        return refFournisseur;
    }

    public void setRefFournisseur(Fournisseur refFournisseur) {
        this.refFournisseur = refFournisseur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "FournitureFournisseur{" +
                "refFourniture=" + refFourniture +
                ", refFournisseur=" + refFournisseur +
                ", prix=" + prix +
                '}';
    }
}
