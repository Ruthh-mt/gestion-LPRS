package repository;

import database.Database;
import model.FicheEtudiant;

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


    public Boolean deleteFicheEtudiant(int id) throws SQLException {
        String sql = "DELETE FROM fiche_etudiante WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.execute();
    }

    public Boolean getFicheEtudiant(int id) throws SQLException {
        String sql = "SELECT * FROM fiche_etudiante WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return true;
        }
        return false;
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
                telephone = resultatRequete.getString("telephone");
                adresse = resultatRequete.getString("adresse");
                dernierDiplome = resultatRequete.getString("dernier_diplome_etudiant");


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
