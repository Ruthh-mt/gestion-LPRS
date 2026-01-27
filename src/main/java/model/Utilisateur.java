package model;

public class Utilisateur
{

        private int id_utilisateur;
        private String nom;
        private String prenom;
        private String email ;
        private String password ;
        private String role ;
        private int refFiliere ;

        public Utilisateur(String email, String mdp) {
            this.email = email;
            this.password = mdp;

        }
        public Utilisateur(String nom, String prenom, String email, String mdp, String role, int refFiliere) {
            this.nom= nom;
            this.prenom = prenom;
            this.email = email;
            this.password = mdp;
        }
        public Utilisateur(int id_utilisateur, String nom, String prenom, String email, String mdp, String role, int refFiliere) {
            this.id_utilisateur = id_utilisateur;
            this.nom= nom;
            this.prenom = prenom;
            this.email = email;
            this.password = mdp;
        }
        public Utilisateur(int id_utilisateur, String nom, String prenom, String email,String mdp, String role) {
            this.id_utilisateur = id_utilisateur;
            this.nom= nom;
            this.prenom = prenom;
            this.email = email;
        }




        public int getId_utilisateur() {
            return this.id_utilisateur ;
        }
        public String getNom() {
            return this.nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getPrenom() {
            return this.prenom;
        }
        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
        public String getEmail() {
            return this.email;
        }
        public String getPassword(){
            return this.password;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getRole() {
            return this.role;
        }

        public String toString() {
            return "id utilisateur : "+this.id_utilisateur+"\nNom : "+this.nom+"\nPrenom : "+this.prenom+"\nEmail : "+this.email+"\nRole : "+this.role;
        }
    }

