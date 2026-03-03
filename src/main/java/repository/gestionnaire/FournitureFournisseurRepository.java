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
            ps.setInt(1, fournitureFournisseur.getRefFourniture());
            ps.setInt(2,fournitureFournisseur.getRefFournisseur());
            ps.setDouble(3,fournitureFournisseur.getPrix());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Erreur lors de l'ajout : "+e.getMessage());
            return false;
        }
    }

    public ArrayList <FournitureFournisseur> getAllFournisseursByFournitureId(int fournitureId) {
        String getAllFournisseur= "SELECT * FROM fourniture_fournisseur WHERE ref_fourniture = ?";
        ArrayList<FournitureFournisseur> allFournisseurs=new ArrayList<>();

        try{
            PreparedStatement ps = cnx.prepareStatement(getAllFournisseur);
            ps.setInt(1,fournitureId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                FournitureFournisseur fournisseurFourniture=new FournitureFournisseur(
                        rs.getInt("ref_fourniture"),
                        rs.getInt("ref_fournisseur"),
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
    public ArrayList <FournitureFournisseur> getAllFournitureByFournisseursId(int  fournisseurId) {
        String getAllFournisseur= "SELECT * FROM fourniture_fournisseur WHERE ref_fournisseur = ?";
        ArrayList<FournitureFournisseur> allFournitures=new ArrayList<>();

        try{
            PreparedStatement ps = cnx.prepareStatement(getAllFournisseur);
            ps.setInt(1,fournisseurId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                FournitureFournisseur fournisseurFourniture=new FournitureFournisseur(
                        rs.getInt("ref_fourniture"),
                        rs.getInt("ref_fournisseur"),
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

}
