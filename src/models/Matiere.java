package models;

import database.Connectivity;
import tools.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Matiere {
    private int idMatiere;
    private String nomMatiere;
    private String descriptionMatiere;

    public Matiere() {}

    public Matiere(int idMatiere, String nomMatiere, String descriptionMatiere){
        this.idMatiere = idMatiere;
        this.nomMatiere = nomMatiere;
        this.descriptionMatiere = descriptionMatiere;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getDescriptionMatiere() {
        return descriptionMatiere;
    }

    public void setDescriptionMatiere(String descriptionMatiere) {
        this.descriptionMatiere = descriptionMatiere;
    }

    public Matiere(String nomMatiere, String descriptionMatiere) {
        this.nomMatiere = nomMatiere;
        this.descriptionMatiere = descriptionMatiere;
    }

    public void addMatiere() throws SQLException {
        if (nomMatiere == null) {
            Tools.textInRed("Tous les champs doivent être remplis avant d'ajouter une matière.");
            return;
        }
        Connection connection = Connectivity.getDbConnection();
        String sql = "INSERT INTO matieres (nom_matiere, description) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nomMatiere);
            ps.setString(2, descriptionMatiere);
            ps.executeUpdate();
            System.out.println(" ");
            Tools.textInGreen("Matière ajoutée!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMatiere(String matiere) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "DELETE FROM matieres WHERE nom_matiere = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, matiere);
            preparedStatement.executeUpdate();
            System.out.println(" ");
            Tools.textInGreen("Matière supprimée!");
        }
    }

    public static void updateMatiere(int idMatiere, String nomMatiere, String descriptionMatiere) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "UPDATE matieres SET nom_matiere = ?, description = ? WHERE id_matiere = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, nomMatiere);
            preparedStatement.setString(2, descriptionMatiere);
            preparedStatement.setInt(3, idMatiere);
            preparedStatement.executeUpdate();
            System.out.println(" ");
            Tools.textInGreen("Matière mise à jour!");
        }
    }

    public void getMatiereById(int idMatiere) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "SELECT * FROM matieres WHERE id_matiere = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idMatiere);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                this.idMatiere = resultSet.getInt("id_matiere");
                this.nomMatiere = resultSet.getString("nom_matiere");
                this.descriptionMatiere = resultSet.getString("description");
                System.out.println(toString());
            }
        }
    }

    public static int getIdMatiereByName(String nom) throws SQLException {
        String sql = "SELECT id_matiere FROM matieres WHERE nom_matiere = ?";
        int id = 0;
        try (Connection connection = Connectivity.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id_matiere");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return id;
    }

    public static List displayMatiere() throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "SELECT * FROM matieres";
        List<Matiere> matieres = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id_matiere");
                String nom = resultSet.getString("nom_matiere");
                String description = resultSet.getString("description");
                matieres.add(new Matiere(id, nom, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return matieres;

    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-50s", idMatiere, nomMatiere, descriptionMatiere);
    }
    public static void main (String[] args) throws SQLException {
Matiere.displayMatiere();
    }
}
