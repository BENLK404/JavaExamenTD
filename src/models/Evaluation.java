package models;

import database.Connectivity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Tools;

import java.sql.*;
import java.time.LocalDate;

import static models.Matiere.getIdMatiereByName;

public class Evaluation {
    private String nomEvaluation;
    private String description;
    private LocalDate evaluationDate;
    private int idEvaluation;
    private int idMatiere;
    private String nomMatiere;
    private String typeEvaluation;

    public String getNomEvaluation() {
        return nomEvaluation;
    }

    public void setNomEvaluation(String nomEvaluation) {
        this.nomEvaluation = nomEvaluation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(LocalDate evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
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

    public String getTypeEvaluation() {
        return typeEvaluation;
    }

    public void setTypeEvaluation(String typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
    }

    public Evaluation(String nomEvaluation, String description, LocalDate evaluationDate, int idEvaluation, int idMatiere, String nomMatiere, String typeEvaluation) {
        this.nomEvaluation = nomEvaluation;
        this.description = description;
        this.evaluationDate = evaluationDate;
        this.idEvaluation = idEvaluation;
        this.idMatiere = idMatiere;
        this.nomMatiere = nomMatiere;
        this.typeEvaluation = typeEvaluation;
    }

    public Evaluation() {
    }

    public Evaluation(String nomEvaluation, String description, LocalDate evaluationDate, int idEvaluation, String nomMatiere, String typeEvaluation) {
        this.nomEvaluation = nomEvaluation;
        this.description = description;
        this.evaluationDate = evaluationDate;
        this.idEvaluation = idEvaluation;
        this.nomMatiere = nomMatiere;
        this.typeEvaluation = typeEvaluation;
    }

    public Evaluation(String nomEvaluation, String description, LocalDate evaluationDate, String typeEvaluation, int idMatiere) {
        this.nomEvaluation = nomEvaluation;
        this.description = description;
        this.evaluationDate = evaluationDate;
        this.typeEvaluation = typeEvaluation;
        this.idMatiere = idMatiere;
    }

    // Getters and Setters

    @Override
    public String toString() {
        return String.format("%-15d %-30s %-40s %-20s %-30s %-20s",
                idEvaluation, nomEvaluation, description, evaluationDate, nomMatiere, typeEvaluation);
    }

    public static ObservableList<Evaluation> displayAllEvaluation() throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "select * from evaluations join matieres m on m.id_matiere = evaluations.id_matiere";
        ObservableList<Evaluation> listEvaluation = FXCollections.observableArrayList();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Evaluation evaluation = new Evaluation();
                evaluation.setNomEvaluation(resultSet.getString("nom_evaluation"));
                evaluation.setDescription(resultSet.getString("description"));
                evaluation.setIdEvaluation(resultSet.getInt("id_evaluation"));
                evaluation.setIdMatiere(resultSet.getInt("id_matiere"));
                evaluation.setNomMatiere(resultSet.getString("nom_matiere"));
                evaluation.setTypeEvaluation(resultSet.getString("type"));
                evaluation.setEvaluationDate(resultSet.getDate("date_evaluation").toLocalDate());
                listEvaluation.add(evaluation);
            }
        }
        return listEvaluation;
    }

    public void addEvaluation() throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String insertEvaluationQuery = "INSERT INTO evaluations (nom_evaluation, description, date_evaluation, type, id_matiere) VALUES (?, ?, ?, ?, ?)";
        String selectEtudiantsQuery = "SELECT id_etudiant FROM etudiants WHERE id_classe = (SELECT id_classe FROM classes join enseignants e on e.id_enseignant = classes.id_enseignant_principal WHERE id_matiere = ?)";
        String insertNoteQuery = "INSERT INTO notes (id_etudiant, id_evaluation) VALUES (?, ?)";

        try (PreparedStatement insertEvaluationStmt = connection.prepareStatement(insertEvaluationQuery, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement selectEtudiantsStmt = connection.prepareStatement(selectEtudiantsQuery);
             PreparedStatement insertNoteStmt = connection.prepareStatement(insertNoteQuery)) {

            insertEvaluationStmt.setString(1, nomEvaluation);
            insertEvaluationStmt.setString(2, description);
            insertEvaluationStmt.setDate(3, Date.valueOf(evaluationDate));
            insertEvaluationStmt.setString(4, typeEvaluation);
            insertEvaluationStmt.setInt(5, idMatiere);
            insertEvaluationStmt.executeUpdate();

            ResultSet generatedKeys = insertEvaluationStmt.getGeneratedKeys();
            int evaluationId = 0;
            if (generatedKeys.next()) {
                evaluationId = generatedKeys.getInt(1);
            }

            selectEtudiantsStmt.setInt(1, idMatiere);
            ResultSet etudiantsResultSet = selectEtudiantsStmt.executeQuery();

            while (etudiantsResultSet.next()) {
                int etudiantId = etudiantsResultSet.getInt("id_etudiant");
                insertNoteStmt.setInt(1, etudiantId);
                insertNoteStmt.setInt(2, evaluationId);
                insertNoteStmt.executeUpdate();
            }
        }
        Tools.textInGreen("Ajout de l'évaluation effectuée");
    }
    public static void deleteEvaluation(String nomEvaluation, String typeEvaluation, String nomMatiere) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        int idMatiere = getIdMatiereByName(nomMatiere);
        String sql = "DELETE FROM evaluations WHERE nom_evaluation = ? AND type = ? AND id_matiere = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nomEvaluation);
            preparedStatement.setString(2, typeEvaluation);
            preparedStatement.setInt(3, idMatiere);
            preparedStatement.executeUpdate();
            Tools.textInGreen("Suppression effectuée");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
