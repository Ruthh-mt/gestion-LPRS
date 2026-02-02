package appli.secretaire;

import appli.StartApplication;
import appli.session.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.classfile.Label;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.FicheEtudiant;
import model.Utilisateur;
import repository.FicheEtudiantRepository ;
import repository.UtilisateurRepository;


public class FicheCreateController implements Initializable {

    @FXML
    public Button cancelButton;

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
    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    Utilisateur utilisateurActuel = Session.getInstance().getUtilisateur();

    @FXML
    private Label erreurLabel ;




    public void createFicheEtudiant() throws SQLException, IOException {

    String nom = nomTextField.getText();
    String prenom = prenomTextField.getText();
    String email = emailTextField.getText();
    String telephone = telephoneTextField.getText();
    String adresse = adresseTextfield.getText();
    String dernierDiplome = dernierDiplomeComboBox.getValue();



    if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || adresse.isEmpty() || dernierDiplome.isEmpty()) {
        System.out.println("Manque un champ");
    }
    else {
        FicheEtudiant newFiche = new FicheEtudiant(nom,prenom,adresse,telephone,email,dernierDiplome);
        boolean ok = ficheEtudiantRepository.AjouterFicheEtudiant(newFiche);
        if (ok) {
            System.out.println("insertion ok");
            StartApplication.changeScene("secretaire/ficheList");
        }
        else {
            System.out.println("Erreur");
        }
    }

}

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

        dernierDiplomeComboBox.getItems().addAll(
                "BTS SIO SLAM",
                "BTS SIO SISR",
                "Licence informatique",
                "BUT informatique"
        );
    }
}
