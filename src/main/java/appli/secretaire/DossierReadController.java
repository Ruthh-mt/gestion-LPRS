package appli.secretaire;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
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

import static appli.StartApplication.mainStage;

public class DossierReadController implements Initializable {


    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label dernierDiplomeLabel;
    @FXML
    private Label dateInscriptionLabel;
    @FXML
    private Label heureLabel;
    @FXML
    private Label filiereLabel;
    @FXML
    private TextArea motivationArea;
    @FXML
    private Label titreDossier;

    @FXML
    private Button redirectionUpdateDossierBtn ;

    DossierRepository dr = new DossierRepository();
    FicheEtudiantRepository fr = new FicheEtudiantRepository();
    FiliereRepository filiereRepository = new FiliereRepository();
    DossierInscription dossierActuel = null;
    FicheEtudiant ficheActuel = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void retourListe() throws IOException {
        StartApplication.changeScene("secretaire/dossierList","liste dossiers");
    }
    @FXML
    public void initData(DossierInscription dossierInscription) throws SQLException {
        this.dossierActuel = dossierInscription ;
        System.out.println("Dossier actuel : "+dossierActuel);
        // Recuperer fiche
        this.ficheActuel = fr.getFicheEtudiant(dossierActuel.getRefFiche());
        //Recuperer filière
        int ref_filiere = this.dossierActuel.getRefFiliere();
        Filiere filiere = filiereRepository.getFiliere(ref_filiere);

        //Remplissage des champs
        titreDossier.setText("Dossier n°"+dossierActuel.getId());
        dateInscriptionLabel.setText(String.valueOf(this.dossierActuel.getDate()));
        heureLabel.setText(String.valueOf(this.dossierActuel.getHeure()));
        nomLabel.setText(ficheActuel.getNomEtudiant());
        prenomLabel.setText(ficheActuel.getPrenomEtudiant());
        emailLabel.setText(ficheActuel.getEmailEtudiant());
        motivationArea.setText(dossierActuel.getMotivation());
        dernierDiplomeLabel.setText(ficheActuel.getDernierDiplome());
        filiereLabel.setText(filiere.getNomFiliere());

        System.out.println("Id dossier :"+ dossierInscription.getId());

    }

    @FXML
    public void redirectionUpdateDossier() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource("secretaire/dossierUpdate" + "View.fxml"));
        Parent root = fxmlLoader.load();
        DossierUpdateController dossierUpdateController = fxmlLoader.getController();
        dossierUpdateController.initData(dossierActuel);
        Stage mainStage = (Stage) redirectionUpdateDossierBtn.getScene().getWindow();

        mainStage.setScene(new Scene(root));
        mainStage.show();
    }

}
