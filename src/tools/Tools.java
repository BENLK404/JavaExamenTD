package tools;
import database.Connectivity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Tools {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void textInRed(String text) {
        System.out.println(ANSI_RED+text+ANSI_RESET);
    }
    public static void textInGreen(String text) {
        System.out.println(ANSI_GREEN+text+ANSI_RESET);
    }
    public static void textInYellow(String text) {
        System.out.println(ANSI_YELLOW+text+ANSI_RESET);
    }
    public static void textInBlue(String text) {
        System.out.println(ANSI_BLUE+text+ANSI_RESET);
    }
    public static void textInPurple(String text) {
        System.out.println(ANSI_PURPLE+text+ANSI_RESET);
    }
    public static void textInCyan(String text) {
        System.out.println(ANSI_CYAN+text+ANSI_RESET);

    }
    public static void textInWhite(String text) {
        System.out.println(ANSI_WHITE+text+ANSI_RESET);
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                // Commande pour Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Commande pour Unix-based systems (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkIfExists(String tableName, String columnName, String value) throws SQLException {
        try (Connection connect = Connectivity.getDbConnection()) {
            String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";
            assert connect != null;
            try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
                preparedStatement.setString(1, value);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        }
    }



}
