package repository;

import database.Database;
import model.FicheEtudiant;
import model.Utilisateur;
import session.Session;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.Types.NULL;

public class FicheEtudiantRepository {
    private  Connection connection;

    public FicheEtudiantRepository() {
        this.connection = Database.getConnexion();
    }

    public boolean AjouterFicheEtudiant(FicheEtudiant fe) throws SQLException {
        String sql = "INSERT INTO fiche_etudiante (ref_createur,nom_etudiant,prenom_etudiant,email_etudiant,dernier_diplome_etudiant,telephone,adresse) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, fe.getRefCreateur());
            ps.setString(2,fe.getNomEtudiant());
            ps.setString(3, fe.getPrenomEtudiant());
            ps.setString(4, fe.getEmailEtudiant());
            ps.setString(5, fe.getDernierDiplome());
            ps.setString(6, fe.getTelephoneEtudiant());
            ps.setString(7, fe.getAdresseEtudiant());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean mettreAJourFiche(FicheEtudiant fe,int id) throws SQLException {
        String update = "UPDATE fiche_etudiante SET nom_etudiant=?,prenom_etudiant=?,email_etudiant=?,dernier_diplome_etudiant=?,telephone=?,adresse=? WHERE id_fiche_etudiante=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(update);
            stmt.setString(1, fe.getNomEtudiant());
            stmt.setString(2, fe.getPrenomEtudiant());
            stmt.setString(3, fe.getEmailEtudiant());
            stmt.setString(4, fe.getDernierDiplome());
            stmt.setString(5, fe.getTelephoneEtudiant());
            stmt.setString(6, fe.getAdresseEtudiant());
            stmt.setInt(7,id);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise a jour de lA FICHE : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }


    public Boolean deleteFicheEtudiant(int id) throws SQLException {
        String sql = "DELETE FROM fiche_etudiante WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.execute();
    }

    public FicheEtudiant getFicheEtudiant(int id) throws SQLException {

        int ref_createur = 0 ;
        String nom_etudiant = "";
        String prenom_etudiant = "";
        String email_etudiant = "";
        String dernier_diplome_etudiant = "";
        String telephone_etudiant = "";
        String adresse_etudiant = "";
        FicheEtudiant fe = null ;

        String sql = "SELECT * FROM fiche_etudiante WHERE id_fiche_etudiante = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
       while (rs.next()) {
           ref_createur = rs.getInt("ref_createur");
           nom_etudiant = rs.getString("nom_etudiant");
           prenom_etudiant = rs.getString("prenom_etudiant");
           email_etudiant = rs.getString("email_etudiant");
           dernier_diplome_etudiant = rs.getString("dernier_diplome_etudiant");
           telephone_etudiant = rs.getString("telephone");
           adresse_etudiant = rs.getString("adresse");
           fe = new FicheEtudiant(id,ref_createur,nom_etudiant,prenom_etudiant,email_etudiant,dernier_diplome_etudiant,telephone_etudiant,adresse_etudiant);
       }
       return fe;

}

    public ArrayList<FicheEtudiant> getToutesLesFiches() throws SQLException {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS nb FROM fiche_etudiante");
            if (rs.next()) System.out.println("NB lignes fiche_etudiante = " + rs.getInt("nb"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * from fiche_etudiante";
        ArrayList<FicheEtudiant> ficheEtudiants = new ArrayList<>();
        int id = 0;
        int ref_createur = 0 ;
        String nom = "";
        String prenom = "";
        String email = "";
        String telephone = "";
        String adresse = "";
        String dernierDiplome = "";
        FicheEtudiant ficheEtudiant = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultatRequete = stmt.executeQuery();
            while (resultatRequete.next()) {
                id = resultatRequete.getInt("id_fiche_etudiante");
                ref_createur = resultatRequete.getInt("ref_createur");
                nom = resultatRequete.getString("nom_etudiant");
                prenom = resultatRequete.getString("prenom_etudiant");
                email = resultatRequete.getString("email_etudiant");
                dernierDiplome = resultatRequete.getString("dernier_diplome_etudiant");

                telephone = resultatRequete.getString("telephone");
                adresse = resultatRequete.getString("adresse");
                ficheEtudiant = new FicheEtudiant(
                        id ,
                        ref_createur ,
                        nom,
                prenom,
                email ,
                dernierDiplome ,
                telephone ,
                adresse
                );
                ficheEtudiants.add(ficheEtudiant);
                ResultSet rsCount = connection.createStatement()
                        .executeQuery("SELECT COUNT(*) AS nb FROM fiche_etudiante");
                if (rsCount.next()) System.out.println("NB lignes = " + rsCount.getInt("nb"));

            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête  " + e.getMessage());
        }
        return ficheEtudiants;
    }
}
