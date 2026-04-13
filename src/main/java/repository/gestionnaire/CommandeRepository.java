package repository.gestionnaire;

import database.Database;
import model.Utilisateur;
import model.gestionnaire.Commande;
import model.gestionnaire.Fournisseur;
import model.gestionnaire.Fourniture;

import java.sql.*;
import java.util.ArrayList;

public class CommandeRepository {
    private Connection cnx;
    public CommandeRepository() {
        this.cnx = Database.getConnexion();
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

    public boolean supprimerCommandeParId(Commande commande) {
        String delete = "DELETE FROM commande WHERE id_commande=? ";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(delete);
            stmt.setInt(1, commande.getIdCommande());
            stmt.executeUpdate();
            System.out.println("Commande deleted with success : " + '\n' + ">> Commande was : " + commande.getNomCommande());
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppresion : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public boolean mettreAJourCommande(Commande commande) {
        String update = "UPDATE commande SET raison_commande= ? ,nom_commande=? ,status=? WHERE id_commande=?";
        try {
            PreparedStatement ps = this.cnx.prepareStatement(update);
            ps.setString(1,commande.getRaisonCommande());
            ps.setString(2,commande.getNomCommande());
            ps.setString(3,commande.getStatus());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise a jour de la commande : " + '\n' + " >>" + e.getMessage());
            return false;
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

    public int getNbCommande(){
        int nbCommande=0;
        String count="SELECT COUNT(*) as nb_commande FROM commande";
        try{
            PreparedStatement ps = cnx.prepareStatement(count);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 nbCommande=rs.getInt("nb_commande");
             }
            return nbCommande;
        } catch (SQLException e) {
            System.out.println( "Erreur lors de la recuperation du nombre de Commandes : "+e.getMessage());
            throw new RuntimeException("Erreur lors de la recuperation des commande",e); // petit test et faut que je me penche sur les exception sa a l'air interressant
        }
    }
    
    public int getNbCommandeEnCours(){
        int nbCommande=0;
        String countEnCours="SELECT COUNT(*) as nb_commande FROM commande WHERE status= ?";
        try{
            PreparedStatement ps = cnx.prepareStatement(countEnCours);
            ps.setString(1,"En Cours");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                nbCommande=rs.getInt("nb_commande");
            }
            return nbCommande;
        } catch (SQLException e) {
            System.out.println( "Erreur lors de la recuperation du nombre de Commandes : "+e.getMessage());
            throw new RuntimeException("Erreur lors de la recuperation des commande",e); // petit test et faut que je me penche sur les exception sa a l'air interressant
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

