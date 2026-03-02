package appli.profil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Utilisateur;
import session.Session;

import java.io.IOException;

public class ProfilReadController {

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label roleLabel;

    private final Utilisateur userSession = Session.getInstance().getUtilisateur();

    @FXML
    public void initialize() {
        nomLabel.setText(userSession.getNom());
        prenomLabel.setText(userSession.getPrenom());
        emailLabel.setText(userSession.getEmail());
        roleLabel.setText(userSession.getRole());
    }

    @FXML
    void onModifierProfilClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                StartApplication.class.getResource("profil/profilUpdateView.fxml"));
        Parent root = fxmlLoader.load();
        profilUpdateController controller = fxmlLoader.getController();
        controller.initData(userSession);
        Stage stage = (Stage) nomLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Modifier le profil");
        stage.show();
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/homePage", "Accueil");
    }
}