package session;

import model.FicheEtudiant;
import model.Utilisateur;

import java.util.List;

public class SessionFiche {
    private  static SessionFiche instance;
    private  String idFiche;
    private int refCreateur;
    private String nomEtudiant ;
    private String prenomEtudiant ;
    private String email ;
    private String dernierDiplome ;
    private String telephone;
    private String adresse;
    private FicheEtudiant ficheActive;

    private SessionFiche() { }
    public static SessionFiche getInstance() {
        if (instance == null) {
            instance = new SessionFiche();
        }
        return instance;
    }
    public void sauvegardeSession(FicheEtudiant ficheEtudiant) {
        if (this.ficheActive == null) {
            this.ficheActive = ficheEtudiant;
        }
    }
    public FicheEtudiant getFiche() {
        return ficheActive;
    }
    public void deconnecter() {
        ficheActive = null;
    }
}