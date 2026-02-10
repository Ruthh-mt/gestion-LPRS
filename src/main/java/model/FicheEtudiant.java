package model;

public class FicheEtudiant {


    public int idFicheEtudiante ;
    public int refCreateur ;
    public String nomEtudiant ;
    public String prenomEtudiant;
    public String adresseEtudiant;
    public String telephoneEtudiant;
    public String emailEtudiant;
    public String dernierDiplome;

    public FicheEtudiant(
            int idFicheEtudiante,
            int refCreateur ,
            String nomEtudiant ,
            String prenomEtudiant ,
            String emailEtudiant ,
            String dernierDiplome ,
            String telephoneEtudiant ,
            String adresseEtudiant

    ) {
        this.idFicheEtudiante = idFicheEtudiante;
        this.refCreateur = refCreateur;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.telephoneEtudiant = telephoneEtudiant;
        this.adresseEtudiant = adresseEtudiant;
        this.dernierDiplome = dernierDiplome;
    }

    public FicheEtudiant(int refCreateur,String nomEtudiant,String prenomEtudiant,String emailEtudiant,
                         String telephoneEtudiant ,String adresseEtudiant , String dernierDiplome) {
        this.refCreateur = refCreateur;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.telephoneEtudiant = telephoneEtudiant;
        this.adresseEtudiant = adresseEtudiant;
        this.dernierDiplome = dernierDiplome;
    }

    public int getIdFicheEtudiante() {
        return idFicheEtudiante;
    }
    public int getRefCreateur() {
        return refCreateur;
    }
    public String getNomEtudiant() {
        return nomEtudiant;
    }
    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }
    public String getAdresseEtudiant() {
        return adresseEtudiant;
    }
    public String getTelephoneEtudiant() {
        return telephoneEtudiant;
    }
    public String getEmailEtudiant() {
        return emailEtudiant;
    }
    public String getDernierDiplome() {
        return dernierDiplome;
    }

    public void setRefCreateur(int ref_createur) {
        this.refCreateur = ref_createur;
    }
    public void setNomEtudiant(String nom_etudiant) {
        this.nomEtudiant = nom_etudiant;
    }
    public void setPrenomEtudiant(String prenom_etudiant) {
        this.prenomEtudiant = prenom_etudiant;
    }
    public void setAdresseEtudiant(String adresse_etudiant) {
        this.adresseEtudiant = adresse_etudiant;
    }
    public void setTelephoneEtudiant(String telephone_etudiant) {
        this.telephoneEtudiant = telephone_etudiant;
    }
    public void setEmailEtudiant(String email_etudiant) {
        this.emailEtudiant = email_etudiant;
    }
    public void setDernierDiplome(String dernierDiplome) {
        this.dernierDiplome = dernierDiplome;
    }

    @Override
    public String toString() {
        return "Id : "+idFicheEtudiante + "\nNom : "+nomEtudiant+"\nPrenom : "+prenomEtudiant+
                "\nEmail : "+emailEtudiant + "\nDernierDiplome : "+dernierDiplome;
    }
}
