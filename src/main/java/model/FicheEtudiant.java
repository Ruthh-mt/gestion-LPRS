package model;

public class FicheEtudiant {
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDernierDiplome() {
        return dernierDiplome;
    }

    public void setDernierDiplome(String dernierDiplome) {
        this.dernierDiplome = dernierDiplome;
    }

    public String nom;
    public String prenom;
    public String adresse;
    public String telephone;
    public String email;
    public String dernierDiplome;

    public FicheEtudiant(String nom, String prenom, String adresse, String telephone, String email
    , String dernierDiplome) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.dernierDiplome = dernierDiplome;
    }



    @Override
    public String toString() {
        return "Nom : "+this.nom +"\nPrenom : "+this.prenom+"\nAdresse : "+this.adresse+"\nTelephone : "+this.telephone+
                "\nEmail : "+this.email+"\nDiplome : "+this.dernierDiplome+"\n";
    }
}
