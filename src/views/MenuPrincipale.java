package views;

import tools.Tools;

import java.util.Scanner;

public class MenuPrincipale {
    private static Scanner scanner;
    public MenuPrincipale() {
        scanner = new Scanner(System.in);
    }
    public void afficherMenuPrincipale() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                                                                                                                                                                                                 ");
        Tools.textInYellow("                                                                               GESTIONNAIRE D'EVALUATION À ESIBA                                                                                 ");
        System.out.println("                                                                                                                                                                                                 ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(" ");
        System.out.println("1. Gestion des etudiants");
        System.out.println("2. Gestion des classes");
        System.out.println("3. Gestion des evaluations");
        System.out.println("4. Gestion des enseignents");
        System.out.println("5. Gestion des notes");
        System.out.println("6. Gestion des matières");
        System.out.println("7. Quitter");
        System.out.println(" ");
    }

    public static int lireChoix() {
        int option = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Choisissez une option: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
                validInput = true;  // input is valid, exit the loop
            } catch (NumberFormatException e) {
                Tools.textInRed("Choisissez un option valide");
            }
        }
        return option;
    }














}
