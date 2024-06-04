// src/database/Connectivity.java
package database;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity {
    private static final String USER = "root";  // Assurez-vous que l'utilisateur est correct
    private static final String PASSWORD = "";  // Assurez-vous que le mot de passe est correct
    private static final String DATABASE = "ecole";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false&serverTimezone=UTC";

    public static Connection getDbConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("👍 Connexion à la base de données '" + DATABASE + "' réussie...😎");
            return connect;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver introuvable " + e.getMessage());
        } catch (SQLNonTransientConnectionException e) {
            System.out.println("Erreur de nom d'utilisateur ou de mot de passe");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Erreur de syntaxe SQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur de connexion: " + e.getMessage());
        }
        return null;
    }
}
