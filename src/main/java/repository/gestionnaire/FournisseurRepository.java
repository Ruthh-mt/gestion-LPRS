package repository.gestionnaire;

import database.Database;
import model.gestionnaire.Fournisseur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public void getNameFournisseurById(int idFournisseur) {

    }

    public ArrayList<Fournisseur> getAllFournisseur() {
        String getAll="SELECT * FROM fournisseur";
        ArrayList<Fournisseur> allFournisseurs=new ArrayList<>();
        try{
            PreparedStatement ps= cnx.prepareStatement(getAll);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Fournisseur fournisseur= new Fournisseur(
                        rs.getInt("id_fournisseur"),
                        rs.getString("nom_fournisseur"),
                        rs.getString("adresse_fournisseur"),
                        rs.getString("mail_fournisseur"),
                        rs.getString("telephone_fournisseur"),
                        rs.getInt("delai_livraison_moyen"),
                        rs.getDouble("frais_livraison")
                );
                allFournisseurs.add(fournisseur);
            }
            return allFournisseurs;

        }catch(SQLException e){
            System.out.println( "Erreur lors de la recuperation des fournisseur : "+e.getMessage());
            return null;
        }

    }





}
