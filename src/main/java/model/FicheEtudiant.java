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
        this.dernierDiplome = dernierDiplome;
        this.telephoneEtudiant = telephoneEtudiant;
        this.adresseEtudiant = adresseEtudiant;

    }

    public FicheEtudiant(int refCreateur,String nomEtudiant,String prenomEtudiant,String emailEtudiant, String dernierDiplome,
                         String telephoneEtudiant ,String adresseEtudiant ) {
        this.refCreateur = refCreateur;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.dernierDiplome = dernierDiplome;
        this.telephoneEtudiant = telephoneEtudiant;
        this.adresseEtudiant = adresseEtudiant;

    }
    public FicheEtudiant(String nomEtudiant,String prenomEtudiant,String emailEtudiant, String dernierDiplome,
                         String telephoneEtudiant ,String adresseEtudiant ) {
        this.refCreateur = refCreateur;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.dernierDiplome = dernierDiplome;
        this.telephoneEtudiant = telephoneEtudiant;
        this.adresseEtudiant = adresseEtudiant;

    }

    public int getIdFicheEtudiante() {
        return idFicheEtudiante;
    }
    public int getRefCreateur() {
        return this.refCreateur;
    }
    public String getNomEtudiant() {
        return this.nomEtudiant;
    }
    public String getPrenomEtudiant() {
        return this.prenomEtudiant;
    }
    public String getAdresseEtudiant() {
        return this.adresseEtudiant;
    }
    public String getTelephoneEtudiant() {
        return this.telephoneEtudiant;
    }
    public String getEmailEtudiant() {
        return this.emailEtudiant;
    }
    public String getDernierDiplome() {
        return this.dernierDiplome;
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
