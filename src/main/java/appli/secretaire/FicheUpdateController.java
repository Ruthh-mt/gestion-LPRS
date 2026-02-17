package appli.secretaire;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FicheEtudiant;
import model.Utilisateur;
import repository.FicheEtudiantRepository;
import session.Session;
import session.SessionFiche;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FicheUpdateController {

    @FXML
    private Label sessionLabel ;
    @FXML
    private Label nomFicheLabel ;
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField adresseTextfield;
    @FXML
    private ComboBox<String> dernierDiplomeComboBox;

    private Utilisateur sessionActuel = Session.getInstance().getUtilisateur();
    private FicheEtudiantRepository fer = new FicheEtudiantRepository();
    private FicheEtudiant ficheActuel ;


    @FXML
    public void initialize()
    {
        this.sessionLabel.setText("Session de "+sessionActuel.getNom());

    }

    @FXML
    public void redirectionListeFiche(ActionEvent actionEvent) throws IOException {
        StartApplication.changeScene("secretaire/ficheList","Liste des fiches");
    }

    public void updateFiche(){
     
    }

    @FXML
   public void initData(FicheEtudiant fiche) throws SQLException {
        this.ficheActuel = fiche ;

        System.out.println("Id session :"+ sessionActuel.getId());
       System.out.println("Id fiche : "+ficheActuel.getIdFicheEtudiante());
       //REQUETE RECUPERER FICHE
       int idFiche = ficheActuel.getIdFicheEtudiante();
       FicheEtudiant feTrouve = fer.getFicheEtudiant(idFiche);
       nomTextField.setText(feTrouve.getNomEtudiant());
       prenomTextField.setText(feTrouve.getPrenomEtudiant());
       emailTextField.setText(feTrouve.getEmailEtudiant());
       dernierDiplomeComboBox.setValue(feTrouve.getDernierDiplome());
       adresseTextfield.setText(feTrouve.getAdresseEtudiant());
       telephoneTextField.setText(feTrouve.getTelephoneEtudiant());
       nomFicheLabel.setText(
               ficheActuel.getPrenomEtudiant()+" "+ficheActuel.getNomEtudiant());
   }

}
