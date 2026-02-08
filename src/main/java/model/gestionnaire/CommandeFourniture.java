package model.gestionnaire;

import java.sql.Date;

public class CommandeFourniture {
    private int refCommande;
    private int refFourniture;

    public CommandeFourniture( int refCommande, int refFourniture) {
        this.refCommande = refCommande;
        this.refFourniture = refFourniture;
    }

    public int getRefCommande() {
        return refCommande;
    }

    public void setRefCommande(int refCommande) {
        this.refCommande = refCommande;
    }

    public int getRefFourniture() {
        return refFourniture;
    }

    public void setRefFourniture(int refFourniture) {
        this.refFourniture = refFourniture;
    }
}
