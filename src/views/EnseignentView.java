package views;

import models.Enseignent;
import tools.Tools;

import java.util.List;
import java.util.Scanner;

public class EnseignentView {
    private Scanner scanner;

    public EnseignentView() {
        this.scanner = new Scanner(System.in);
    }

    public void enseignantsChoice(){
        System.out.println("------------------------------------------------------");
          Tools.textInBlue("                   Option Enseignant                  ");
        System.out.println("------------------------------------------------------");
        System.out.println("1. Ajouter un enseignent");
        System.out.println("2. Modifier un enseignent");
        System.out.println("3. Supprimer un enseignent");
        System.out.println("4. Afficher les enseignent");
        System.out.println("0. Retour au menu");
    }

    public String[] demanderInformationsAddEnseignent(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                          Information de l'enseignent                           ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("0. Retour");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();

        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();

        System.out.print("Telephone: ");
        String telephone = scanner.nextLine();

        System.out.print("Email(...@gmail.com): ");
        String email = scanner.nextLine();

        System.out.print("Matière: ");
        String matiere = scanner.nextLine();

        return new String[]{nom, prenom, email, telephone, matiere};
    }

    public static void afficherEnseignents(List<Enseignent> enseignants){
        System.out.println(String.format("%-10s %-15s %-20s %-25s %-20s %-10s %15s",
                "ID", "Nom", "Prénom", "Email", "Téléphone", "Matiere","Classe"));
        printSeparator(122);
        for (Enseignent e : enseignants) {
            System.out.println(e);
        }
    }

    private static void printSeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public String[] demanderInformationsUpdateEnseignent(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                          Information de l'enseignent                           ");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("ID de l'enseignent: ");
        String id = scanner.nextLine();
        System.out.print("Nouveau Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Nouveau Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Nouveau Email(...@gmail.com): ");
        String email = scanner.nextLine();
        System.out.print("Nouveau Telephone: ");
        String telephone = scanner.nextLine();
        System.out.print("Nouvelle Matière: ");
        String matiere = scanner.nextLine();
        return new String[]{id,nom,prenom,email, telephone,matiere};
    }

    public String[] demanderInformationsDeleteEnseigent(){

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                          Information de l'enseignent                           ");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("0. Retour");
        System.out.println(" ");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prenom: ");
        String prenom = scanner.nextLine();

        return new String[]{nom,prenom};
    }
}
