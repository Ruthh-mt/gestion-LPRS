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
        String sql = "INSERT INTO fiche_etudiante (nom_etudiant,ref_createur, prenom_etudiant,email_etudiant,telephone,adresse,dernierDiplome) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fe.getNomEtudiant());
            ps.setInt(2,1);
            ps.setString(3, fe.getPrenomEtudiant());
            ps.setString(4, fe.getEmailEtudiant());
            ps.setString(5, fe.getTelephoneEtudiant());
            ps.setString(6, fe.getAdresseEtudiant());
            ps.setString(7, fe.getDernierDiplome());
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
            ResultSet resultatRequete = stmt.executeQuery(sql);
            while (resultatRequete.next()) {
                id = resultatRequete.getInt("id_fiche_etudiante");
                ref_createur = resultatRequete.getInt("ref_createur");
                nom = resultatRequete.getString("nom_etudiant");
                prenom = resultatRequete.getString("prenom_etudiant");
                email = resultatRequete.getString("email_etudiant");
                telephone = resultatRequete.getString("telephone");
                adresse = resultatRequete.getString("adresse");
                dernierDiplome = resultatRequete.getString("dernierDiplome");


                ficheEtudiant= new FicheEtudiant(id,ref_createur,nom,prenom,adresse,telephone,email,dernierDiplome) ;
                ficheEtudiants.add(ficheEtudiant);
                System.out.println(ficheEtudiant.toString());
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête  " + e.getMessage());
        }
        return ficheEtudiants;
    }
}
