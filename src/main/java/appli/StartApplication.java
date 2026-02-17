package appli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class    StartApplication extends Application {
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("accueil/loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setTitle("Connexion");
        mainStage.setScene(scene);
        mainStage.show();
    }
    public static void changeScene(String nomDuFichierFxml, String namePage) throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(StartApplication.class.getResource(nomDuFichierFxml + "View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setUserData(fxmlLoader.getController());
        mainStage.setScene(scene);
        mainStage.setTitle(namePage);
    }
    public static Object getControllerFromStage(){
        return mainStage.getScene().getUserData();
    }
    public static void main(String[] args) {
        launch();
    }
}