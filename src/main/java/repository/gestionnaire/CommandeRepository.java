package repository.gestionnaire;

import database.Database;
import model.Utilisateur;
import model.gestionnaire.Commande;
import model.gestionnaire.Fournisseur;

import java.sql.*;
import java.util.ArrayList;

public class CommandeRepository {
    private Connection cnx;
    public CommandeRepository() {
        this.cnx = Database.getConnexion();;
    }

    public int createCommande(Commande commande) {
        String add="INSERT INTO commande (raison_commande,ref_fournisseur," +
                "ref_gestionnaire,nom_commande,date_commande,status,date_livraison) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= cnx.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,commande.getRaisonCommande());
            ps.setInt(2,commande.getRefFournisseur().getIdFournisseur());
            ps.setInt(3,commande.getRefGestionnaire().getIdUtilisateur());
            ps.setString(4,commande.getNomCommande());
            ps.setString(5,commande.getDateCommande());
            ps.setString(6,commande.getStatus());
            ps.setString(7,commande.getDateLivraison());
            int id= ps.executeUpdate();
            return id;
        } catch (SQLException e) {
            System.out.println( "Erreur lors de la creation de la commande : "+e.getMessage());
            return 0;
        }

    }

    public ArrayList<Commande> getAllCommandes() {
        String getAll="SELECT C.*, U.* ,F.* FROM Commande as C INNER JOIN utilisateur as U on C.ref_gestionnaire=U.id_utilisateur " +
                "INNER JOIN fournisseur as F on C.ref_fournisseur = F.id_fournisseur";
        ArrayList<Commande> allCommandes=new ArrayList<>();
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
                Utilisateur gestionnaire= new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("role"),
                        rs.getInt("ref_filiere")
                );
                Commande commande= new Commande(
                        rs.getInt("id_commande"),
                        rs.getString("raison_commande"),
                        gestionnaire,
                        fournisseur,
                        rs.getString("nom_commande"),
                        rs.getString("date_commande"),
                        rs.getString("status"),
                        rs.getString("date_livraison")
                );
                allCommandes.add(commande);
            }
            return allCommandes;

        }catch(SQLException e){
            System.out.println( "Erreur lors de la recuperation des Commandes : "+e.getMessage());
            return null;
        }
    }

    public Commande getCommandeById(int idCommande) {
        String get="SELECT C.*, U.* ,F.* FROM Commande as C INNER JOIN utilisateur as U on C.ref_gestionnaire=U.id_utilisateur " +
        "INNER JOIN fournisseur as F on C.ref_fournisseur = F.id_fournisseur WHERE id_commande=? ";
        Commande commande=null;
        try{
            PreparedStatement ps= cnx.prepareStatement(get);
            ps.setInt(1,idCommande);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Utilisateur refGestionnaire=new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mdp"),
                        rs.getString("role"),
                        rs.getInt("ref_filiere")
                );
                Fournisseur refFournisseur = new Fournisseur(
                        rs.getInt("id_fournisseur"),
                        rs.getString("nom_fournisseur"),
                        rs.getString("adresse_fournisseur"),
                        rs.getString("mail_fournisseur"),
                        rs.getString("telephone_fournisseur"),
                        rs.getInt("delai_livraison_moyen"),
                        rs.getDouble("frais_livraison")
                );
                 commande= new Commande(
                        rs.getInt("id_commande"),
                        rs.getString("raison_commande"),
                        refGestionnaire,
                        refFournisseur,
                        rs.getString("nom_commande"),
                        rs.getString("date_commande"),
                         rs.getString("status"),
                         rs.getString("date_livraison")
                );
            }
            return commande;
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

