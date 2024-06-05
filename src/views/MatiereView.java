package views;

import java.util.Scanner;

public class MatiereView {
    private Scanner scanner;

    public MatiereView() {
        this.scanner = new Scanner(System.in);
    }

    public void matiereChoice(){
        System.out.println("------------------------------------------------------");
        System.out.println("                   Option Matiere                     ");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Ajouter une matiere");
        System.out.println("2. Modifier une matiere");
        System.out.println("3. Supprimer une matiere");
        System.out.println("4. Afficher les matiere");
        System.out.println("0. Retour au menu");
        System.out.println(" ");
    }
    public String[] demanderInformationsAddMatiere(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information de la matiere                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        return new String[]{nom,description};

    }
    public String[] demanderInformationsUpdateMatiere(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                           Information de la matiere                            ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("ID de la matiere: ");
        String idMatiere = scanner.nextLine();
        System.out.print("Nouveau Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Nouvelle Description: ");
        String description = scanner.nextLine();
        return new String[]{idMatiere,nom,description};

    }
}
