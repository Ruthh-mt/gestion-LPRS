package model.gestionnaire;

import java.util.ArrayList;

public class FournitureFournisseur {
    private int refFourniture;
    private int refFournisseur;
    private double prix;

    public FournitureFournisseur(int refFourniture, int refFournisseur, double prix) {
        this.refFourniture = refFourniture;
        this.refFournisseur = refFournisseur;
        this.prix = prix;
    }

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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
