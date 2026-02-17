package appli.professeur;
import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class DemandeCreateController {

    @FXML
    void onProfilClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("profil/profilRead", "Profil");
    }

    @FXML
    void onRetourClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/homePage", "Accueil");
    }
}