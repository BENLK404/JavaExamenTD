package views;


import javafx.collections.ObservableList;

import models.Evaluation;
import tools.Tools;



import java.sql.SQLException;
import java.util.Scanner;

public class EvaluationView {
    private Scanner scanner;
    public EvaluationView() {
        scanner = new Scanner(System.in);
    }
    public void evaluationsChoice(){
        System.out.println("------------------------------------------------------");
          Tools.textInBlue("                   Option Evaluation                  ");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Ajouter une evaluation");
        System.out.println("2. Modifier une evaluation");
        System.out.println("3. Supprimer une evaluation");
        System.out.println("4. Afficher les evaluation");
        System.out.println("5. Afficher le classement");
        System.out.println("0. Retour au menu");
        System.out.println(" ");

    }
    public void classementEvaluationsChoice(){
        System.out.println("------------------------------------------------------");
        System.out.println("                   Option Classement                  ");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Classement generale");
        System.out.println("2. Classement par Type d'evaluation"); ;
        System.out.println("3. Classement par matiere");
        System.out.println("4. Classement par classe");
        System.out.println("5. Classement par note");
        System.out.println("6. Retour");
        System.out.println("0. Retour au menu");
        System.out.println(" ");
    }
    public String[] demanderInformationsClassementType(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information du classement                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("Nom de l'evaluation : ");
        String nom_evaluation = scanner.nextLine();
        System.out.print("Type de l'evaluation : ");
        String type_evaluation = scanner.nextLine();

        return new String[]{nom_evaluation,type_evaluation};
    }
    public String[] demanderInformationsClassementClasse(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information du classement                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("Nom de l'evaluation : ");
        String nom_evaluation = scanner.nextLine();
        System.out.print("Classe evaluee : ");
        String classe = scanner.nextLine();
        return new String[]{nom_evaluation,classe};
    }
    public String[] demanderInformationsClassementMatiere(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information du classement                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("Nom de l'evaluation : ");
        String nom_evaluation = scanner.nextLine();
        System.out.print("Nom de la matiere : ");
        String matiere = scanner.nextLine();
        return new String[]{nom_evaluation,matiere};
    }
    public String[] demanderInformationsAddEvaluation(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information du Evaluation                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("Nom de l'evaluation : ");
        String nom_evaluation = scanner.nextLine();
        System.out.print("Description de l'evaluation : ");
        String description = scanner.nextLine();
        System.out.print("Date de l'evaluation (YYYY-MM-DD): ");
        String date_evaluation = scanner.nextLine();
        System.out.print("Type de l'evaluation : ");
        String type_evaluation = scanner.nextLine();
        System.out.print("Nom de la matiere : ");
        String nom_matiere = scanner.nextLine();

        return new String[]{nom_evaluation,description,date_evaluation,type_evaluation,nom_matiere};
    }
    public String[] demanderInformationsDeleteEvaluation(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information du Evaluation                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("Nom de l'evaluation : ");
        String nom_evaluation = scanner.nextLine();
        System.out.print("Type de l'evaluation : ");
        String type_evaluation = scanner.nextLine();
        System.out.print("Nom de la matiere : ");
        String nom_matiere = scanner.nextLine();
        return new String[]{nom_evaluation,type_evaluation,nom_matiere};
    }

    public static void afficherEvaluations(ObservableList<Evaluation> listEvaluationsAffiche) throws SQLException {
        System.out.printf("%-10s %-20s %-30s %-15s %-20s %-15s\n",
                "ID", "Nom", "Description", "Date", "Nom Matiere", "Type");
        printSeparator(110);
        for (Evaluation evaluation : listEvaluationsAffiche) {
            System.out.println(evaluation);
        }
    }

    private static void printSeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }


}
