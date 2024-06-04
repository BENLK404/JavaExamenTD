package models;

import database.Connectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Matiere {
    private int idMatiere;
    private String nomMatiere;
    private String descriptionMatiere;


    public Matiere() {

    }

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

    public Matiere(String nomMatiere, String descriptionMatiere) {
        this.nomMatiere = nomMatiere;
        this.descriptionMatiere = descriptionMatiere;
    }

    public String getDescriptionMatiere() {
        return descriptionMatiere;
    }

    public void setDescriptionMatiere(String descriptionMatiere) {
        this.descriptionMatiere = descriptionMatiere;
    }
    public void addMatiere() throws SQLException {
        if (nomMatiere == null || descriptionMatiere == null ) {
            throw new IllegalArgumentException("Tous les champs doivent être remplis avant d'ajouter une matiere .");
        }
        Connection connection = Connectivity.getDbConnection();

        String sql = "INSERT INTO matieres (nom_matiere, description) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nomMatiere);
            ps.setString(2, descriptionMatiere);
            ps.executeUpdate();
            System.out.println("Matiere ajouter!");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteMatiere(int idMatiere) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "DELETE FROM matieres WHERE id_matiere = ?";
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idMatiere);
            preparedStatement.executeUpdate();
            System.out.println("Matiere supprimer!");
        }

    }
    public void updateMatiere(int idMatiere, String nomMatiere, String descriptionMatiere) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "update matieres set nom_matiere = ?, description = ? where id_matiere = ?";
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, nomMatiere);
            preparedStatement.setString(2, descriptionMatiere);
            preparedStatement.setInt(3, idMatiere);
            preparedStatement.executeUpdate();
            System.out.println("Matiere updater!");
        }


    }
    public void getMatiereById(int idMatiere) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "SELECT * FROM matieres WHERE id_matiere = ?";
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idMatiere);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idMatiere = resultSet.getInt("id_matiere");
                nomMatiere = resultSet.getString("nom_matiere");
                descriptionMatiere = resultSet.getString("description");
                System.out.println(toString());
            }
        }
    }
    public static int getIdMatierClasseByName(String nom) throws SQLException {
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

    @Override
    public String toString() {
        return "Matiere" +
                " id Matiere :" + idMatiere +
                ", nom :'" + nomMatiere + '\'' +
                ", description :'" + descriptionMatiere + '\'';
    }
}
