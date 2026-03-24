package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.FicheEtudiant;
import model.Utilisateur;
import repository.UtilisateurRepository;
import session.Session;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FicheReadController implements Initializable {
    FicheEtudiant ficheActuel = null;

    @FXML
    private Button retourButton;

    @FXML
    private Button redirectionUpdateFicheBtn ;

    @FXML
    private Label titreFiche ;

    @FXML
    private Label sessionLabel ;

    @FXML
    private Label nomLabel ;
    @FXML
    private Label prenomLabel ;
    @FXML
    private Label emailLabel ;

    @FXML
    private Label dernierDiplomeLabel ;
    @FXML
    private Label nomCreateurLabel ;
    @FXML
    private Label prenomCreateurLabel ;
    @FXML
    private Label emailCreateurLabel ;

    Utilisateur userActuel = Session.getInstance().getUtilisateur();
    UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    public void initData(FicheEtudiant ficheEtudiant) throws SQLException {
        this.ficheActuel = ficheEtudiant ;
        System.out.println("Fiche actuel : "+ficheActuel);
        titreFiche.setText("Fiche étudiante n°"+ficheActuel.getIdFicheEtudiante());
        nomLabel.setText(ficheActuel.getNomEtudiant());
        prenomLabel.setText(ficheActuel.getPrenomEtudiant());
        emailLabel.setText(ficheActuel.getEmailEtudiant());
        dernierDiplomeLabel.setText(ficheActuel.getDernierDiplome());
        Utilisateur utilisateur = utilisateurRepository.getUtilisateurById(ficheActuel.getRefCreateur());
        nomCreateurLabel.setText(utilisateur.getNom());
        prenomCreateurLabel.setText(utilisateur.getPrenom());
        emailCreateurLabel.setText(utilisateur.getEmail());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.sessionLabel.setText("Session de "+userActuel.getPrenom()+" "+Session.getInstance().getUtilisateur().getNom());
        sessionLabel.setText("Session de "+userActuel.getPrenom() +" "+Session.getInstance().getUtilisateur().getNom());

    }

    @FXML
    public void redirectionUpdateFiche() throws IOException, SQLException {

        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource("secretaire/ficheUpdate" + "View.fxml"));
        Parent root = fxmlLoader.load();
        FicheUpdateController ficheUpdateController = fxmlLoader.getController();
        ficheUpdateController.initData(ficheActuel);
            Stage mainStage = (Stage) redirectionUpdateFicheBtn.getScene().getWindow();

        mainStage.setScene(new Scene(root));
        mainStage.show();    }

    @FXML
    public void retourButtonAction() throws IOException {
        StartApplication.changeScene("secretaire/ficheList","liste des fiches");
    }
}
