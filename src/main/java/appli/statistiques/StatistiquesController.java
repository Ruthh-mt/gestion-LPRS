package appli.statistiques;

import repository.DossierRepository;

public class StatistiquesController {

    DossierRepository dossierRepository = new DossierRepository();
    int nb_dossier = dossierRepository.countDossier();
}
