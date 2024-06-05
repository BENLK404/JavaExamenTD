package models;

import database.Connectivity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.Tools;

import java.sql.*;
import java.time.LocalDate;

import static models.Note.printSeparator;
import static models.Note.selectIdEvaluation;

public class Etudiant {
    private int idEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private String sexeEtudiant;

    public Etudiant(int idEtudiant, String nomEtudiant, String prenomEtudiant) {
        this.idEtudiant = idEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    private String adresseEtudiant;
    private String emailEtudiant;
    private String telephoneEtudiant;

    public Etudiant(int idEtudiant, String nomEtudiant, String prenomEtudiant, String sexeEtudiant, String adresseEtudiant, String emailEtudiant, String telephoneEtudiant, String nomClasse, LocalDate dateNaissanceEtudiant, int idClasse) {
        this.idEtudiant = idEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.sexeEtudiant = sexeEtudiant;
        this.adresseEtudiant = adresseEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.telephoneEtudiant = telephoneEtudiant;
        this.nomClasse = nomClasse;
        this.dateNaissanceEtudiant = dateNaissanceEtudiant;
        this.idClasse = idClasse;
    }

    private String nomClasse;
    private LocalDate dateNaissanceEtudiant;

    private int idClasse;

    public Etudiant(int idEtudiant, String nomEtudiant, String prenomEtudiant, String sexeEtudiant, String adresseEtudiant, String emailEtudiant, String telephoneEtudiant, LocalDate dateNaissanceEtudiant, int idClasse) {
        this.idEtudiant = idEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.sexeEtudiant = sexeEtudiant;
        this.adresseEtudiant = adresseEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.telephoneEtudiant = telephoneEtudiant;
        this.dateNaissanceEtudiant = dateNaissanceEtudiant;
        this.idClasse = idClasse;
    }

    public Etudiant(int idEtudiant, String nomEtudiant, String prenomEtudiant, String sexeEtudiant, String adresseEtudiant, String emailEtudiant, String telephoneEtudiant, LocalDate dateNaissanceEtudiant) {
        this.idEtudiant = idEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.sexeEtudiant = sexeEtudiant;
        this.adresseEtudiant = adresseEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.telephoneEtudiant = telephoneEtudiant;
        this.dateNaissanceEtudiant = dateNaissanceEtudiant;
    }

    public Etudiant(String nomEtudiant, String prenomEtudiant, String sexeEtudiant, String adresseEtudiant, String emailEtudiant, String telephoneEtudiant, LocalDate dateNaissanceEtudiant) {
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.sexeEtudiant = sexeEtudiant;
        this.adresseEtudiant = adresseEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.telephoneEtudiant = telephoneEtudiant;
        this.dateNaissanceEtudiant = dateNaissanceEtudiant;
    }

    public Etudiant(String nomEtudiant, String prenomEtudiant, String sexeEtudiant, String adresseEtudiant, String emailEtudiant, String telephoneEtudiant, LocalDate dateNaissanceEtudiant, int idClasse) {
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.sexeEtudiant = sexeEtudiant;
        this.adresseEtudiant = adresseEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.telephoneEtudiant = telephoneEtudiant;
        this.dateNaissanceEtudiant = dateNaissanceEtudiant;
        this.idClasse = idClasse;
    }

    public Etudiant() {

    }

    public void addEtudiant() throws SQLException {
        if (nomEtudiant == null || prenomEtudiant == null || sexeEtudiant == null || adresseEtudiant == null || emailEtudiant == null || telephoneEtudiant == null || dateNaissanceEtudiant == null) {
            throw new IllegalArgumentException("Tous les champs doivent être remplis avant d'ajouter un étudiant.");
        }
            Connection connection = Connectivity.getDbConnection();
            String sql = "INSERT INTO etudiants (nom, prenom, date_naissance, sexe, adresse, telephone, email, id_classe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                assert connection != null;
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, nomEtudiant);
                    preparedStatement.setString(2, prenomEtudiant);
                    preparedStatement.setDate(3, Date.valueOf(dateNaissanceEtudiant));
                    preparedStatement.setString(4, sexeEtudiant);
                    preparedStatement.setString(5, adresseEtudiant);
                    preparedStatement.setString(6, telephoneEtudiant);
                    preparedStatement.setString(7, emailEtudiant);
                    if (idClasse != 0) {
                        preparedStatement.setInt(8, idClasse);
                    } else {
                        preparedStatement.setNull(8, Types.INTEGER);
                    }
                    preparedStatement.executeUpdate();
                    Tools.textInGreen("Étudiant ajouté avec succès!");
                }
            } catch (NullPointerException | SQLException nullPointerException){
                nullPointerException.printStackTrace();
            }

    }
    public void updateEtudiant(int idEtudiant, String nomEtudiant, String prenomEtudiant, String sexeEtudiant, String adresseEtudiant, String emailEtudiant, String telephoneEtudiant, LocalDate dateNaissanceEtudiant,int idClasse) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "update etudiants SET nom =?, prenom=?, date_naissance=?, sexe=?, adresse=?, telephone=?, email=?, id_classe=? where id_etudiant = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, nomEtudiant);
            preparedStatement.setString(2, prenomEtudiant);
            preparedStatement.setDate(3, Date.valueOf(dateNaissanceEtudiant));
            preparedStatement.setString(4, sexeEtudiant);
            preparedStatement.setString(5, adresseEtudiant);
            preparedStatement.setString(6, telephoneEtudiant);
            preparedStatement.setString(7, emailEtudiant);
            preparedStatement.setInt(8, idClasse);
