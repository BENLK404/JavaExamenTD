package models;

import database.Connectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LaClasse {
    private int id_classe;
    private int id_enseignant_principal;
    private String nom_classe;
    private String niveau;

    public LaClasse(int id_classe, int id_enseignant_principal, String nom_classe, String niveau) {
        this.id_classe = id_classe;
        this.id_enseignant_principal = id_enseignant_principal;
        this.nom_classe = nom_classe;
        this.niveau = niveau;
    }

    public LaClasse(int id_enseignant_principal, String nom_classe, String niveau) {
        this.id_enseignant_principal = id_enseignant_principal;
        this.nom_classe = nom_classe;
        this.niveau = niveau;
    }

    public int getId_classe() {
        return id_classe;
    }


    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public int getId_enseignant_principal() {
        return id_enseignant_principal;
    }

    public void setId_enseignant_principal(int id_enseignant_principal) {
        this.id_enseignant_principal = id_enseignant_principal;
    }

    public String getNom_classe() {
        return nom_classe;
    }

    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public LaClasse() {

    }

    public static int getIdClasseByName(String nom) throws SQLException {
        String sql = "SELECT id_classe FROM classes WHERE nom_classe = ?";
        int id = 0;

        try (Connection connection = Connectivity.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id_classe");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return id;
    }

}
