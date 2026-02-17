package repository.gestionnaire;

import database.Database;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.Fourniture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FournitureRepository {
    private Connection cnx;
    public FournitureRepository() {
        this.cnx = Database.getConnexion();;
    }
    private int idFourniture;
    private String libelle;
    private String description;
    private int stockActuelle;
    private int stockMinimum;

    public boolean createFourniture(Fourniture fourniture) {
        String add="INSERT INTO fourniture (libelle,description,stock_actuelle" +
                ",stock_minimum) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(add);
            ps.setString(1,fourniture.getLibelle());
            ps.setString(2,fourniture.getDescription());
            ps.setInt(3,fourniture.getStockActuelle());
            ps.setInt(4,fourniture.getStockMinimum());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println( "Erreur lors de l'ajout du Fournisseur : "+e.getMessage());
            return false;
        }

    }

    public ArrayList<Fourniture> getAllFournitures() {
        String getAll="SELECT * FROM fourniture";
        ArrayList<Fourniture> allFournitures=new ArrayList<>();
        try{
            PreparedStatement ps= cnx.prepareStatement(getAll);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Fourniture fourniture= new Fourniture(
                        rs.getInt("id_fourniture"),
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("stock_actuelle"),
                        rs.getInt("stock_minimum")
                );
                allFournitures.add(fourniture);
            }
            return allFournitures;

        }catch(SQLException e){
            System.out.println( "Erreur lors de la recuperation des fournitures: "+e.getMessage());
            return null;
        }

    }
}