//            if (idClasse != 0) {
//                preparedStatement.setInt(8, idClasse);
//            } else {
//                preparedStatement.setNull(8, Types.INTEGER);
//            }
            preparedStatement.setInt(9, idEtudiant);
            preparedStatement.executeUpdate();
            Tools.textInGreen("Modification effectuer");
        }catch (NullPointerException | SQLException nullPointerException){
            nullPointerException.printStackTrace();
            }
    }
//    public void deleteEtudiant(int idEtudiant) throws SQLException {
//        Connection connection = Connectivity.getDbConnection();
//        String sql = "delete from etudiants where id_etudiant = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
//            preparedStatement.setInt(1, idEtudiant);
//            preparedStatement.executeUpdate();
//            System.out.println("Suppression reussi ");
//        }
//    }
    public void deleteEtudiant(String nomEtudiant, String prenomEtudiant) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "delete from etudiants where nom = ? and  prenom =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, nomEtudiant);
            preparedStatement.setString(2, prenomEtudiant);
            preparedStatement.executeUpdate();
            System.out.println(" ");
            Tools.textInGreen("Suppression Effectuer");
        }
    }
    public void getEtudiantById(int idEtudiant) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "select * from etudiants where id_etudiant = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idEtudiant);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                this.idEtudiant = idEtudiant;
               idClasse = resultSet.getInt("id_classe");
               nomEtudiant = resultSet.getString("nom");
               prenomEtudiant = resultSet.getString("prenom");
               sexeEtudiant = resultSet.getString("sexe");
               adresseEtudiant = resultSet.getString("adresse");
               emailEtudiant = resultSet.getString("email");
               telephoneEtudiant = resultSet.getString("telephone");
               dateNaissanceEtudiant = resultSet.getDate("date_naissance").toLocalDate();
            System.out.println(toString());
            }
        }
    }
    public  Etudiant getEtudiantByName(String nomEtudiant,String prenomEtudiant) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "select * from etudiants where nom = ? and  prenom =?";
        Etudiant etudiant;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nomEtudiant);
            preparedStatement.setString(2, prenomEtudiant);
            ResultSet resultSet = preparedStatement.executeQuery();
            etudiant = new Etudiant();
            while (resultSet.next()) {
                etudiant.setIdEtudiant(resultSet.getInt("id_etudiant"));
                etudiant.setIdClasse(resultSet.getInt("id_classe"));
                etudiant.setNomEtudiant(resultSet.getString("nom"));
                etudiant.setPrenomEtudiant(resultSet.getString("prenom"));
                etudiant.setSexeEtudiant(resultSet.getString("sexe"));
                etudiant.setAdresseEtudiant(resultSet.getString("adresse"));
                etudiant.setEmailEtudiant(resultSet.getString("email"));
                etudiant.setTelephoneEtudiant(resultSet.getString("telephone"));
                etudiant.setDateNaissanceEtudiant(resultSet.getDate("date_naissance").toLocalDate());
            }
        }
        System.out.println(etudiant);

        return etudiant;
    }


    public int getIdEtudiant() {
        return idEtudiant;
    }


    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }

    public String getSexeEtudiant() {
        return sexeEtudiant;
    }

    public void setSexeEtudiant(String sexeEtudiant) {
        this.sexeEtudiant = sexeEtudiant;
    }

    public String getAdresseEtudiant() {
        return adresseEtudiant;
    }

    public void setAdresseEtudiant(String adresseEtudiant) {
        this.adresseEtudiant = adresseEtudiant;
    }

    public String getEmailEtudiant() {
        return emailEtudiant;
    }

    public void setEmailEtudiant(String emailEtudiant) {
        this.emailEtudiant = emailEtudiant;
    }

    public String getTelephoneEtudiant() {
        return telephoneEtudiant;
    }

    public void setTelephoneEtudiant(String telephoneEtudiant) {
        this.telephoneEtudiant = telephoneEtudiant;
    }

    public LocalDate getDateNaissanceEtudiant() {
        return dateNaissanceEtudiant;
    }

    public void setDateNaissanceEtudiant(LocalDate dateNaissanceEtudiant) {
        this.dateNaissanceEtudiant = dateNaissanceEtudiant;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }


    public static ObservableList<Etudiant> displayAllEtudiant() throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        String sql = "select * from etudiants join classes c on c.id_classe = etudiants.id_classe order by id_etudiant asc";
        ObservableList<Etudiant> listEtudiants = FXCollections.observableArrayList();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setIdEtudiant(resultSet.getInt("id_etudiant"));
                etudiant.setIdClasse(resultSet.getInt("id_classe"));
                etudiant.setNomEtudiant(resultSet.getString("nom"));
                etudiant.setPrenomEtudiant(resultSet.getString("prenom"));
                etudiant.setSexeEtudiant(resultSet.getString("sexe"));
                etudiant.setAdresseEtudiant(resultSet.getString("adresse"));
                etudiant.setEmailEtudiant(resultSet.getString("email"));
                etudiant.setTelephoneEtudiant(resultSet.getString("telephone"));
                etudiant.setNomClasse(resultSet.getString("nom_classe"));
                etudiant.setDateNaissanceEtudiant(resultSet.getDate("date_naissance").toLocalDate());
                listEtudiants.add(etudiant);
            }
        }
        return listEtudiants;
    }

    public String toString() {
        return String.format("%-10d %-20s %-20s %-10s %-20s %-20s %-15s %-10s %-20s",
                idEtudiant,nomEtudiant, prenomEtudiant, sexeEtudiant,  nomClasse,adresseEtudiant,
                telephoneEtudiant, dateNaissanceEtudiant,emailEtudiant);
    }
    public String afficherEtudiantEvaluer() throws SQLException {
        return String.format("%-40s %-40s %-40s",
                idEtudiant,nomEtudiant,prenomEtudiant);

    }
    public static void afficherEtudiantEvaluerTableau(ObservableList<Etudiant> etudiants) throws SQLException {
        System.out.printf("%-40s %-40s %-10s\n",
                "ID Etudiant","Nom","Prenom");
        printSeparator(100);
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant.afficherEtudiantEvaluer());
        }
    }
    public static  void selectEtudientsFromEvaluation(String matiereEvaluation, String typeEvaluation) throws SQLException {
        Connection connection = Connectivity.getDbConnection();
        Etudiant etudiant ;
        ObservableList listEtudient = FXCollections.observableArrayList();
        String sql = "select nom,prenom,e.id_etudiant\n" +
                "from notes " +
                "join ecole.etudiants e on e.id_etudiant = notes.id_etudiant" +
                " join ecole.evaluations e2 on e2.id_evaluation = notes.id_evaluation" +
                " where e2.id_evaluation =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, selectIdEvaluation(matiereEvaluation,typeEvaluation));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                etudiant = new Etudiant(
                        resultSet.getInt("id_etudiant"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")
                );
                listEtudient.add((etudiant));
            }
        }
        afficherEtudiantEvaluerTableau(listEtudient);
    }
    public static void main(String[] args) throws SQLException {
        selectEtudientsFromEvaluation("Javascript","Examen");
    }
}

