package repository.gestionnaire;

import database.Database;
import model.Utilisateur;
import model.gestionnaire.Commande;
import model.gestionnaire.Demande;
import model.gestionnaire.Fournisseur;

import java.sql.*;
import java.util.ArrayList;

public class DemandeRepository {

    private final Connection cnx;

    public DemandeRepository() {
        this.cnx = Database.getConnexion();
    }

    /** Insère une demande et retourne l'ID généré, ou -1 en cas d'erreur. */
    public int creerDemande(Demande demande) {
        String sql = "INSERT INTO demande (est_valide, ref_professeur, ref_gestionnaire, " +
                "raison_demande, status, urgence, date_demande) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, demande.isEstValide());
            ps.setInt(2, demande.getRefProfesseur());
            ps.setInt(3, demande.getRefGestionnaire());
            ps.setString(4, demande.getRaisonDemande());
            ps.setString(5, demande.getStatus());
            ps.setString(6, demande.getUrgence());
            ps.setString(7, demande.getDateDemande());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Erreur création demande : " + e.getMessage());
        }
        return -1;
    }

    /** Insère une ligne dans demande_fourniture. */
    public boolean creerDemandeFourniture(int idDemande, int idFourniture, int qte) {
        String sql = "INSERT INTO demande_fourniture (ref_demande, ref_fourniture, qte) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, idDemande);
            ps.setInt(2, idFourniture);
            ps.setInt(3, qte);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur création demande_fourniture : " + e.getMessage());
            return false;
        }
    }

    /** Retourne l'ID du premier gestionnaire disponible, ou -1 si aucun. */
    public int getPremierGestionnaireId() {
        String sql = "SELECT id_utilisateur FROM utilisateur WHERE role = 'Gestionnaire' LIMIT 1";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_utilisateur");
        } catch (SQLException e) {
            System.out.println("Erreur récupération gestionnaire : " + e.getMessage());
        }
        return -1;
    }

    public ArrayList<Demande> getAllDemandes() {
        String getAll="SELECT * FROM demande";
        ArrayList<Demande> allDemandes=new ArrayList<>();
        try{
            PreparedStatement ps= cnx.prepareStatement(getAll);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Demande demande= new Demande(
                  rs.getInt("id_demande"),
                  rs.getBoolean("est_valide"),
                  rs.getInt("ref_professeur"),
                  rs.getInt("ref_gestionnaire"),
                        rs.getString("raison_demande"),
                        rs.getString("status"),
                        rs.getString("urgence"),
                        rs.getString("date_demande")
                );
                allDemandes.add(demande);
            }
            return allDemandes;

        }catch(SQLException e){
            System.out.println( "Erreur lors de la recuperation des Demandes : "+e.getMessage());
            return null;
        }

    }
}