package model;

public class Utilisateur {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String role;
    private int refFiliere;
    private String code;


    public Utilisateur(String nom, String prenom, String email, String motDePasse, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public Utilisateur(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.email = email;
        this.role = role;
    }
    public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, String role,int refFiliere) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.email = email;
        this.role = role;
        this.refFiliere=refFiliere;
    }

    public Utilisateur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRefFiliere() {
        return refFiliere;
    }

    public void setRefFiliere(int ref_filiere) {
        this.refFiliere = ref_filiere;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", role='" + role + '\'' +
                ", refFiliere=" + refFiliere +
                ", code='" + code + '\'' +
                '}';
    }
}

