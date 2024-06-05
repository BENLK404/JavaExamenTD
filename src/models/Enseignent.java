package models;

import database.Connectivity;
import tools.Tools;
import views.EnseignentView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Enseignent {
    private int idEnseignant;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private int idMatiere;
    private String matiere;
    private String classe;

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Enseignent(int idEnseignant, String nom, String prenom, String email, String telephone, String matiere, String classe) {
        this.idEnseignant = idEnseignant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.matiere = matiere;
        this.classe = classe;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public Enseignent(int idEnseignant, String nom, String prenom, String email, String telephone, String matiere) {
        this.idEnseignant = idEnseignant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.matiere = matiere;
    }

    public Enseignent(int idEnseignant, String nom, String prenom, String email, String telephone, int idMatiere) {
        this.idEnseignant = idEnseignant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.idMatiere = idMatiere;
    }

    public Enseignent(String nom, String prenom, String email, String telephone, int idMatiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.idMatiere = idMatiere;
    }

    // Getters and Setters
    public int getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-15s %-20s %-25s %-20s %-10s %15s",
                idEnseignant, nom, prenom, email, telephone, matiere,classe);
    }
    public static void addEnseignant(Enseignent enseignant) {
        String sql = "INSERT INTO enseignants (nom, prenom, email, telephone, id_matiere) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Connectivity.getDbConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enseignant.getNom());
            preparedStatement.setString(2, enseignant.getPrenom());
            preparedStatement.setString(3, enseignant.getEmail());
            preparedStatement.setString(4, enseignant.getTelephone());
            preparedStatement.setInt(5, enseignant.getIdMatiere());
            preparedStatement.executeUpdate();
            Tools.textInGreen("Enseignant ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Enseignent> getAllEnseignants() {
        List<Enseignent> enseignants = new ArrayList<>();
        String sql = "select * from enseignants " +
                "join matieres m on m.id_matiere = enseignants.id_matiere " +
                "join classes c on enseignants.id_enseignant = c.id_enseignant_principal";
        try (Connection connection = Connectivity.getDbConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Enseignent enseignant = new Enseignent(
                        resultSet.getInt("id_enseignant"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone"),
                        resultSet.getString("nom_matiere"),
                        resultSet.getString("nom_classe")
                );
                enseignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EnseignentView.afficherEnseignents(enseignants);
        return enseignants;
    }

    public Enseignent getEnseignantById(int id) {
        String sql = "SELECT * FROM enseignants WHERE id_enseignant = ?";
        try (Connection connection = Connectivity.getDbConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Enseignent(
                        resultSet.getInt("id_enseignant"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone"),
                        resultSet.getString("nom_classe"),
                        resultSet.getString("nom_matiere")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateEnseignant(int idEnseignant ,Enseignent enseignant) {
        String sql = "UPDATE enseignants SET nom = ?, prenom = ?, email = ?, telephone = ?, id_matiere = ? WHERE id_enseignant = ?";
        try (Connection connection = Connectivity.getDbConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enseignant.getNom());
            preparedStatement.setString(2, enseignant.getPrenom());
            preparedStatement.setString(3, enseignant.getEmail());
            preparedStatement.setString(4, enseignant.getTelephone());
            preparedStatement.setInt(5, enseignant.getIdMatiere());
            preparedStatement.setInt(6, idEnseignant);
            preparedStatement.executeUpdate();
            System.out.println(" ");
            Tools.textInGreen("Enseignant mis à jour avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEnseignant(String nom , String prenom) {
        String sql = "DELETE FROM enseignants WHERE nom=? and prenom=?";
        try (Connection connection = Connectivity.getDbConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.executeUpdate();
            System.out.println(" ");
            Tools.textInGreen("Enseignant supprimé avec succès !");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(" ");
            Tools.textInRed("Cet enseignent est liée à une classe.");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[]args){

    }
}
