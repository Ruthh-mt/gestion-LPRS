package appli.statistiques;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import repository.DossierRepository;
import repository.RendezVousRepository;
import repository.gestionnaire.CommandeRepository;
import repository.gestionnaire.FournitureRepository;

import java.io.IOException;

public class StatistiquesController {

    @FXML
    private Label nbDossiersLabel;

    @FXML
    private Label nbCommandesLabel;

    @FXML
    private Label nbRdvLabel;

    @FXML
    private Label qteStockLabel;

    DossierRepository dossierRepository = new DossierRepository();
    CommandeRepository commandeRepository = new CommandeRepository();
    RendezVousRepository rendezVousRepository = new RendezVousRepository();
    FournitureRepository fournitureRepository = new FournitureRepository();

    @FXML
    public void initialize() {
        chargerStatistiques();
    }

    private void chargerStatistiques() {

            //NB DOSSIERS
        int nb_dossier = dossierRepository.countDossier();
            nbDossiersLabel.setText(String.valueOf(nb_dossier));


            // NB COMMANDES
            int nb_commande = commandeRepository.countCommandes();
            nbCommandesLabel.setText(String.valueOf(nb_commande));


            // NB RDV
            int nb_rdv = rendezVousRepository.countRendezVous();
            nbRdvLabel.setText(String.valueOf(nb_rdv));


            // QTE STOCK
            int qte_stock = fournitureRepository.countQteStock();
            qteStockLabel.setText(String.valueOf(qte_stock));

    }
    public void retour() throws IOException {
        StartApplication.changeScene("accueil/homePage","Accueil");

    }
}