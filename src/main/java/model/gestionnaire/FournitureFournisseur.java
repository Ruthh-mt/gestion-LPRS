package model.gestionnaire;

public class FournitureFournisseur {
    private int refFourniture;
    private int refFournisseur;

    public FournitureFournisseur(int refFourniture, int refFournisseur) {
        this.refFourniture = refFourniture;
        this.refFournisseur = refFournisseur;
    }

    public int getRefFourniture() {
        return refFourniture;
    }

    public void setRefFourniture(int refFourniture) {
        this.refFourniture = refFourniture;
    }

    public int getRefFournisseur() {
        return refFournisseur;
    }

    public void setRefFournisseur(int refFournisseur) {
        this.refFournisseur = refFournisseur;
    }
}
