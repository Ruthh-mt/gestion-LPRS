package repository.gestionnaire;

import database.Database;
import model.gestionnaire.Commande;
import model.gestionnaire.Fourniture;
import model.gestionnaire.CommandeFourniture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeFournitureRepository {

    private Connection cnx;
    public CommandeFournitureRepository() {
        this.cnx = Database.getConnexion();
    }

    public Boolean addCommandeFourniture(CommandeFourniture commandeFourniture) {
        String add= "INSERT INTO commande_fourniture (ref_commande, ref_fourniture,qte) VALUES (?, ?,?)";
        try{
            PreparedStatement ps = cnx.prepareStatement(add);
            ps.setInt(1, commandeFourniture.getRefCommande().getIdCommande());
            ps.setInt(2,commandeFourniture.getRefFourniture().getIdFourniture());
            ps.setDouble(3,commandeFourniture.getQte());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println("Erreur lors de l'ajout : "+e.getMessage());
            return false;
        }
    }

    public ArrayList<CommandeFourniture> getAllFournitureByCommandeId(Commande commande) {
        String getAllFourniture= "SELECT CF.* , F.* From commande_fourniture as CF Inner join fourniture " +
                "as F on CF.ref_fourniture = F.id_fourniture Where CF.ref_commande=?";
        ArrayList<CommandeFourniture> allFournitures=new ArrayList<>();
        try{
            PreparedStatement ps = cnx.prepareStatement(getAllFourniture);
            ps.setInt(1,commande.getIdCommande());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                Fourniture fourniture = new Fourniture (
                        rs.getInt("id_fourniture"),
                        rs.getString("libelle"),
                        rs.getString("description"),
                        rs.getInt("stock_actuelle"),
                        rs.getInt("stock_minimum")
                );

                CommandeFourniture fournisseurFourniture=new CommandeFourniture(
                        commande,
                        fourniture,
                        rs.getInt("qte")
                );
                allFournitures.add(fournisseurFourniture);
            }
            return allFournitures;
        }catch(SQLException e)
        {
            System.out.println("Erreur lors de la recuperation des fournisseurs: "+e.getMessage());
            return null;
        }
    }

    public boolean mettreAJourCommandeFourniture(CommandeFourniture commandeFourniture) {
        String update="UPDATE commande_fourniture SET ref_fourniture=? , qte=? ref_commande=? ";
        try{
            PreparedStatement ps = cnx.prepareStatement(update);
            ps.setInt(1,commandeFourniture.getRefFourniture().getIdFourniture());
            ps.setInt(2,commandeFourniture.getQte());
            ps.setInt(3,commandeFourniture.getRefCommande().getIdCommande());
            ps.executeUpdate();
            return true;

        }catch(SQLException e){
            System.out.println("Errur lors de la modification d'une commande" + '\n'+ ">> "+ e.getMessage());
            return false;
        }
    }

    public boolean supprimerUneFournitureByCommandeId(CommandeFourniture commandeFourniture){
        String update="DELETE FROM commande_fourniture WHERE ref_fourniture=? AND ref_commande=? ";
        try{
            PreparedStatement ps = cnx.prepareStatement(update);
            ps.setInt(1,commandeFourniture.getRefFourniture().getIdFourniture());
            ps.setInt(2,commandeFourniture.getRefCommande().getIdCommande());
            ps.executeUpdate();
            return true;

        }catch(SQLException e){
            System.out.println("Erreur lors de la suppression d'une fourniture d'une commande" + '\n'+ ">> "+ e.getMessage());
            return false;
        }
    }

    public boolean supprimerToutesFournituresByCommandeId(int idCommande){
        String update="DELETE FROM commande_fourniture WHERE  ref_commande=? ";
        try{
            PreparedStatement ps = cnx.prepareStatement(update);
            ps.setInt(1,idCommande);
            ps.executeUpdate();
            return true;

        }catch(SQLException e){
            System.out.println("Erreur lors de la suppression des fournitures d'une commande" + '\n'+ ">> "+ e.getMessage());
            return false;
        }
    }

}
