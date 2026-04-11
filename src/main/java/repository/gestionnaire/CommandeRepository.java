package repository.gestionnaire;

import database.Database;
import model.gestionnaire.Commande;
import model.gestionnaire.Fournisseur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeRepository {
    private Connection cnx;
    public CommandeRepository() {
        this.cnx = Database.getConnexion();;
    }
    private int idCommande;
    private String raisonCommande;
    private int refGestionnaire;
    private int refFournisseur;
    private String nomCommande;
    private String dateCommande;
    public boolean createCommande(Commande commande) {
        String add="INSERT INTO commande (raison_commande,ref_fournisseur," +
                "ref_gestionnaire,nom_commande,date_commande) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(add);
            ps.setString(1,commande.getRaisonCommande());
            ps.setInt(2,commande.getRefFournisseur());
            ps.setInt(3,commande.getRefGestionnaire());
            ps.setString(4,commande.getNomCommande());
            ps.setString(5,commande.getDateCommande());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println( "Erreur lors de la creation de la commande : "+e.getMessage());
            return false;
        }

    }

    public ArrayList<Commande> getAllCommandes() {
        String getAll="SELECT * FROM Commande";
        ArrayList<Commande> allCommandes=new ArrayList<>();
        try{
            PreparedStatement ps= cnx.prepareStatement(getAll);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Commande commande= new Commande(
                        rs.getInt("id_commande"),
                        rs.getString("raison_commande"),
                        rs.getInt("ref_fournisseur"),
                        rs.getInt("ref_gestionnaire"),
                        rs.getString("nom_commande"),
                        rs.getString("date_commande")
                );
                allCommandes.add(commande);
            }
            return allCommandes;

        }catch(SQLException e){
            System.out.println( "Erreur lors de la recuperation des Commandes : "+e.getMessage());
            return null;
        }

    }
    public int countCommandes() {
        String sql = "SELECT COUNT(*) FROM commande " ;
        int nb_commande = 0;
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                nb_commande++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nb_commande;
    }

}

