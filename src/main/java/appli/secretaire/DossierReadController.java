package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.DossierInscription;
import model.FicheEtudiant;
import model.Filiere;
import repository.DossierRepository;
import repository.FicheEtudiantRepository;
import repository.FiliereRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;

public class DossierReadController implements Initializable {


    public Label nomLabel;
    public Label prenomLabel;
    public Label emailLabel;
    public Label dernierDiplomeLabel;
    public Label dateInscriptionLabel;
    public Label heureLabel;
    public Label filiereLabel;
    public TextArea motivationArea;
    public Label titreDossier;

    DossierRepository dr = new DossierRepository();
    FicheEtudiantRepository fr = new FicheEtudiantRepository();
    FiliereRepository fir = new FiliereRepository();
    DossierInscription dossierActuel = null ;
    FicheEtudiant ficheActuel = null ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void retourListe() throws IOException {
        StartApplication.changeScene("secretaire/dossierList","liste dossiers");
    }

    @FXML
    public void initData(DossierInscription dossierInscription , int ref_fiche) throws SQLException {
        this.dossierActuel = dossierInscription ;
        titreDossier.setText("Dossier n°"+dossierActuel.getId());
        dateInscriptionLabel.setText(String.valueOf(this.dossierActuel.getDate()));
        heureLabel.setText(String.valueOf(this.dossierActuel.getHeure()));
        filiereLabel.setText(dossierActuel.getNomFiliere());
        nomLabel.setText(dossierActuel.getNomEtudiant());
        prenomLabel.setText(dossierActuel.getPrenomEtudiant());
        emailLabel.setText(dossierActuel.getEmailEtudiant());
        //Get fiche

        System.out.println("Id dossier :"+ dossierInscription.getId());




    }
}
