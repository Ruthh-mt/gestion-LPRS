package appli.secretaire;

import appli.StartApplication;
import javafx.scene.control.*;
import session.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.FicheEtudiant;
import model.Utilisateur;
import repository.FicheEtudiantRepository ;
import repository.UtilisateurRepository;


public class FicheCreateController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private javafx.scene.control.Label dernierDiplomeLabel;
    @FXML
    private Button retourButton;

    @FXML
    private TextField adresseTextfield;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nomTextField;


    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private Button validerButton;

    @FXML
    private ComboBox<String> dernierDiplomeComboBox;

    @FXML
    private Label erreurLabel;




    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    Utilisateur utilisateurActuel = Session.getInstance().getUtilisateur();


    public void createFicheEtudiant() throws SQLException, IOException {

    int ref_createur = utilisateurActuel.getId() ;
    String nom = nomTextField.getText();
    String prenom = prenomTextField.getText();
    String email = emailTextField.getText();
    String dernierDiplome = dernierDiplomeComboBox.getValue();
    String telephone = telephoneTextField.getText();
    String adresse = adresseTextfield.getText();

    if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || adresse.isEmpty() || dernierDiplome.isEmpty()) {
        erreurLabel.setText("Manque un champ");
    }
    else {
        FicheEtudiant newFiche = new FicheEtudiant(ref_createur , nom,prenom,email,dernierDiplome,telephone,adresse);
        boolean ok = ficheEtudiantRepository.AjouterFicheEtudiant(newFiche);
        if (ok) {
            System.out.println("insertion ok");
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
public void retourListe() throws IOException {
        StartApplication.changeScene("secretaire/ficheList","Liste des fiches");
}

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        erreurLabel.setVisible(false);
        dernierDiplomeComboBox.getItems().addAll(
                "BTS SIO SLAM",
                "BTS SIO SISR",
                "Licence informatique",
                "BUT informatique"
        );
    }


}
