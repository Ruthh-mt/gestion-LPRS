package model;

public class Filiere {
    private int idFiliere;
    private String nomFiliere;

    public Filiere(int idFiliere, String nomFiliere) {
        this.idFiliere = idFiliere;
        this.nomFiliere = nomFiliere;
    }
    public Filiere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }
    public int getIdFiliere() {
        return this.idFiliere;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public void setNomFiliere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }
}
