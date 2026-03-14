package repository;

import database.Database;
import model.DossierInscription;
import model.FicheEtudiant;
import model.Filiere;
import session.Session;

import java.sql.*;
import java.util.ArrayList;

public class DossierRepository {
    private Connection connection;

    public DossierRepository() {
        this.connection = Database.getConnexion();

    }

    public boolean ajouterDossier(DossierInscription doss) throws SQLException {
        String sql = "INSERT INTO dossier_inscription (date_inscription,heure,motivation_etudiant,ref_filiere,ref_fiche_etudiante) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(doss.getDate().toLocalDate()));
            ps.setTime(2, doss.getHeure());
            ps.setString(3, doss.getMotivation());
            ps.setInt(4, doss.getRefFiliere());
            ps.setInt(5, doss.getRef_fiche());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<DossierInscription> getAllDossiers(int refUser) throws SQLException {
        String sql = "SELECT date_inscription,heure,motivation_etudiant,nom_etudiant,prenom_etudiant,f.nom \n" +
                "                from dossier_inscription di  \n" +
                "                 inner JOIN filiere f on f.id_filiere = di.ref_filiere\n" +
                "                inner join fiche_etudiante fe ON di.ref_fiche_etudiante = fe.id_fiche_etudiante \n" +
                "                \n" +
                "                 where ref_createur = ?";
        ArrayList<DossierInscription> dossiers = new ArrayList<>();
        DossierInscription dossierInscription = null;
        int id = 0;
        Date date = null;
        Time heure = null;
        String motivation = null;
        int ref_filiere = 0;
        int ref_fiche = 0;
        String nom = null ;
        String prenom = null ;
        String nom_filiere = null ;
        DossierInscription dossier = null ;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,refUser);
            ResultSet resultatRequete = stmt.executeQuery();
            while (resultatRequete.next()) {
                date = resultatRequete.getDate("date_inscription");
                heure = resultatRequete.getTime("heure");
                motivation = resultatRequete.getString("motivation_etudiant");
                nom = resultatRequete.getString("nom_etudiant");
                prenom = resultatRequete.getString("prenom_etudiant");
                nom_filiere = resultatRequete.getString("nom");
                dossier = new DossierInscription(date,heure,motivation,nom,prenom,nom_filiere);
                dossiers.add(dossier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dossiers;
    }

    public boolean supprimerDossier(DossierInscription dossier) {
        String sql = "DELETE FROM dossier_inscription WHERE id_dossier_inscription =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, dossier.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DossierInscription getDossier(int id) throws SQLException {
        int id_dossier = 0;
        Date date = null;
        Time heure = null;
        String motivation = null;
        int ref_filiere = 0;
        int ref_fiche = 0;
        DossierInscription dossierInscription = null;

        String sql = "SELECT * FROM dossier WHERE id_dossier_inscription = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            id_dossier = rs.getInt("id_dossier_inscription");
            date = rs.getDate("date_inscription");
            heure = rs.getTime("heure");
            motivation = rs.getString("motivation_etudiant");
            ref_filiere = rs.getInt("ref_filiere");
            ref_fiche = rs.getInt("ref_fiche_etudiante");
            dossierInscription = new DossierInscription(id_dossier, date, heure, motivation, ref_filiere, ref_fiche);
        }
        return dossierInscription;
    }

    public boolean mettreAjourDossier(DossierInscription doss) throws SQLException {

        // Vérification de l'objet reçu
        System.out.println("Objet DossierInscription reçu :");
        System.out.println(" - ID dossier           : " + doss.getId());
        System.out.println(" - Date dossier         : " + doss.getDate());
        System.out.println(" - Heure dossier        : " + doss.getHeure());
        System.out.println(" - Motivation           : " + doss.getMotivation());
        System.out.println(" - Ref filière          : " + doss.getRefFiliere());
        System.out.println(" - Ref fiche étudiante  : " + doss.getRef_fiche());

        String sql = "UPDATE dossier_inscription " +
                "SET date_dossier = ?, heure = ?, motivation_etudiant = ?, ref_filiere = ?, ref_fiche_etudiante = ? " +
                "WHERE id_dossier_inscription = ?";

        System.out.println("\nRequête SQL préparée :\n" + sql);

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            // Debug avant affectation
            System.out.println("\nAffectation des paramètres :");

            ps.setDate(1, java.sql.Date.valueOf(doss.getDate().toLocalDate()));
            System.out.println(" - Param 1 (date_dossier)        = " + doss.getDate().toLocalDate());

            ps.setTime(2, doss.getHeure());
            System.out.println(" - Param 2 (heure)               = " + doss.getHeure());

            ps.setString(3, doss.getMotivation());
            System.out.println(" - Param 3 (motivation_etudiant) = " + doss.getMotivation());

            ps.setInt(4, doss.getRefFiliere());
            System.out.println(" - Param 4 (ref_filiere)         = " + doss.getRefFiliere());

            ps.setInt(5, doss.getRef_fiche());
            System.out.println(" - Param 5 (ref_fiche_etudiante) = " + doss.getRef_fiche());

            ps.setInt(6, doss.getId());
            System.out.println(" - Param 6 (id_dossier)          = " + doss.getId());

            System.out.println("\nExécution de la requête...");
            int rows = ps.executeUpdate();
            System.out.println("Résultat : " + rows + " ligne(s) mise(s) à jour.");

            return true;

        } catch (SQLException e) {

            System.out.println("Message : " + e.getMessage());

            // Détection spécifique des erreurs de clé étrangère
            if (e.getMessage().contains("foreign key")) {
                System.out.println("  ERREUR FK : La valeur ref_fiche_etudiante ("
                        + doss.getRef_fiche() +
                        ") n'existe pas dans dossier. !");
            }

        }

        return false;
    }

}


