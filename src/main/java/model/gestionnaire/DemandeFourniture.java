package model.gestionnaire;

public class DemandeFourniture {
private int refDemande;
private int refFourniture;
private int qte;

    public DemandeFourniture(int refDemande, int refFourniture,int qte) {
        this.refDemande = refDemande;
        this.refFourniture = refFourniture;
        this.qte = qte;
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
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
