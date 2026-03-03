package model;

public class Salle {

    private int idSalle;
    private int capacite;
    private boolean estOccupe;

    public Salle(int idSalle, int capacite, boolean estOccupe) {
        this.idSalle = idSalle;
        this.capacite = capacite;
        this.estOccupe = estOccupe;
    }

    public Salle(int capacite, boolean estOccupe) {
        this.capacite = capacite;
        this.estOccupe = estOccupe;
    }

    public int getIdSalle() { return idSalle; }
    public void setIdSalle(int idSalle) { this.idSalle = idSalle; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public boolean isEstOccupe() { return estOccupe; }
    public void setEstOccupe(boolean estOccupe) { this.estOccupe = estOccupe; }

    @Override
    public String toString() {
        return "Salle " + idSalle + " (cap. " + capacite + ")";
    }
}