package repository.gestionnaire;

import database.Database;
import model.gestionnaire.Fournisseur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FournisseurRepository {
    private Connection cnx;
    public FournisseurRepository() {
        this.cnx = Database.getConnexion();
    }

    public boolean createFournisseur(Fournisseur fournisseur) {
        String add="INSERT INTO fournisseur (nom_fournisseur,adresse_fournisseur,mail_fournisseur," +
                "telephone_fournisseur,delai_livraison_moyen,frais_livraison) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(add);
            ps.setString(1,fournisseur.getNomfournisseur());
            ps.setString(2,fournisseur.getAdresseFournisseur());
            ps.setString(3,fournisseur.getMailFournisseur());
            ps.setString(4,fournisseur.getTelephoneFournisseur());
            ps.setInt(5,fournisseur.getDelaiLivraisionMoyen());
            ps.setDouble(6,fournisseur.getFraisLivraison());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println( "Erreur lors de l'ajout du Fournisseur : "+e.getMessage());
            return false;
        }

    }





}
