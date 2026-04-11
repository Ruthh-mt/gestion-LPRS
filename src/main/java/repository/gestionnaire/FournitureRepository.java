package repository.gestionnaire;

import database.Database;
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

    public boolean createFourniture(Fourniture fourniture) {
        String add="INSERT INTO fourniture (libelle,description,stock_actuelle,stock_minimum) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(add);
            ps.setString(1,fourniture.getLibelle());
            ps.setString(2,fourniture.getDescription());
            ps.setInt(3,fourniture.getStockActuelle());
            ps.setInt(4,fourniture.getStockMinimum());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println( "Erreur lors de l'ajout de la Fourniture : "+e.getMessage());
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
            System.out.println( "Erreur lors de la recuperation des Fourniture : "+e.getMessage());
            return null;
        }

    }

    public Fourniture getFournitureById(Fourniture fourniture) {
        String get = "SELECT * FROM fourniture WHERE id_fourniture=? ";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(get);
            stmt.setInt(1, fourniture.getIdFourniture());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                 fourniture= new Fourniture (
                        rs.getInt("id_fourniture"),
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("stock_actuelle"),
                        rs.getInt("stock_minimum")
                );
            }
            return fourniture;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la Fourniture: " + '\n' + " >>" + e.getMessage());
            return null;
        }
    }


    public boolean supprimerFournitureParId(Fourniture fourniture) {
        String delete = "DELETE FROM fourniture WHERE id_fourniture=? ";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(delete);
            stmt.setInt(1, fourniture.getIdFourniture());
            stmt.executeUpdate();
            System.out.println("Fourniture deleted with success : " + '\n' + ">> Fourniture was : " + fourniture.getLibelle());
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppresion : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public boolean mettreAJourFourniture(Fourniture fourniture) {
        String update = "UPDATE Fourniture SET libelle= ? ,description=? ,stock_actuelle=? ,stock_minimum=?  WHERE id_fourniture=?";
        try {
            PreparedStatement ps = this.cnx.prepareStatement(update);
            ps.setString(1,fourniture.getLibelle());
            ps.setString(2,fourniture.getDescription());
            ps.setInt(3,fourniture.getStockActuelle());
            ps.setInt(4,fourniture.getStockMinimum());
            ps.setInt(5,fourniture.getIdFourniture());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise a jour du Fourniture : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public int countQteStock() {
        String sql = "SELECT SUM(stock_actuelle) FROM fourniture" ;
        int qte_stock = 0;
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                qte_stock = resultSet.getInt("SUM(stock_actuelle)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return qte_stock;
    }

}
