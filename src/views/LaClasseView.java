package views;

import java.util.Scanner;

public class LaClasseView {
    private Scanner scanner;
    public LaClasseView() {
        scanner = new Scanner(System.in);
    }

    public void classeChoice(){
        System.out.println("------------------------------------------------------");
        System.out.println("                   Option Classe                      ");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Ajouter une classe");
        System.out.println("2. Modifier une classe");
        System.out.println("3. Supprimer une classe");
        System.out.println("4. Afficher les classe");
        System.out.println("0. Retour au menu");
        System.out.println(" ");
    }
    public String[] demanderInformationsClasse(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information de la classe                             ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Niveau: ");
        String niveau = scanner.nextLine();
        return new String[]{nom,niveau};
    }
}
