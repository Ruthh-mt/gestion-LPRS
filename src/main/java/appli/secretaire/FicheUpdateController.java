package appli.secretaire;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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

    public void redirectionListeFiche(ActionEvent actionEvent) throws IOException {
        StartApplication.changeScene("secretaire/ficheList","Liste des fiches");
    }

    public void initialize() throws SQLException {
    }

    public void updateFiche(){

    }

   public void initData(FicheEtudiant fiche) throws SQLException {
       System.out.println("Id session :"+ sessionActuel.getId());
       System.out.println("Id fiche : "+ficheActuel.getIdFicheEtudiante());
       this.ficheActuel = fiche ;
       //REQUETE RECUPERER FICHE
       int idFiche = ficheActuel.getIdFicheEtudiante();
       FicheEtudiant feTrouve = fer.getFicheEtudiant(idFiche);
       nomTextField.setText(feTrouve.getNomEtudiant());
       prenomTextField.setText(feTrouve.getPrenomEtudiant());
       emailTextField.setText(feTrouve.getEmailEtudiant());
       dernierDiplomeComboBox.setValue(feTrouve.getDernierDiplome());
       adresseTextfield.setText(feTrouve.getAdresseEtudiant());
       telephoneTextField.setText(feTrouve.getTelephoneEtudiant());

   }

}
