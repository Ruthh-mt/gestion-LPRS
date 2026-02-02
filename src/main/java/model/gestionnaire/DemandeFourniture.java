package model.gestionnaire;

public class DemandeFourniture {
private int refDemande;
private int refFourniture;

    public DemandeFourniture(int refDemande, int refFourniture) {
        this.refDemande = refDemande;
        this.refFourniture = refFourniture;
    }

    public int getRefDemande() {
        return refDemande;
    }

    public void setRefDemande(int refDemande) {
        this.refDemande = refDemande;
    }

    public int getRefFourniture() {
        return refFourniture;
    }

    public void setRefFourniture(int refFourniture) {
        this.refFourniture = refFourniture;
    }
}
