package repository.gestionnaire;

import database.Database;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.Fourniture;
import model.gestionnaire.FournitureFournisseur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FournitureFournisseurRepository {
    private Connection cnx;
    public FournitureFournisseurRepository() {
        this.cnx = Database.getConnexion();;
    }

    public Boolean addFournitureFournisseur(FournitureFournisseur fournitureFournisseur) {
        String add= "INSERT INTO fourniture_fournisseur (ref_fourniture, ref_fournisseur,prix) VALUES (?, ?,?)";
        try{
            PreparedStatement ps = cnx.prepareStatement(add);
            ps.setInt(1, fournitureFournisseur.getRefFourniture().getIdFourniture());
            ps.setInt(2,fournitureFournisseur.getRefFournisseur().getIdFournisseur());
            ps.setDouble(3,fournitureFournisseur.getPrix());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Erreur lors de l'ajout : "+e.getMessage());
            return false;
        }
    }

    public ArrayList <FournitureFournisseur> getAllFournisseursByFournitureId(Fourniture fourniture) {
        String getAllFournisseur= "SELECT FF.*, F.* FROM fourniture_fournisseur as FF INNER JOIN fournisseur as F on FF.ref_fournisseur= F.id_fournisseur WHERE ref_fourniture = ?";
        ArrayList<FournitureFournisseur> allFournisseurs=new ArrayList<>();
        try{
            PreparedStatement ps = cnx.prepareStatement(getAllFournisseur);
            ps.setInt(1,fourniture.getIdFourniture());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Fournisseur fournisseur= new Fournisseur (
                        rs.getInt("id_fournisseur"),
                        rs.getString("nom_fournisseur"),
                        rs.getString("adresse_fournisseur"),
                        rs.getString("mail_fournisseur"),
                        rs.getString("telephone_fournisseur"),
                        rs.getInt("delai_livraison_moyen"),
                        rs.getDouble("frais_livraison")
                );

                FournitureFournisseur fournisseurFourniture=new FournitureFournisseur(
                        fourniture,
                        fournisseur,
                        rs.getDouble("prix")
                );
                allFournisseurs.add(fournisseurFourniture);
            }
            return allFournisseurs;
        }catch(SQLException e)
            {
            System.out.println("Erreur lors de la recuperation des fournisseurs: "+e.getMessage());
            return null;
            }
    }
    public ArrayList <FournitureFournisseur> getAllFournitureByFournisseursId(Fournisseur fournisseur) {
        String getAllFournisseur= "SELECT FF.*, F.* FROM fourniture_fournisseur as FF INNER JOIN fourniture as F on FF.ref_fourniture= F.id_fourniture WHERE ref_fournisseur = ?";
        ArrayList<FournitureFournisseur> allFournitures=new ArrayList<>();

        try{
            PreparedStatement ps = cnx.prepareStatement(getAllFournisseur);
            ps.setInt(1,fournisseur.getIdFournisseur());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Fourniture fourniture= new Fourniture(
                        rs.getInt("id_fourniture"),
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("stock_actuelle"),
                        rs.getInt("stock_minimum")
                );

                FournitureFournisseur fournisseurFourniture=new FournitureFournisseur(
                        fourniture,
                        fournisseur,
                        rs.getDouble("prix")
                );
                allFournitures.add(fournisseurFourniture);
            }
            return allFournitures;
        }catch(SQLException e)
        {
            System.out.println("Erreur lors de la recuperation des fournitures : "+e.getMessage());
            return null;
        }
    }

    public boolean mettreAJourFournitureFournisseur(FournitureFournisseur fournitureFournisseur){
        String update="UPDATE fourniture_fournisseur SET prix=? WHERE ref_fourniture=? AND ref_fournisseur=? ";
        try{
            PreparedStatement ps = cnx.prepareStatement(update);
            ps.setDouble(1,fournitureFournisseur.getPrix());
            ps.setInt(2,fournitureFournisseur.getRefFourniture().getIdFourniture());
            ps.setInt(3,fournitureFournisseur.getRefFournisseur().getIdFournisseur());
            ps.executeUpdate();
            return true;

        }catch(SQLException e){
            System.out.println("Errur lors de la modification d'une liaison fournisseur fourniture" + '\n'+ ">> "+ e.getMessage());
            return false;
        }
    }

    public boolean supprimerFournitureFournisseur(FournitureFournisseur fournitureFournisseur){
        String update="DELETE FROM fourniture_fournisseur WHERE ref_fourniture=? AND ref_fournisseur=? ";
        try{
            PreparedStatement ps = cnx.prepareStatement(update);
            ps.setInt(1,fournitureFournisseur.getRefFourniture().getIdFourniture());
            ps.setInt(2,fournitureFournisseur.getRefFournisseur().getIdFournisseur());
            ps.executeUpdate();
            return true;

        }catch(SQLException e){
            System.out.println("Errur lors de la suppression d'une liaison fournisseur fourniture" + '\n'+ ">> "+ e.getMessage());
            return false;
        }
    }
}
