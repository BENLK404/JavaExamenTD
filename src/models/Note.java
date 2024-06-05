package models;

import database.Connectivity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Note {
    private int idNote;
    private double note;
    private int idEvaluation;
    private int idEtudiant;
    private String commentaire;

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    public String getNomEvaluation() {
        return nomEvaluation;
    }

    public void setNomEvaluation(String nomEvaluation) {
        this.nomEvaluation = nomEvaluation;
    }

    public String getTypeEvaluation() {
        return typeEvaluation;
    }

    public void setTypeEvaluation(String typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
    }

    public String getMatiereEvaluation() {
        return matiereEvaluation;
    }

    public void setMatiereEvaluation(String matiereEvaluation) {
        this.matiereEvaluation = matiereEvaluation;
    }

    private String nomEtudient;
    private String prenomEtudient;
    private String nomEvaluation;
    private String typeEvaluation;
    private String matiereEvaluation;



    public Note(double note, int idEvaluation, int idEtudiant, String commentaire) {
        this.note = note;
        this.idEvaluation = idEvaluation;
        this.idEtudiant = idEtudiant;
        this.commentaire = commentaire;
    }

    public Note(String nomEvaluation, String typeEvaluation, String matiereEvaluation, String nomEtudient, String prenomEtudient, double note, String commentaire) {
        this.nomEvaluation = nomEvaluation;
        this.typeEvaluation = typeEvaluation;
        this.matiereEvaluation = matiereEvaluation;
        this.nomEtudient = nomEtudient;
        this.prenomEtudient = prenomEtudient;
        this.note = note;
        this.commentaire = commentaire;
    }


    public Note(int idNote, double note, int idEvaluation, int idEtudiant) {
        this.idNote = idNote;
        this.note = note;
        this.idEvaluation = idEvaluation;
        this.idEtudiant = idEtudiant;
    }

    public Note(double note, int idEvaluation, int idEtudiant) {
        this.note = note;
        this.idEvaluation = idEvaluation;
        this.idEtudiant = idEtudiant;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public void addNote() throws SQLException {
        Connection connection = Connectivity.getDbConnection();

        String query = "INSERT INTO notes(id_evaluation,id_etudiant,note,commentaires) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idEvaluation);
            preparedStatement.setInt(2, idEtudiant);
            preparedStatement.setDouble(3, note);
            preparedStatement.setString(4, commentaire);
            preparedStatement.executeUpdate();
            System.out.println("Note added");
        }

    }

    public static ObservableList<Note> displayNote() throws SQLException {
        ObservableList<Note> noteObservableList = FXCollections.observableArrayList();
        Connection connection = Connectivity.getDbConnection();
        String query = "    select nom_evaluation,type,nom_matiere,nom,prenom,note,commentaires\n" +
                "    from notes n join evaluations e on e.id_evaluation = n.id_evaluation \n" +
                "        join matieres m on m.id_matiere = e.id_matiere \n" +
                "        join etudiants e2 on e2.id_etudiant = n.id_etudiant";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Note note = new Note(
                        resultSet.getString("nom_evaluation"),
                        resultSet.getString("type"),
                        resultSet.getString("nom_matiere"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDouble("note"),
                        resultSet.getString("commentaires")
                );
                noteObservableList.add(note);
            }
        }
        afficherNoteTableau(noteObservableList);
        return noteObservableList;
    }

//    public void addEvaluationNote(Double note) throws SQLException {
//        Connection connection = Connectivity.getDbConnection();
//        String sql= "update notes set note = ?, commentaires =? where id_etudiant =? and id_evaluation = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setDouble(1, note);
//            preparedStatement.setString(2, commentaire);
//            preparedStatement.setInt(3, idEtudiant);
//            preparedStatement.setInt(4, idEvaluation);
//        }
//
//    }
    public static void afficherNoteTableau(ObservableList<Note>notes) throws SQLException {
        System.out.printf("%-30s %-20s %-15s %-15s  %-10s %-10s\n",
                "Evaluations","Types","Noms","Prenoms","Notes","Commentaires");
        printSeparator(110);
        for (Note note :notes ) {
            System.out.println(note);
        }
    }

    static void printSeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    @Override
    public String toString (){
        return  String.format("%-30s %-20s %-15s %-15s  %-10s %-10s",
                nomEvaluation,typeEvaluation,nomEtudient,prenomEtudient,note,commentaire
                );
    }

    public static int selectIdEvaluation(String matiereEvaluation, String typeEvaluation) throws SQLException {
        int idEvaluation = 0;
        Connection connection = Connectivity.getDbConnection();
        String sql ="select id_evaluation from evaluations " +
                "join ecole.matieres m on evaluations.id_matiere = m.id_matiere where nom_matiere =? and type =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, matiereEvaluation);
            preparedStatement.setString(2, typeEvaluation);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idEvaluation = resultSet.getInt(1);
            }

        }
        return idEvaluation;

    }

    public static void main(String[] args) throws SQLException {


    }




}
