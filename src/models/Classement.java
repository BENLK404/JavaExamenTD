package models;

import database.Connectivity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Classement {
    public Classement() {
    }

    private String typeEvaluation;
    private String classe;
    private String nomEvaluation;
    private double note;
    private LocalDate dateEvaluation;

    public Classement(String nom, String prenom, String nomClasse, String nomEvaluation, String type, String nomMatiere, double note, LocalDate dateEvaluation) {
        this.nomEtudient = nom;
        this.prenomEtudient = prenom;
        this.classeEtudient = nomClasse;
        this.nomEvaluation = nomEvaluation;
        this.typeEvaluation =type;
        this.matiere = nomMatiere;
        this.note = note;
        this.dateEvaluation = dateEvaluation;

    }

    public LocalDate getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(LocalDate dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public String getNomEtudient() {
        return nomEtudient;
    }

    public void setNomEtudient(String nomEtudient) {
        this.nomEtudient = nomEtudient;
    }

    public String getPrenomEtudient() {
        return prenomEtudient;
    }

    public void setPrenomEtudient(String prenomEtudient) {
        this.prenomEtudient = prenomEtudient;
    }

    public String getClasseEtudient() {
        return classeEtudient;
    }

    public void setClasseEtudient(String classeEtudient) {
        this.classeEtudient = classeEtudient;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(String idMatiere) {
        this.idMatiere = idMatiere;
    }

    private String nomEtudient;
    private String prenomEtudient;
    private String classeEtudient;
    private String matiere;
    private String idMatiere;

    public Classement(String typeEvaluation, String classe, String nomEvaluation, double note, LocalDate dateEvaluation, String nomEtudient, String prenomEtudient, String classeEtudient, String matiere, String idMatiere) {
        this.typeEvaluation = typeEvaluation;
        this.classe = classe;
        this.nomEvaluation = nomEvaluation;
        this.note = note;
        this.dateEvaluation = dateEvaluation;
        this.nomEtudient = nomEtudient;
        this.prenomEtudient = prenomEtudient;
        this.classeEtudient = classeEtudient;
        this.matiere = matiere;
        this.idMatiere = idMatiere;
    }

    public Classement(String typeEvaluation, String matiere, String classeEtudient, String prenomEtudient, String nomEtudient, LocalDate dateEvaluation, double note, String nomEvaluation) {
        this.typeEvaluation = typeEvaluation;
        this.matiere = matiere;
        this.classeEtudient = classeEtudient;
        this.prenomEtudient = prenomEtudient;
        this.nomEtudient = nomEtudient;
        this.dateEvaluation = dateEvaluation;
        this.note = note;
        this.nomEvaluation = nomEvaluation;
    }

    public Classement(String typeEvaluation, String classe, String nomEvaluation, double note) {
        this.typeEvaluation = typeEvaluation;
        this.classe = classe;
        this.nomEvaluation = nomEvaluation;
        this.note = note;
    }

    public String getTypeEvaluation() {
        return typeEvaluation;
    }

    public void setTypeEvaluation(String typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getNomEvaluation() {
        return nomEvaluation;
    }

    public void setNomEvaluation(String nomEvaluation) {
        this.nomEvaluation = nomEvaluation;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }






    public static ObservableList<Classement> displayClassementByMatierType(String nomMatiere, String typeEvaluation) throws SQLException {
        ObservableList<Classement> classements = FXCollections.observableArrayList();
        Connection connection = Connectivity.getDbConnection();
        String query = "select * from evaluations join notes n on evaluations.id_evaluation = n.id_evaluation join etudiants e on e.id_etudiant = n.id_etudiant join classes c on c.id_classe = e.id_classe join matieres m on m.id_matiere = evaluations.id_matiere where m.nom_matiere =? and evaluations.type = ? order by note desc";

        Classement classement;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nomMatiere);
            preparedStatement.setString(2, typeEvaluation);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classement = new Classement(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("nom_classe"),
                        resultSet.getString("nom_evaluation"),
                        resultSet.getString("type"),
                        resultSet.getString("nom_matiere"),
                        resultSet.getDouble("note"),
                        resultSet.getDate("date_evaluation").toLocalDate());
                classements.add(classement);
            }

        }
        displayRanking(classements);
        return classements;
    }

    public static void displayRanking(ObservableList<Classement> classements) {
        System.out.printf("%-15s %-15s %-20s %-30s %-20s %-25s %-10s %-15s\n",
                "Noms", "Prenoms", "Classes", "Evaluations", "Types", "Matières", "Notes", "Dates");
        printSeparator(160);
        for (Classement classement : classements) {
            System.out.println(classement);
        }
    }

    private static void printSeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-20s %-30s %-20s %-25s %-10.2f %-15s",
                nomEtudient, prenomEtudient, classeEtudient, nomEvaluation, typeEvaluation, matiere, note, dateEvaluation);
    }
    public static ObservableList<Classement> displayClassementByClasseType(String classe, String type,String niveau) throws SQLException {
        ObservableList<Classement> classements = FXCollections.observableArrayList();
        Classement classement;
        Connection connection = Connectivity.getDbConnection();
        String query = "select * from evaluations join notes n on evaluations.id_evaluation = n.id_evaluation join etudiants e on e.id_etudiant = n.id_etudiant join classes c on c.id_classe = e.id_classe join matieres m on m.id_matiere = evaluations.id_matiere where c.nom_classe =? and evaluations.type = ? and c.niveau = ? order by note desc";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, classe);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, niveau);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classement = new Classement(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("nom_classe"),
                        resultSet.getString("nom_evaluation"),
                        resultSet.getString("type"),
                        resultSet.getString("nom_matiere"),
                        resultSet.getDouble("note"),
                        resultSet.getDate("date_evaluation").toLocalDate());
                classements.add(classement);

            }
        }
        displayRanking(classements);
        return classements;
    }
    public static ObservableList<Classement> displayClassementGeneral(String type) throws SQLException {
        ObservableList<Classement> classements = FXCollections.observableArrayList();
        Classement classement;
        Connection connection = Connectivity.getDbConnection();
        String query = "select * from evaluations join notes n on evaluations.id_evaluation = n.id_evaluation join etudiants e on e.id_etudiant = n.id_etudiant join classes c on c.id_classe = e.id_classe join matieres m on m.id_matiere = evaluations.id_matiere where evaluations.type = ? order by note desc";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, type);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classement = new Classement(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("nom_classe"),
                        resultSet.getString("nom_evaluation"),
                        resultSet.getString("type"),
                        resultSet.getString("nom_matiere"),
                        resultSet.getDouble("note"),
                        resultSet.getDate("date_evaluation").toLocalDate());
                classements.add(classement);

            }
        }
        displayRanking(classements);
        return classements;
    }

        public static void main (String[]args) throws SQLException {
        displayClassementByClasseType("SIL","devoir","1");

    }

}
