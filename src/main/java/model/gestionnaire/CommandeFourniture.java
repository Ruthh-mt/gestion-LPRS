package model.gestionnaire;


public class CommandeFourniture {
    private Commande refCommande;
    private Fourniture refFourniture;
    private int qte;

    public CommandeFourniture( Commande refCommande, Fourniture refFourniture,int qte) {
        this.refCommande = refCommande;
        this.refFourniture = refFourniture;
        this.qte = qte;
    }

    public Commande getRefCommande() {
        return refCommande;
    }

    public void setRefCommande(Commande refCommande) {
        this.refCommande = refCommande;
    }

    public Fourniture getRefFourniture() {
        return refFourniture;
    }

    public void setRefFourniture(Fourniture refFourniture) {
        this.refFourniture = refFourniture;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
