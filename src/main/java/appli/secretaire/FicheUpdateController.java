package appli.secretaire;

import appli.StartApplication;
import javafx.event.ActionEvent;

import java.io.IOException;

public class FicheUpdateController {

    public void redirectionListeFiche(ActionEvent actionEvent) throws IOException {
        StartApplication.changeScene("secretaire/ficheList","Liste des fiches");
    }
}
