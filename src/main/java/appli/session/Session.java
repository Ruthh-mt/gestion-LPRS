package appli.session;

import model.Utilisateur;

public class Session {
        private static Session instance;
        private Utilisateur utilisateurConnecte;
        private Session() { }
        public static Session getInstance() {
            if (instance == null) {
                instance = new Session();
            }
            return instance;
        }
        public void sauvegardeSession(Utilisateur utilisateur) {
            if (this.utilisateurConnecte == null) {
                this.utilisateurConnecte = utilisateur;
            }
        }
        public Utilisateur getUtilisateur() {
            return utilisateurConnecte;
        }
        public void deconnecter() {
            utilisateurConnecte = null;
        }
    }

