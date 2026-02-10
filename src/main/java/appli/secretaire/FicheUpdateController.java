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

    private TextField nomTextField;
    private TextField prenomTextField;
    private TextField emailTextField;
    private TextField telephoneTextField;
    private TextField adresseTextfield;
    @FXML
    private ComboBox<String> dernierDiplomeComboBox;

    Utilisateur sessionUtilisateur = Session.getInstance().getUtilisateur();
    FicheEtudiantRepository fer = new FicheEtudiantRepository();
    int idSession = sessionUtilisateur.getId();
    FicheEtudiant ficheActuel = SessionFiche.getInstance().getFiche();

    public void redirectionListeFiche(ActionEvent actionEvent) throws IOException {
        StartApplication.changeScene("secretaire/ficheList","Liste des fiches");
    }

    public void initialize() throws SQLException {
        System.out.println("Id session :"+ Session.getInstance().getUtilisateur().getId());
        System.out.println("Id fiche : "+SessionFiche.getInstance().getFiche().getIdFicheEtudiante());
        FicheEtudiant fe = SessionFiche.getInstance().getFiche();


    }
    public void updateFiche(){

    }


}
