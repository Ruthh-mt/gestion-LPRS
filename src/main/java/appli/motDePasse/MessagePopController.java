package appli.motDePasse;
/*
import appli.StartApplication;
import javafx.fxml.FXML;

import java.io.IOException;

import model.Utilisateur;
import repository.UtilisateurRepository;
import services.EmailService;

public class MessagePopController {
    private String mailUser;
    public void initData(String mail) {
        this.mailUser = mail;
    }


    @FXML
    void oRessaisirEmail() throws IOException {
        StartApplication.changeScene("motDePasse/saisieEmail","Mot de passe Oublie");
    }

    @FXML
    void onContinueAction() throws IOException {
        UtilisateurRepository userRepo=new UtilisateurRepository();
        Utilisateur user= new Utilisateur(mailUser,"00");
        if(userRepo.getUserByMail(user)!= null ) {
            String code = EmailService.genererCode();
            EmailService.envoyerEmail(mailUser, "Réinitialisation de mot de passe", "Votre code de " +
                    "réinitialisation est : " + code);
            System.out.println("Code envoyé à : " + mailUser);
            userRepo.createCode(code,mailUser);
            StartApplication.changeScene("motDePasse/saisieCode","Mot de passe Oublie");
            SaisieCodeController controller = (SaisieCodeController)
                    StartApplication.getControllerFromStage();
            controller.initData(mailUser);

        }
        else {
            System.out.println("L'utilisateur n'existe pas.");
            StartApplication.changeScene("accueil/login","Connexion");
        }

    }

    @FXML
    void onGoBackToLogin() throws IOException {
        StartApplication.changeScene("accueil/login", "Connexion");

    }
}
*/