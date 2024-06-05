package models;

import database.Connectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Note {
    private int idNote;
    private double note;
    private int idEvaluation;
    private int idEtudiant;
    private String commentaire;

    public Note(double note, int idEvaluation, int idEtudiant, String commentaire) {
        this.note = note;
        this.idEvaluation = idEvaluation;
        this.idEtudiant = idEtudiant;
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Note{" +
                "idNote=" + idNote +
                ", note=" + note +
                ", idEvaluation=" + idEvaluation +
                ", idEtudiant=" + idEtudiant +
                '}';
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

}
