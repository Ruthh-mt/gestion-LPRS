package session;

import model.DossierInscription;
import model.FicheEtudiant;

import java.sql.Date;
import java.sql.Time;

public class SessionDossier {

        private  static session.SessionDossier instance;
        private  String idDossier;
        private Date date ;
        private Time heure ;
        private String motivation ;
        private int ref_filiere;
        private DossierInscription dossierActif ;

        private SessionDossier() { }
        public static session.SessionDossier getInstance() {
            if (instance == null) {
                instance = new session.SessionDossier();
            }
            return instance;
        }
        public void sauvegardeSession(DossierInscription dossierInscription) {
            if (this.dossierActif == null) {
                this.dossierActif = dossierInscription;
            }
        }
        public DossierInscription getDossier() {
            return dossierActif;
        }
        public void deconnecter() {
            dossierActif = null;
        }
    }

