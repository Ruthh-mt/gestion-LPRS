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
    @FXML
    private Label erreurLabel ;

    private Utilisateur sessionActuel = Session.getInstance().getUtilisateur();
    private FicheEtudiantRepository fer = new FicheEtudiantRepository();
    private FicheEtudiant ficheActuel ;


    @FXML
    public void initialize()
    {
        erreurLabel.setVisible(false);
        this.sessionLabel.setText("Session de "+sessionActuel.getNom());
    }

    @FXML
    public void redirectionListeFiche(ActionEvent actionEvent) throws IOException {
        StartApplication.changeScene("secretaire/ficheList","Liste des fiches");
    }

    public void updateFiche() throws SQLException, IOException {
        int ref_createur = sessionActuel.getId() ;
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String email = emailTextField.getText();
        String telephone = telephoneTextField.getText();
        String adresse = adresseTextfield.getText();
        String dernierDiplome = dernierDiplomeComboBox.getValue();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || adresse.isEmpty() || dernierDiplome.isEmpty()) {
            erreurLabel.setText("Manque un champ");
        }
        else {
            FicheEtudiant newFiche = new FicheEtudiant(ref_createur , nom,prenom,email,dernierDiplome,telephone,adresse);
            boolean ok = fer.updateFicheEtudiant(newFiche);
            if (ok) {
                System.out.println("modificatin ok");
                System.out.println("Ref createur: " + ref_createur);
                System.out.println("nom: " + nom);
                System.out.println("prenom: " + prenom);
                System.out.println("email: " + email);
                System.out.println("dernierDiplome: " + dernierDiplome);
                System.out.println("telephone: " + telephone);
                System.out.println("adresse: " + adresse);

                StartApplication.changeScene("secretaire/ficheList","Liste des fiches");
            }
            else {
                System.out.println("Erreur");
            }
        }
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
