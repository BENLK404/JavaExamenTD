package models;

import database.Connectivity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Tools;

import java.sql.*;
import java.time.LocalDate;

public class Evaluation {
    private String nomEvaluation;
    private String description;
    private LocalDate evaluationDate;
    private int idEvaluation;
    private int idMatiere;
    private String nomMatiere;
    private String typeEvaluation;

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

    public void addEvaluation() throws SQLException {
//        if (nomEvaluation == null || description == null || typeEvaluation == null || nomMatiere == null || evaluationDate == null) {
//            throw new IllegalArgumentException("Tous les champs doivent être remplis avant d'ajouter une evaluation.");
//        }
        Connection connection = Connectivity.getDbConnection();
        String sql = "INSERT INTO evaluations (nom_evaluation, description, date_evaluation, type,id_matiere) VALUES (?, ?, ?, ?, ?)";
        try {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nomEvaluation);
                preparedStatement.setString(2, description);
                preparedStatement.setDate(3, Date.valueOf(evaluationDate));
                preparedStatement.setString(4, typeEvaluation);

                if (idMatiere != 0) {
                    preparedStatement.setInt(5, idMatiere);
                } else {
                    preparedStatement.setNull(5, Types.INTEGER);
                }
                preparedStatement.executeUpdate();
                Tools.textInGreen("Evaluation ajouté avec succès!");
            }
        } catch (NullPointerException | SQLException nullPointerException){
            nullPointerException.printStackTrace();
        }

    }

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

//    @Override
//    public String toString() {
//        return
//                "Id : " + idEvaluation +
//                " Nom : '" + nomEvaluation + '\'' +
//                " description : '" + description + '\'' +
//                " Date : '" + evaluationDate + '\'' +
//                " Matiere : '" + nomMatiere + '\'' +
//                " Type : '" + typeEvaluation + '\'';
//    }
//    public String toString() {
//        return String.format("%-10d %-20s %-20s %-10s %-20s %-20s",
//                idEvaluation,nomEvaluation, nomMatiere, typeEvaluation,  description,
//                evaluationDate);
//    }
//Cette méthode utilise String.format pour structurer les informations de l'évaluation en colonnes alignées, en spécifiant la largeur de chaque colonne. Les spécificateurs de format utilisés sont :
//
//            %-20s : Chaîne de caractères alignée à gauche avec une largeur minimale de 20 caractères.
//%-30s : Chaîne de caractères alignée à gauche avec une largeur minimale de 30 caractères.
//%-15s : Chaîne de caractères alignée à gauche avec une largeur minimale de 15 caractères.
//%-10d : Entier aligné à gauche avec une largeur minimale de 10 caractères.
//%-20s : Chaîne de caractères alignée à gauche avec une largeur minimale de 20 caractères.
//%-15s : Chaîne de caractères alignée à gauche avec une largeur minimale de 15 caractères.
//    @Override
//    public String toString() {
//        return String.format("%-20s %-30s %-15s %-10d %-10d %-20s %-15s",
//                nomEvaluation, description, evaluationDate, idEvaluation, idMatiere, nomMatiere, typeEvaluation);
//    }
//@Override
//    public String toString() {
//        return String.format("%-10d %-20s %-30s %-15s %-10d %-20s %-15s",
//                idEvaluation, nomEvaluation, description, evaluationDate, idMatiere, nomMatiere, typeEvaluation);
//    }
@Override
public String toString() {
    return String.format("%-10d %-20s %-30s %-15s %-20s %-15s",
            idEvaluation, nomEvaluation, description, evaluationDate, nomMatiere, typeEvaluation);
}


    public static ObservableList<Evaluation> displayAllEvaluation() throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "select * from evaluations join notes n on evaluations.id_evaluation = n.id_evaluation join etudiants e on e.id_etudiant = n.id_etudiant join matieres m on m.id_matiere = evaluations.id_matiere join classes c on c.id_classe = e.id_classe";
        ObservableList<Evaluation> listEvaluation = FXCollections.observableArrayList();


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
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

    public static void deleteEvaluation(String nomEvaluation, String typeEvaluation,String nomMatiere) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        int idMatier = Matiere.getIdMatierClasseByName(nomEvaluation);
        String sql = "delete from evaluations where nom_evaluation = ? and  type =? and id_matiere=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, nomEvaluation);
            preparedStatement.setString(2, typeEvaluation);
            preparedStatement.setInt(3, idMatier);
            preparedStatement.executeUpdate();
            System.out.println(" ");
            Tools.textInGreen("Suppression Effectuer");
        }
    }

//    public static ObservableList<Evaluation> displayAllClassement() throws SQLException {
//        Connection connection = Connectivity.getDbConnection();
//        String sql = "select * from evaluations join notes n on evaluations.id_evaluation = n.id_evaluation join etudiants e on e.id_etudiant = n.id_etudiant join matieres m on m.id_matiere = evaluations.id_matiere join classes c on c.id_classe = e.id_classe";
//        ObservableList<Evaluation> listEvaluation = FXCollections.observableArrayList();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Evaluation evaluation = new Evaluation();
//                evaluation.setNomEvaluation(resultSet.getString("nom_evaluation"));
//                evaluation.setNomMatiere(resultSet.getString("nom_matiere"));
//                evaluation.setEvaluationDate(resultSet.getDate("date_evaluation").toLocalDate());
//
//            }
//        }
//
//    }
}
