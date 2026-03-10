package model.gestionnaire;

public class Fourniture {
    private int idFourniture;
    private String libelle;
    private String description;
    private int stockActuelle;
    private int stockMinimum;

    public Fourniture(int idFourniture, String libelle, String description, int stockActuelle, int stockMinimum) {
        this.idFourniture = idFourniture;
        this.libelle = libelle;
        this.description = description;
        this.stockActuelle = stockActuelle;
        this.stockMinimum = stockMinimum;
    }

    public Fourniture(String libelle, String description, int stockActuelle, int stockMinimum) {
        this.libelle = libelle;
        this.description = description;
        this.stockActuelle = stockActuelle;
        this.stockMinimum = stockMinimum;
    }

    public int getIdFourniture() {
        return idFourniture;
    }

    public void setIdFourniture(int idFourniture) {
        this.idFourniture = idFourniture;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockActuelle() {
        return stockActuelle;
    }

    public void setStockActuelle(int stockActuelle) {
        this.stockActuelle = stockActuelle;
    }

    public int getStockMinimum() {
        return stockMinimum;
    }

    public void setStockMinimum(int stockMinimum) {
        this.stockMinimum = stockMinimum;
    }

    @Override
    public String toString() {
        return  libelle;
    }
}
