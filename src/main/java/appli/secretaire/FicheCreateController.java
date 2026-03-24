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

    @FXML
    private Label sessionLabel;



    FicheEtudiantRepository ficheEtudiantRepository = new FicheEtudiantRepository();

    Utilisateur userActuel = Session.getInstance().getUtilisateur();


    public void createFicheEtudiant() throws SQLException, IOException {

    int ref_createur = userActuel.getId() ;
    String nom = nomTextField.getText();
    String prenom = prenomTextField.getText();
    String email = emailTextField.getText();
    String dernierDiplome = dernierDiplomeComboBox.getValue();
    String telephone = telephoneTextField.getText();
    String adresse = adresseTextfield.getText();

    if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || adresse.isEmpty() || dernierDiplome.isEmpty()) {
        erreurLabel.setVisible(true);
        erreurLabel.setText("Veuillez remplir tous les champs !");
        erreurLabel.setStyle("-fx-text-fill: red;");
    }
    else {
        FicheEtudiant newFiche = new FicheEtudiant(ref_createur , nom,prenom,email,dernierDiplome,telephone,adresse);
        boolean ok = ficheEtudiantRepository.AjouterFicheEtudiant(newFiche);
        if (ok) {
            erreurLabel.setVisible(true);
            erreurLabel.setText("Fiche ajouté avec succès");
            erreurLabel.setStyle("-fx-text-fill: green;");

        }
        else {
            erreurLabel.setText("Erreur");
            erreurLabel.setStyle("-fx-text-fill: red;");
        }
    }

}

@FXML
public void retour(ActionEvent event) throws IOException {
    nomTextField.clear();
    prenomTextField.clear();
    emailTextField.clear();
    dernierDiplomeComboBox.getItems().clear();
    telephoneTextField.clear();
    adresseTextfield.clear();


}
public void retourListe() throws IOException {
        StartApplication.changeScene("secretaire/ficheList","Liste des fiches");
}

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        erreurLabel.setVisible(false);
        sessionLabel.setVisible(true);
        sessionLabel.setText("Session de "+userActuel.getPrenom()+" "+userActuel.getNom());
        dernierDiplomeComboBox.getItems().addAll(
                "BTS SIO SLAM",
                "BTS SIO SISR",
                "Licence informatique",
                "BUT informatique",
                "BAC Scientifique",
                "BAC STI2D"
        );
    }


}
