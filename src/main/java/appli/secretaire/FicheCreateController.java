package appli.secretaire;

import appli.session.Session;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.lang.classfile.Label;
import java.sql.SQLException;

import model.FicheEtudiant;
import model.Utilisateur;
import repository.FicheEtudiantRepository ;
import repository.UtilisateurRepository;


public class FicheCreateController {

    @FXML
    public Button cancelButton;
    @FXML
    private TextField dernierDiplomeTextField;
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
    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    Utilisateur utilisateurActuel = Session.getInstance().getUtilisateur();

    @FXML
    private Label erreurLabel ;




    public void createFicheEtudiant() throws SQLException {

    String nom = nomTextField.getText();
    String prenom = prenomTextField.getText();
    String email = emailTextField.getText();
    String telephone = telephoneTextField.getText();
    String adresse = adresseTextfield.getText();
    String dernierDiplome = dernierDiplomeTextField.getText();


    if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || adresse.isEmpty() || dernierDiplome.isEmpty()) {
        System.out.println("Manque un champ");
    }
    else {
        FicheEtudiant newFiche = new FicheEtudiant(nom,prenom,adresse,telephone,email,dernierDiplome);
        boolean ok = ficheEtudiantRepository.AjouterFicheEtudiant(newFiche);
        if (ok) {
            System.out.println("insertion ok");
        }
        else {
            System.out.println("Erreur");
        }
    }

}





}
